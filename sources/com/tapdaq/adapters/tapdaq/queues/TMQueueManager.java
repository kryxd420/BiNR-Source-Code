package com.tapdaq.adapters.tapdaq.queues;

import android.content.Context;
import com.google.gson.Gson;
import com.tapdaq.adapters.tapdaq.TDResponseHandler;
import com.tapdaq.adapters.tapdaq.model.TMAd;
import com.tapdaq.adapters.tapdaq.model.TMQueue;
import com.tapdaq.adapters.tapdaq.model.TMQueueID;
import com.tapdaq.sdk.CreativeType;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.helpers.TLog;
import com.tapdaq.sdk.network.TMServiceClient;
import com.tapdaq.sdk.storage.FileStorage;
import com.tapdaq.sdk.storage.FileStorageProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TMQueueManager {
    private List<TMQueueID> mPendingQueues = new ArrayList();
    private Map<String, TMAdQueue> mQueues = new HashMap();
    private TDResponseHandler mResponseHandler;
    private TMServiceClient mServiceClient = new TMServiceClient();

    public TMQueueManager(TDResponseHandler tDResponseHandler) {
        this.mResponseHandler = tDResponseHandler;
    }

    public void requestQueues(Context context, List<TMQueueID> list) {
        this.mPendingQueues.addAll(list);
        for (TMQueueID next : list) {
        }
    }

    public List<String> getPlacements(CreativeType creativeType) {
        ArrayList arrayList = new ArrayList();
        String name = creativeType.name();
        for (String next : this.mQueues.keySet()) {
            if (next.contains(name)) {
                arrayList.add(next.replace(name, "").substring(1));
            }
        }
        return arrayList;
    }

    public TMAdQueue getQueue(String str, String str2) {
        String format = String.format(Locale.ENGLISH, "%s-%s", new Object[]{str2, str});
        if (this.mQueues.containsKey(format)) {
            return this.mQueues.get(format);
        }
        return null;
    }

    public void adQueueReceived(Context context, TMQueueID tMQueueID, TMQueue tMQueue) {
        if (tMQueue != null && tMQueue.getAdverts() != null) {
            String creativetype = tMQueue.getCreativetype();
            for (TMAd next : tMQueue.getAdverts()) {
                for (String next2 : next.getPlacementTags()) {
                    String format = String.format(Locale.ENGLISH, "%s-%s", new Object[]{creativetype, next2});
                    if (this.mQueues.containsKey(format)) {
                        this.mQueues.get(format).adAd(next);
                    } else {
                        TMAdQueue tMAdQueue = new TMAdQueue(next2, creativetype);
                        tMAdQueue.adAd(next);
                        this.mQueues.put(format, tMAdQueue);
                    }
                }
            }
            FileStorageProvider.getInstance(context).saveFile(String.format(Locale.ENGLISH, "QueueID-%s-Type-%s", new Object[]{tMQueueID.getID(), tMQueueID.getCreativeType()}), "Queues", new Gson().toJson((Object) tMQueue), true);
            this.mPendingQueues.remove(tMQueueID);
            checkQueuesComplete();
        }
    }

    public void adQueueFailed(Context context, TMQueueID tMQueueID) {
        String loadFile = new FileStorage(context).loadFile(String.format(Locale.ENGLISH, "QueueID-%s-Type-%s", new Object[]{tMQueueID.getID(), tMQueueID.getCreativeType()}), "Queues");
        if (loadFile != null) {
            try {
                adQueueReceived(context, tMQueueID, (TMQueue) new Gson().fromJson(loadFile, TMQueue.class));
            } catch (Exception e) {
                TLog.error(e);
                this.mPendingQueues.remove(tMQueueID);
                checkQueuesComplete();
            }
        } else {
            this.mPendingQueues.remove(tMQueueID);
            checkQueuesComplete();
        }
    }

    private void checkQueuesComplete() {
        if (this.mPendingQueues.isEmpty()) {
            TLog.debug("Promotion Queues Complete");
            if (this.mQueues.isEmpty()) {
                this.mResponseHandler.onError(new TMAdError(-1, "No Queues Available"));
            } else {
                this.mResponseHandler.onSuccess();
            }
        }
    }
}
