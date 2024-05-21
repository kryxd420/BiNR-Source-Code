package com.tapdaq.sdk.debug;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.tapdaq.sdk.TMTestActivity;

public class TMDebuggerOptionsFragment extends Fragment {
    private LinearLayout mLayout;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        this.mLayout = new LinearLayout(getActivity());
        this.mLayout.setOrientation(1);
        this.mLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        Button button = new Button(getActivity());
        button.setText("Mediate");
        this.mLayout.addView(button);
        Button button2 = new Button(getActivity());
        button2.setText("Adapters");
        this.mLayout.addView(button2);
        button.setOnClickListener(new OnClickMediate());
        button2.setOnClickListener(new OnClickAdapters());
        return this.mLayout;
    }

    private class OnClickMediate implements View.OnClickListener {
        private OnClickMediate() {
        }

        public void onClick(View view) {
            Activity activity = TMDebuggerOptionsFragment.this.getActivity();
            if (activity instanceof TMTestActivity) {
                ((TMTestActivity) activity).showFragment(new TMDebuggerMediateFragment());
            }
        }
    }

    private class OnClickAdapters implements View.OnClickListener {
        private OnClickAdapters() {
        }

        public void onClick(View view) {
            Activity activity = TMDebuggerOptionsFragment.this.getActivity();
            if (activity instanceof TMTestActivity) {
                ((TMTestActivity) activity).showFragment(new TMDebuggerAdapterListFragment());
            }
        }
    }
}
