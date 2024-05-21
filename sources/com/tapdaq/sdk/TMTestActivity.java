package com.tapdaq.sdk;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.FrameLayout;
import com.tapdaq.sdk.debug.TMDebuggerOptionsFragment;
import com.tapdaq.sdk.helpers.Utils;
import java.util.Locale;

public class TMTestActivity extends Activity {
    private FrameLayout layout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 21) {
            setTheme(16974391);
        } else if (Build.VERSION.SDK_INT >= 11) {
            setTheme(16973934);
        } else {
            setTheme(16973836);
        }
        super.onCreate(bundle);
        this.layout = new FrameLayout(this);
        this.layout.setId(Utils.generateViewId());
        this.layout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        setContentView(this.layout);
        String format = String.format(Locale.ENGLISH, "Debug (%s)", new Object[]{BuildConfig.SDK_IDENTIFIER});
        SpannableString spannableString = new SpannableString(format);
        spannableString.setSpan(new ForegroundColorSpan(-1), 0, format.length(), 33);
        if (Build.VERSION.SDK_INT >= 21 && getActionBar() != null) {
            getActionBar().hide();
        }
        if (Build.VERSION.SDK_INT > 11 && getActionBar() != null) {
            getActionBar().setBackgroundDrawable(new ColorDrawable(ViewCompat.MEASURED_STATE_MASK));
            getActionBar().setTitle(spannableString);
        }
        setTitle("Debug View");
        if (bundle == null) {
            showFragment(new TMDebuggerOptionsFragment());
        }
    }

    public void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            beginTransaction.replace(this.layout.getId(), fragment);
        } else {
            beginTransaction.add(this.layout.getId(), fragment, fragment.getClass().getName());
        }
        beginTransaction.addToBackStack((String) null);
        beginTransaction.commit();
    }

    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Tapdaq.getInstance().onResume(this);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        Tapdaq.getInstance().onPause(this);
    }
}
