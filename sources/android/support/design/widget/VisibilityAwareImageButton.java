package android.support.design.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

class VisibilityAwareImageButton extends ImageButton {
    private int mUserSetVisibility;

    public VisibilityAwareImageButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mUserSetVisibility = getVisibility();
    }

    public void setVisibility(int i) {
        internalSetVisibility(i, true);
    }

    /* access modifiers changed from: package-private */
    public final void internalSetVisibility(int i, boolean z) {
        super.setVisibility(i);
        if (z) {
            this.mUserSetVisibility = i;
        }
    }

    /* access modifiers changed from: package-private */
    public final int getUserSetVisibility() {
        return this.mUserSetVisibility;
    }
}
