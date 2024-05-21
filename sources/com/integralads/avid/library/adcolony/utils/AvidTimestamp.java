package com.integralads.avid.library.adcolony.utils;

public class AvidTimestamp {
    private static double a = 1000000.0d;

    public static double getCurrentTime() {
        double nanoTime = (double) System.nanoTime();
        double d = a;
        Double.isNaN(nanoTime);
        return nanoTime / d;
    }
}
