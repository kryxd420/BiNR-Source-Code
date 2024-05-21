package com.tapjoy.internal;

public enum ek {
    VARINT(0),
    FIXED64(1),
    LENGTH_DELIMITED(2),
    FIXED32(5);
    
    final int e;

    private ek(int i) {
        this.e = i;
    }

    public final en a() {
        switch (this) {
            case VARINT:
                return en.j;
            case FIXED32:
                return en.g;
            case FIXED64:
                return en.l;
            case LENGTH_DELIMITED:
                return en.q;
            default:
                throw new AssertionError();
        }
    }
}
