package com.tapjoy.internal;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v4.app.NotificationCompatExtras;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.util.ArrayList;

public final class jm {

    public static class a {
        final Bundle a;
        final jp[] b;
        final jp[] c;
        boolean d;
        public int e;
        public CharSequence f;
        public PendingIntent g;
    }

    public static class c {
        Bundle A;
        int B = 0;
        int C = 0;
        Notification D;
        RemoteViews E;
        RemoteViews F;
        RemoteViews G;
        String H;
        int I = 0;
        String J;
        long K;
        int L = 0;
        Notification M = new Notification();
        @Deprecated
        public ArrayList N;
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Context a;
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public ArrayList b = new ArrayList();
        CharSequence c;
        CharSequence d;
        PendingIntent e;
        PendingIntent f;
        RemoteViews g;
        Bitmap h;
        CharSequence i;
        int j;
        int k;
        boolean l = true;
        boolean m;
        d n;
        CharSequence o;
        CharSequence[] p;
        int q;
        int r;
        boolean s;
        String t;
        boolean u;
        String v;
        boolean w = false;
        boolean x;
        boolean y;
        String z;

        public c(@NonNull Context context, @NonNull String str) {
            this.a = context;
            this.H = str;
            this.M.when = System.currentTimeMillis();
            this.M.audioStreamType = -1;
            this.k = 0;
            this.N = new ArrayList();
        }

        public final c a(int i2) {
            this.M.icon = i2;
            return this;
        }

        public final c a(CharSequence charSequence) {
            this.c = d(charSequence);
            return this;
        }

        public final c b(CharSequence charSequence) {
            this.d = d(charSequence);
            return this;
        }

        public final c a(PendingIntent pendingIntent) {
            this.e = pendingIntent;
            return this;
        }

        public final c c(CharSequence charSequence) {
            this.M.tickerText = d(charSequence);
            return this;
        }

        public final c a(Bitmap bitmap) {
            this.h = bitmap;
            return this;
        }

        public final c b() {
            this.M.defaults = 1;
            return this;
        }

        public final c c() {
            this.k = 0;
            return this;
        }

        public final c a(d dVar) {
            if (this.n != dVar) {
                this.n = dVar;
                if (this.n != null) {
                    this.n.a(this);
                }
            }
            return this;
        }

        public final Notification d() {
            Notification notification;
            jn jnVar = new jn(this);
            d dVar = jnVar.b.n;
            if (dVar != null) {
                dVar.a((jl) jnVar);
            }
            if (Build.VERSION.SDK_INT >= 26) {
                notification = jnVar.a.build();
            } else if (Build.VERSION.SDK_INT >= 24) {
                notification = jnVar.a.build();
                if (jnVar.g != 0) {
                    if (!(notification.getGroup() == null || (notification.flags & 512) == 0 || jnVar.g != 2)) {
                        jn.a(notification);
                    }
                    if (notification.getGroup() != null && (notification.flags & 512) == 0 && jnVar.g == 1) {
                        jn.a(notification);
                    }
                }
            } else if (Build.VERSION.SDK_INT >= 21) {
                jnVar.a.setExtras(jnVar.f);
                notification = jnVar.a.build();
                if (jnVar.c != null) {
                    notification.contentView = jnVar.c;
                }
                if (jnVar.d != null) {
                    notification.bigContentView = jnVar.d;
                }
                if (jnVar.h != null) {
                    notification.headsUpContentView = jnVar.h;
                }
                if (jnVar.g != 0) {
                    if (!(notification.getGroup() == null || (notification.flags & 512) == 0 || jnVar.g != 2)) {
                        jn.a(notification);
                    }
                    if (notification.getGroup() != null && (notification.flags & 512) == 0 && jnVar.g == 1) {
                        jn.a(notification);
                    }
                }
            } else if (Build.VERSION.SDK_INT >= 20) {
                jnVar.a.setExtras(jnVar.f);
                notification = jnVar.a.build();
                if (jnVar.c != null) {
                    notification.contentView = jnVar.c;
                }
                if (jnVar.d != null) {
                    notification.bigContentView = jnVar.d;
                }
                if (jnVar.g != 0) {
                    if (!(notification.getGroup() == null || (notification.flags & 512) == 0 || jnVar.g != 2)) {
                        jn.a(notification);
                    }
                    if (notification.getGroup() != null && (notification.flags & 512) == 0 && jnVar.g == 1) {
                        jn.a(notification);
                    }
                }
            } else if (Build.VERSION.SDK_INT >= 19) {
                SparseArray a2 = jo.a(jnVar.e);
                if (a2 != null) {
                    jnVar.f.putSparseParcelableArray(NotificationCompatExtras.EXTRA_ACTION_EXTRAS, a2);
                }
                jnVar.a.setExtras(jnVar.f);
                notification = jnVar.a.build();
                if (jnVar.c != null) {
                    notification.contentView = jnVar.c;
                }
                if (jnVar.d != null) {
                    notification.bigContentView = jnVar.d;
                }
            } else if (Build.VERSION.SDK_INT >= 16) {
                notification = jnVar.a.build();
                Bundle a3 = jm.a(notification);
                Bundle bundle = new Bundle(jnVar.f);
                for (String str : jnVar.f.keySet()) {
                    if (a3.containsKey(str)) {
                        bundle.remove(str);
                    }
                }
                a3.putAll(bundle);
                SparseArray a4 = jo.a(jnVar.e);
                if (a4 != null) {
                    jm.a(notification).putSparseParcelableArray(NotificationCompatExtras.EXTRA_ACTION_EXTRAS, a4);
                }
                if (jnVar.c != null) {
                    notification.contentView = jnVar.c;
                }
                if (jnVar.d != null) {
                    notification.bigContentView = jnVar.d;
                }
            } else {
                notification = jnVar.a.getNotification();
            }
            if (jnVar.b.E != null) {
                notification.contentView = jnVar.b.E;
            }
            int i2 = Build.VERSION.SDK_INT;
            int i3 = Build.VERSION.SDK_INT;
            if (Build.VERSION.SDK_INT >= 16 && dVar != null) {
                jm.a(notification);
            }
            return notification;
        }

        protected static CharSequence d(CharSequence charSequence) {
            return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
        }

        public final c a() {
            this.M.flags |= 16;
            return this;
        }
    }

    public static abstract class d {
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        protected c a;
        CharSequence b;
        CharSequence c;
        boolean d = false;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void a(jl jlVar) {
        }

        public final void a(c cVar) {
            if (this.a != cVar) {
                this.a = cVar;
                if (this.a != null) {
                    this.a.a(this);
                }
            }
        }
    }

    public static class b extends d {
        private CharSequence e;

        public final b a(CharSequence charSequence) {
            this.b = c.d(charSequence);
            return this;
        }

        public final b b(CharSequence charSequence) {
            this.e = c.d(charSequence);
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public final void a(jl jlVar) {
            if (Build.VERSION.SDK_INT >= 16) {
                Notification.BigTextStyle bigText = new Notification.BigTextStyle(jlVar.a()).setBigContentTitle(this.b).bigText(this.e);
                if (this.d) {
                    bigText.setSummaryText(this.c);
                }
            }
        }
    }

    public static Bundle a(Notification notification) {
        if (Build.VERSION.SDK_INT >= 19) {
            return notification.extras;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return jo.a(notification);
        }
        return null;
    }
}
