package com.tapjoy.internal;

public enum fc implements eq {
    APP(0),
    CAMPAIGN(1),
    CUSTOM(2),
    USAGES(3);
    
    public static final en ADAPTER = null;
    private final int a;

    static {
        ADAPTER = new a();
    }

    private fc(int i) {
        this.a = i;
    }

    public static fc a(int i) {
        switch (i) {
            case 0:
                return APP;
            case 1:
                return CAMPAIGN;
            case 2:
                return CUSTOM;
            case 3:
                return USAGES;
            default:
                return null;
        }
    }

    public final int a() {
        return this.a;
    }

    static final class a extends ej {
        a() {
            super(fc.class);
        }

        /* access modifiers changed from: protected */
        public final /* bridge */ /* synthetic */ eq a(int i) {
            return fc.a(i);
        }
    }
}
