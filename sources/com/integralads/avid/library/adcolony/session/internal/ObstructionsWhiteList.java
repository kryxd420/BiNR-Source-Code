package com.integralads.avid.library.adcolony.session.internal;

import android.view.View;
import com.integralads.avid.library.adcolony.weakreference.AvidView;
import java.util.ArrayList;
import java.util.Iterator;

public class ObstructionsWhiteList {
    private final ArrayList<AvidView> a = new ArrayList<>();

    public void add(View view) {
        this.a.add(new AvidView(view));
    }

    public boolean contains(View view) {
        Iterator<AvidView> it = this.a.iterator();
        while (it.hasNext()) {
            if (it.next().contains(view)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<AvidView> getWhiteList() {
        return this.a;
    }
}
