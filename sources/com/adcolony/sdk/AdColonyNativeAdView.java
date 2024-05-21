package com.adcolony.sdk;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.adcolony.sdk.aa;
import org.json.JSONObject;

public class AdColonyNativeAdView extends az {
    private EngagementButton c;
    private boolean d;
    private String e;
    private String f;
    private String g;

    public /* bridge */ /* synthetic */ boolean destroy() {
        return super.destroy();
    }

    public /* bridge */ /* synthetic */ String getZoneID() {
        return super.getZoneID();
    }

    public /* bridge */ /* synthetic */ boolean pause() {
        return super.pause();
    }

    public /* bridge */ /* synthetic */ boolean resume() {
        return super.resume();
    }

    public /* bridge */ /* synthetic */ boolean setMuted(boolean z) {
        return super.setMuted(z);
    }

    public /* bridge */ /* synthetic */ boolean setVolume(float f2) {
        return super.setVolume(f2);
    }

    AdColonyNativeAdView(Context context, af afVar, e eVar) {
        super(context, afVar, eVar);
        JSONObject c2 = afVar.c();
        setNative(true);
        this.d = y.d(c2, "engagement_enabled");
        this.e = y.b(c2, "engagement_click_action");
        this.f = y.b(c2, "engagement_click_action_type");
        this.g = y.b(c2, "engagement_text");
        if (this.d) {
            this.c = new EngagementButton(context);
            this.c.setText(this.g);
            this.c.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (AdColonyNativeAdView.this.c()) {
                        new aa.a().a("Ignoring engagement click as view has been destroyed.").a(aa.e);
                        return;
                    }
                    JSONObject a2 = y.a();
                    y.a(a2, "id", AdColonyNativeAdView.this.getAdSessionId());
                    new af("AdSession.on_native_engagement", AdColonyNativeAdView.this.getContainer().c(), a2).b();
                }
            });
        }
    }

    public String getAdvertiserName() {
        if (!c()) {
            return super.getAdvertiserName();
        }
        new aa.a().a("Ignoring call to getAdvertiserName() as view has been destroyed").a(aa.e);
        return "";
    }

    public ImageView getIcon() {
        ImageView icon = super.getIcon();
        if (icon == null) {
            return null;
        }
        if (!c()) {
            return icon;
        }
        new aa.a().a("Ignoring call to getIcon() as view has been destroyed").a(aa.e);
        return null;
    }

    public String getTitle() {
        if (!c()) {
            return super.getTitle();
        }
        new aa.a().a("Ignoring call to getTitle() as view has been destroyed").a(aa.e);
        return "";
    }

    public String getDescription() {
        if (!c()) {
            return super.getDescription();
        }
        new aa.a().a("Ignoring call to getDescription() as view has been destroyed").a(aa.e);
        return "";
    }

    public boolean isEngagementEnabled() {
        if (!c()) {
            return this.d;
        }
        new aa.a().a("Ignoring call to isEngagementEnabled() as view has been destroyed").a(aa.e);
        return false;
    }

    public EngagementButton getEngagementButton() {
        if (!c()) {
            return this.c;
        }
        new aa.a().a("Ignoring call to getEngagementButton() as view has been destroyed").a(aa.e);
        return null;
    }

    public class EngagementButton extends Button {
        boolean a;
        View.OnClickListener b;

        EngagementButton(Context context) {
            super(context);
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            if (!this.a) {
                super.setOnClickListener(onClickListener);
                this.b = onClickListener;
                this.a = true;
            }
        }

        public View.OnClickListener getOnClickListener() {
            return this.b;
        }
    }
}
