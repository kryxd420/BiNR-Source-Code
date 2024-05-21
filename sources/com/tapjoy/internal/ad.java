package com.tapjoy.internal;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

public enum ad {
    UNSPECIFIED,
    PORTRAIT,
    LANDSCAPE,
    SQUARE,
    NATURAL_PORTRAIT(PORTRAIT),
    RIGHT_LANDSCAPE(LANDSCAPE, NATURAL_PORTRAIT),
    REVERSE_PORTRAIT(PORTRAIT, NATURAL_PORTRAIT),
    LEFT_LANDSCAPE(LANDSCAPE, NATURAL_PORTRAIT),
    NATURAL_LANDSCAPE(LANDSCAPE),
    RIGHT_PORTRAIT(PORTRAIT, NATURAL_LANDSCAPE),
    REVERSE_LANDSCAPE(LANDSCAPE, NATURAL_LANDSCAPE),
    LEFT_PORTRAIT(PORTRAIT, NATURAL_LANDSCAPE),
    NATURAL_SQUARE(SQUARE),
    RIGHT_SQUARE(SQUARE, NATURAL_SQUARE),
    REVERSE_SQUARE(SQUARE, NATURAL_SQUARE),
    LEFT_SQUARE(SQUARE, NATURAL_SQUARE);
    
    private final ad q;
    private final ad r;

    private ad(ad adVar) {
        this.q = adVar;
        this.r = this;
    }

    private ad(ad adVar, ad adVar2) {
        this.q = adVar;
        this.r = adVar2;
    }

    public final boolean a() {
        return this == PORTRAIT || this.q == PORTRAIT;
    }

    public final boolean b() {
        return this == LANDSCAPE || this.q == LANDSCAPE;
    }

    public final int c() {
        if (this.r != null) {
            return ordinal() - this.r.ordinal();
        }
        return 0;
    }

    public static ad a(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        int rotation = defaultDisplay.getRotation();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 13) {
            defaultDisplay.getSize(point);
        } else {
            point.x = defaultDisplay.getWidth();
            point.y = defaultDisplay.getHeight();
        }
        if (point.x < point.y) {
            switch (rotation & 3) {
                case 1:
                    return RIGHT_PORTRAIT;
                case 2:
                    return REVERSE_PORTRAIT;
                case 3:
                    return LEFT_PORTRAIT;
                default:
                    return NATURAL_PORTRAIT;
            }
        } else if (point.x > point.y) {
            switch (rotation & 3) {
                case 1:
                    return RIGHT_LANDSCAPE;
                case 2:
                    return REVERSE_LANDSCAPE;
                case 3:
                    return LEFT_LANDSCAPE;
                default:
                    return NATURAL_LANDSCAPE;
            }
        } else {
            switch (rotation & 3) {
                case 1:
                    return RIGHT_SQUARE;
                case 2:
                    return REVERSE_SQUARE;
                case 3:
                    return LEFT_SQUARE;
                default:
                    return NATURAL_SQUARE;
            }
        }
    }

    public static ad b(Context context) {
        switch (context.getResources().getConfiguration().orientation) {
            case 1:
                return PORTRAIT;
            case 2:
                return LANDSCAPE;
            default:
                return UNSPECIFIED;
        }
    }
}
