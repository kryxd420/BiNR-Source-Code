package com.tapjoy.internal;

import android.app.RemoteInput;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import java.util.Set;

public final class jp {
    final String a;
    final CharSequence b;
    final CharSequence[] c;
    final boolean d;
    final Bundle e;
    final Set f;

    @RequiresApi(20)
    static RemoteInput[] a(jp[] jpVarArr) {
        if (jpVarArr == null) {
            return null;
        }
        RemoteInput[] remoteInputArr = new RemoteInput[jpVarArr.length];
        for (int i = 0; i < jpVarArr.length; i++) {
            jp jpVar = jpVarArr[i];
            remoteInputArr[i] = new RemoteInput.Builder(jpVar.a).setLabel(jpVar.b).setChoices(jpVar.c).setAllowFreeFormInput(jpVar.d).addExtras(jpVar.e).build();
        }
        return remoteInputArr;
    }
}
