package com.tapdaq.sdk.layout;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class TDImageView extends FrameLayout {
    /* access modifiers changed from: private */
    public String mUrl;

    public TDImageView(@NonNull Context context) {
        super(context);
    }

    public TDImageView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TDImageView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public TDImageView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void setUrl(String str) {
        this.mUrl = str;
        setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TDImageView.this.getContext() != null && TDImageView.this.mUrl != null && !TDImageView.this.mUrl.isEmpty()) {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(TDImageView.this.mUrl));
                    if (intent.resolveActivity(TDImageView.this.getContext().getPackageManager()) != null) {
                        TDImageView.this.getContext().startActivity(intent);
                    }
                }
            }
        });
    }

    public void setDrawable(Drawable drawable) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(drawable);
        addView(imageView);
    }
}
