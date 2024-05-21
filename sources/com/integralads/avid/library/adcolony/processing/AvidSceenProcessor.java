package com.integralads.avid.library.adcolony.processing;

import android.view.View;
import com.integralads.avid.library.adcolony.activity.AvidActivityStack;
import com.integralads.avid.library.adcolony.processing.IAvidNodeProcessor;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import org.json.JSONObject;

public class AvidSceenProcessor implements IAvidNodeProcessor {
    private final IAvidNodeProcessor a;

    public AvidSceenProcessor(IAvidNodeProcessor iAvidNodeProcessor) {
        this.a = iAvidNodeProcessor;
    }

    public JSONObject getState(View view) {
        return AvidJSONUtil.getViewState(0, 0, 0, 0);
    }

    public void iterateChildren(View view, JSONObject jSONObject, IAvidNodeProcessor.IAvidViewWalker iAvidViewWalker, boolean z) {
        for (View walkView : AvidActivityStack.getInstance().getRootViews()) {
            iAvidViewWalker.walkView(walkView, this.a, jSONObject);
        }
    }
}
