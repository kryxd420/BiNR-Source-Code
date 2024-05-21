package com.vungle.warren;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.tapdaq.sdk.adnetworks.TMMediationNetworks;
import com.vungle.warren.model.Advertisement;
import com.vungle.warren.model.Cookie;
import com.vungle.warren.model.Placement;
import com.vungle.warren.model.Report;
import com.vungle.warren.persistence.Designer;
import com.vungle.warren.persistence.Memorable;
import com.vungle.warren.persistence.Persistor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {
    public static final int DB_VERSION = 1;
    private static final String TAG = "Storage";
    private final Map<String, Advertisement> advMap = new ConcurrentHashMap();
    private final Designer designer;
    /* access modifiers changed from: private */
    public final Persistor persistor;
    private final Map<String, Placement> placementsMap = new ConcurrentHashMap();
    private List<String> validPlacements = new ArrayList();

    private Storage(@NonNull Persistor persistor2, @NonNull Designer designer2) {
        this.persistor = persistor2;
        this.designer = designer2;
    }

    static Storage makeInstance(@NonNull Persistor persistor2, @NonNull Designer designer2) {
        return new Storage(persistor2, designer2);
    }

    public void init(int i) {
        this.placementsMap.clear();
        this.advMap.clear();
        this.persistor.init(i, new Migrator());
        List<Advertisement> extractAll = this.persistor.extractAll(Advertisement.class);
        if (extractAll != null && extractAll.size() > 0) {
            for (Advertisement next : extractAll) {
                if (next != null) {
                    if (next.getState() == 2) {
                        next.setState(3);
                        save(next);
                        String str = TAG;
                        Log.i(str, "Advertisement " + next.getId() + " state marked as DONE, it stuck in VIEWING state");
                    } else if (next.getState() == 1 && !hasValidAssets(next)) {
                        delete(next);
                        try {
                            this.designer.deleteAssets(next.getId());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void save(@NonNull Memorable memorable) {
        if (memorable instanceof Placement) {
            this.placementsMap.put(memorable.getId(), (Placement) memorable);
        } else if (memorable instanceof Advertisement) {
            this.advMap.put(memorable.getId(), (Advertisement) memorable);
        }
        this.persistor.save(memorable);
    }

    @Nullable
    public <T extends Memorable> T load(@NonNull String str, @NonNull Class<T> cls) {
        if (Placement.class.isAssignableFrom(cls)) {
            Placement placement = this.placementsMap.get(str);
            if (placement != null) {
                return placement.copy();
            }
            T t = (Placement) this.persistor.find(str, cls);
            if (t != null) {
                this.placementsMap.put(str, t);
            }
            return t;
        } else if (!Advertisement.class.isAssignableFrom(cls)) {
            return this.persistor.find(str, cls);
        } else {
            Advertisement advertisement = this.advMap.get(str);
            if (advertisement != null) {
                return advertisement.copy();
            }
            T t2 = (Advertisement) this.persistor.find(str, cls);
            if (t2 != null) {
                this.advMap.put(str, t2);
            }
            return t2;
        }
    }

    @NonNull
    public <T extends Memorable> List<T> loadAll(@NonNull Class<T> cls) {
        return this.persistor.extractAll(cls);
    }

    public void delete(@NonNull Memorable memorable) {
        if (memorable instanceof Placement) {
            this.placementsMap.remove(memorable.getId());
        } else if (memorable instanceof Advertisement) {
            this.advMap.remove(memorable.getId());
        }
        this.persistor.delete(memorable);
    }

    @Nullable
    public Advertisement findValidAdvertisementForPlacement(@Nullable String str) {
        Advertisement advertisement;
        String str2;
        Placement placement = (Placement) load(str, Placement.class);
        Log.i(TAG, " Searching for valid adv for pl " + str);
        if (placement == null || placement.getAdvertisementIDs() == null || placement.getAdvertisementIDs().isEmpty()) {
            return null;
        }
        Log.i(TAG, " Searching for valid adv for pl " + str + " all ids " + placement.getAdvertisementIDs());
        Iterator<String> it = placement.getAdvertisementIDs().iterator();
        while (true) {
            if (!it.hasNext()) {
                advertisement = null;
                break;
            }
            advertisement = (Advertisement) load(it.next(), Advertisement.class);
            if (advertisement != null) {
                boolean z = false;
                boolean z2 = advertisement.getState() == 1 || advertisement.getState() == 0;
                if (advertisement.getExpireTime() > System.currentTimeMillis()) {
                    z = true;
                }
                if (z && z2) {
                    break;
                }
            }
        }
        String str3 = TAG;
        if (advertisement == null) {
            str2 = "Didn't find valid adv";
        } else {
            str2 = "Found valid adv " + advertisement.getId();
        }
        Log.i(str3, str2);
        return advertisement;
    }

    public boolean hasValidAssets(Advertisement advertisement) {
        return this.designer.hasAssetsFor(advertisement.getId(), advertisement.getDownloadableUrls().size());
    }

    public synchronized void setValidPlacements(@NonNull List<Placement> list) {
        this.validPlacements.clear();
        for (Placement next : list) {
            Placement placement = (Placement) load(next.getId(), Placement.class);
            if (placement != null && !placement.equalsIgnoreAdvertisements(next)) {
                String str = TAG;
                Log.w(str, "Placements data for " + next.getId() + " is different from disc, deleting old");
                try {
                    for (String next2 : next.getAdvertisementIDs()) {
                        Advertisement advertisement = (Advertisement) load(next2, Advertisement.class);
                        if (advertisement != null) {
                            delete(advertisement);
                        }
                        this.designer.deleteAssets(next2);
                    }
                    delete(placement);
                } catch (IOException e) {
                    Log.e(TMMediationNetworks.VUNGLE_NAME, "Failed to delete old assets, this could lead to disk space errors");
                    Log.e(TMMediationNetworks.VUNGLE_NAME, e.getMessage());
                }
            } else if (placement != null) {
                next = placement;
            }
            save(next);
            this.validPlacements.add(next.getId());
        }
    }

    public void saveAndApplyState(@NonNull Advertisement advertisement, @NonNull String str, @Advertisement.State int i) {
        String str2 = TAG;
        Log.i(str2, "Setting " + i + " for adv " + advertisement.getId() + " and pl " + str);
        advertisement.setState(i);
        save(advertisement);
        String id = advertisement.getId();
        switch (i) {
            case 0:
            case 1:
                addAdvertisementToPlacement(str, id);
                return;
            case 2:
                removeAdvertisementFromPlacement(str, id);
                return;
            case 3:
            case 4:
                removeAdvertisementFromPlacement(str, id);
                delete(advertisement);
                try {
                    this.designer.deleteAssets(id);
                    return;
                } catch (IOException e) {
                    String str3 = TAG;
                    Log.e(str3, "error on deleting assets for " + advertisement.getId(), e);
                    return;
                }
            default:
                return;
        }
    }

    public void clearAllData() {
        this.designer.clearCache();
        this.persistor.clearCache();
        this.advMap.clear();
        this.placementsMap.clear();
        Log.d(TAG, "Cache cleared.");
    }

    public synchronized Collection<Placement> loadValidPlacements() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (String load : this.validPlacements) {
            Placement placement = (Placement) load(load, Placement.class);
            if (placement != null) {
                arrayList.add(placement.copy());
            }
        }
        return arrayList;
    }

    public synchronized Collection<String> getValidPlacements() {
        return new ArrayList(this.validPlacements);
    }

    public File getAdvertisementAssetDirectory(String str) {
        return this.designer.getAssetDirectory(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void removeAdvertisementFromPlacement(java.lang.String r2, java.lang.String r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            java.lang.Class<com.vungle.warren.model.Placement> r0 = com.vungle.warren.model.Placement.class
            com.vungle.warren.persistence.Memorable r2 = r1.load(r2, r0)     // Catch:{ all -> 0x001c }
            com.vungle.warren.model.Placement r2 = (com.vungle.warren.model.Placement) r2     // Catch:{ all -> 0x001c }
            if (r2 == 0) goto L_0x001a
            boolean r0 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x0012
            goto L_0x001a
        L_0x0012:
            r2.removeAdvertisementID(r3)     // Catch:{ all -> 0x001c }
            r1.save(r2)     // Catch:{ all -> 0x001c }
            monitor-exit(r1)
            return
        L_0x001a:
            monitor-exit(r1)
            return
        L_0x001c:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.warren.Storage.removeAdvertisementFromPlacement(java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void addAdvertisementToPlacement(java.lang.String r2, java.lang.String r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            java.lang.Class<com.vungle.warren.model.Placement> r0 = com.vungle.warren.model.Placement.class
            com.vungle.warren.persistence.Memorable r2 = r1.load(r2, r0)     // Catch:{ all -> 0x001c }
            com.vungle.warren.model.Placement r2 = (com.vungle.warren.model.Placement) r2     // Catch:{ all -> 0x001c }
            if (r2 == 0) goto L_0x001a
            boolean r0 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x0012
            goto L_0x001a
        L_0x0012:
            r2.addAdvertisementID(r3)     // Catch:{ all -> 0x001c }
            r1.save(r2)     // Catch:{ all -> 0x001c }
            monitor-exit(r1)
            return
        L_0x001a:
            monitor-exit(r1)
            return
        L_0x001c:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.warren.Storage.addAdvertisementToPlacement(java.lang.String, java.lang.String):void");
    }

    private class Migrator implements Persistor.MigrationCallback {
        private Migrator() {
        }

        public void onUpgrade(int i, int i2) {
            if (i < 1) {
                Storage.this.persistor.migrateData(i, i2, Report.class, new Persistor.Transformation<Report>() {
                    public Report transform(int i, int i2, byte[] bArr) {
                        return Report.restore(i, i2, bArr);
                    }
                });
                Storage.this.persistor.migrateData(i, i2, Cookie.class, new Persistor.Transformation<Cookie>() {
                    public Cookie transform(int i, int i2, byte[] bArr) {
                        return Cookie.restore(i, i2, bArr);
                    }
                });
                Storage.this.persistor.migrateData(i, i2, Placement.class, new Persistor.Transformation<Placement>() {
                    public Placement transform(int i, int i2, byte[] bArr) {
                        Placement restore = Placement.restore(i, i2, bArr);
                        if (restore != null) {
                            for (String removeAdvertisementID : restore.getAdvertisementIDs()) {
                                restore.removeAdvertisementID(removeAdvertisementID);
                            }
                        }
                        return restore;
                    }
                });
                Storage.this.persistor.migrateData(i, i2, Advertisement.class, new Persistor.Transformation<Advertisement>() {
                    public Advertisement transform(int i, int i2, byte[] bArr) {
                        return null;
                    }
                });
            }
        }

        public void onDowngrade(int i, int i2) {
            Storage.this.clearAllData();
        }
    }
}
