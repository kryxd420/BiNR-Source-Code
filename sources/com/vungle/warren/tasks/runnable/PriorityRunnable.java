package com.vungle.warren.tasks.runnable;

import android.support.annotation.NonNull;

public abstract class PriorityRunnable implements Comparable, Runnable {
    public abstract Integer getPriority();

    public int compareTo(@NonNull Object obj) {
        if (!(obj instanceof PriorityRunnable)) {
            return -1;
        }
        return ((PriorityRunnable) obj).getPriority().compareTo(getPriority());
    }
}
