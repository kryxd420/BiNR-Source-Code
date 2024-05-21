package com.tapdaq.sdk.debug;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import com.tapdaq.sdk.TMTestActivity;
import com.tapdaq.sdk.adnetworks.TDMediationServiceProvider;
import com.tapdaq.sdk.adnetworks.TMAdapterRegistry;
import com.tapdaq.sdk.adnetworks.TMMediationNetworks;
import com.tapdaq.sdk.common.TMAdapter;
import com.tapdaq.sdk.helpers.TLog;
import java.util.ArrayList;
import java.util.List;

public class TMDebuggerAdapterListFragment extends Fragment {
    /* access modifiers changed from: private */
    public List<TMAdapter> mAdapters = new ArrayList();
    /* access modifiers changed from: private */
    public ListView mServiceListView;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        FrameLayout frameLayout = new FrameLayout(getActivity());
        this.mServiceListView = new ListView(getActivity());
        this.mServiceListView.setAdapter(new TMServiceAdapter(getActivity(), this.mAdapters, new OnClickAdapter()));
        frameLayout.addView(this.mServiceListView);
        return frameLayout;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        for (int i = 0; i < TMMediationNetworks.getTotalNetworks(); i++) {
            TMAdapter adapter = TDMediationServiceProvider.getMediationService().getAdapter(i);
            if (adapter == null) {
                adapter = TMAdapterRegistry.GetAdapter(TMMediationNetworks.getName(i));
                if (adapter != null) {
                    adapter.isAdapterVersionCorrect();
                } else {
                    adapter = new TMAdapter(i);
                }
            }
            this.mAdapters.add(adapter);
        }
    }

    private class OnClickAdapter implements View.OnClickListener {
        private OnClickAdapter() {
        }

        public void onClick(View view) {
            TMAdapter tMAdapter;
            int positionForView = TMDebuggerAdapterListFragment.this.mServiceListView.getPositionForView(view);
            if (positionForView >= 0 && (tMAdapter = (TMAdapter) TMDebuggerAdapterListFragment.this.mAdapters.get(positionForView)) != null) {
                TLog.debug("Click " + tMAdapter.getName());
                Activity activity = TMDebuggerAdapterListFragment.this.getActivity();
                if (activity instanceof TMTestActivity) {
                    TMDebuggerAdapterFragment tMDebuggerAdapterFragment = new TMDebuggerAdapterFragment();
                    tMDebuggerAdapterFragment.setAdapter(tMAdapter);
                    ((TMTestActivity) activity).showFragment(tMDebuggerAdapterFragment);
                }
            }
        }
    }
}
