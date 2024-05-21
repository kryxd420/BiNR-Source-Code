package com.unity3d.ads.misc;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import com.unity3d.ads.log.DeviceLog;

public class ViewUtilities {
    public static void removeViewFromParent(View view) {
        if (view != null && view.getParent() != null) {
            try {
                ((ViewGroup) view.getParent()).removeView(view);
            } catch (Exception e) {
                DeviceLog.exception("Error while removing view from it's parent", e);
            }
        }
    }

    public static void setBackground(View view, Drawable drawable) {
        Class<View> cls = View.class;
        String str = "setBackground";
        if (Build.VERSION.SDK_INT < 16) {
            str = "setBackgroundDrawable";
        }
        try {
            cls.getMethod(str, new Class[]{Drawable.class}).invoke(view, new Object[]{drawable});
        } catch (Exception e) {
            DeviceLog.exception("Couldn't run" + str, e);
        }
    }
}
