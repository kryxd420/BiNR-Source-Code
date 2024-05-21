package com.integralads.avid.library.adcolony.walking.async;

import com.integralads.avid.library.adcolony.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.adcolony.utils.AvidCommand;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.integralads.avid.library.adcolony.walking.async.AvidAsyncTask;
import java.util.HashSet;
import org.json.JSONObject;

public class AvidEmptyPublishAsyncTask extends AbstractAvidPublishAsyncTask {
    public AvidEmptyPublishAsyncTask(AvidAsyncTask.StateProvider stateProvider, AvidAdSessionRegistry avidAdSessionRegistry, HashSet<String> hashSet, JSONObject jSONObject, double d) {
        super(stateProvider, avidAdSessionRegistry, hashSet, jSONObject, d);
    }

    /* access modifiers changed from: protected */
    public String doInBackground(Object... objArr) {
        return AvidCommand.setNativeViewState(AvidJSONUtil.getTreeJSONObject(this.state, this.timestamp).toString());
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String str) {
        for (InternalAvidAdSession next : this.adSessionRegistry.getInternalAvidAdSessions()) {
            if (this.sessionIds.contains(next.getAvidAdSessionId())) {
                next.publishEmptyNativeViewStateCommand(str, this.timestamp);
            }
        }
        super.onPostExecute(str);
    }
}
