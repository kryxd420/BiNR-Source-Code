package com.tapjoy.internal;

import android.app.Notification;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompatExtras;
import android.util.Log;
import android.util.SparseArray;
import com.tapjoy.internal.jm;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiresApi(16)
final class jo {
    private static final Object a = new Object();
    private static Field b;
    private static boolean c;
    private static final Object d = new Object();

    public static SparseArray a(List list) {
        int size = list.size();
        SparseArray sparseArray = null;
        for (int i = 0; i < size; i++) {
            Bundle bundle = (Bundle) list.get(i);
            if (bundle != null) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                }
                sparseArray.put(i, bundle);
            }
        }
        return sparseArray;
    }

    public static Bundle a(Notification notification) {
        synchronized (a) {
            if (c) {
                return null;
            }
            try {
                if (b == null) {
                    Field declaredField = Notification.class.getDeclaredField("extras");
                    if (!Bundle.class.isAssignableFrom(declaredField.getType())) {
                        Log.e(NotificationCompatJellybean.TAG, "Notification.extras field is not of type Bundle");
                        c = true;
                        return null;
                    }
                    declaredField.setAccessible(true);
                    b = declaredField;
                }
                Bundle bundle = (Bundle) b.get(notification);
                if (bundle == null) {
                    bundle = new Bundle();
                    b.set(notification, bundle);
                }
                return bundle;
            } catch (IllegalAccessException e) {
                Log.e(NotificationCompatJellybean.TAG, "Unable to access notification extras", e);
                c = true;
                return null;
            } catch (NoSuchFieldException e2) {
                Log.e(NotificationCompatJellybean.TAG, "Unable to access notification extras", e2);
                c = true;
                return null;
            }
        }
    }

    private static Bundle[] a(jp[] jpVarArr) {
        if (jpVarArr == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[jpVarArr.length];
        for (int i = 0; i < jpVarArr.length; i++) {
            jp jpVar = jpVarArr[i];
            Bundle bundle = new Bundle();
            bundle.putString("resultKey", jpVar.a);
            bundle.putCharSequence("label", jpVar.b);
            bundle.putCharSequenceArray("choices", jpVar.c);
            bundle.putBoolean("allowFreeFormInput", jpVar.d);
            bundle.putBundle("extras", jpVar.e);
            Set<String> set = jpVar.f;
            if (set != null && !set.isEmpty()) {
                ArrayList arrayList = new ArrayList(set.size());
                for (String add : set) {
                    arrayList.add(add);
                }
                bundle.putStringArrayList("allowedDataTypes", arrayList);
            }
            bundleArr[i] = bundle;
        }
        return bundleArr;
    }

    public static Bundle a(Notification.Builder builder, jm.a aVar) {
        builder.addAction(aVar.e, aVar.f, aVar.g);
        Bundle bundle = new Bundle(aVar.a);
        if (aVar.b != null) {
            bundle.putParcelableArray(NotificationCompatExtras.EXTRA_REMOTE_INPUTS, a(aVar.b));
        }
        if (aVar.c != null) {
            bundle.putParcelableArray("android.support.dataRemoteInputs", a(aVar.c));
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", aVar.d);
        return bundle;
    }
}
