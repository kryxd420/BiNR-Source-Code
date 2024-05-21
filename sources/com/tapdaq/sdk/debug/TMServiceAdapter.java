package com.tapdaq.sdk.debug;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.internal.view.SupportMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.tapdaq.sdk.common.TMAdapter;
import com.tapdaq.sdk.layout.ServiceItemLayout;
import java.util.List;

public class TMServiceAdapter extends ArrayAdapter<TMAdapter> {
    private Context mContext;
    private View.OnClickListener mListener;

    private static class ViewHolder {
        TextView mTextView;

        private ViewHolder() {
        }
    }

    public TMServiceAdapter(Context context, List<TMAdapter> list, View.OnClickListener onClickListener) {
        super(context, 0, list);
        this.mContext = context;
        this.mListener = onClickListener;
    }

    @NonNull
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = new ServiceItemLayout(this.mContext);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = ((ServiceItemLayout) view).getTextView();
            view.setTag(viewHolder);
            view.setOnClickListener(this.mListener);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (getItem(i) != null) {
            TMAdapter tMAdapter = (TMAdapter) getItem(i);
            viewHolder.mTextView.setText(tMAdapter.getName());
            if (tMAdapter.isInitialised(viewHolder.mTextView.getContext())) {
                viewHolder.mTextView.setBackgroundColor(-16711936);
            } else {
                viewHolder.mTextView.setBackgroundColor(SupportMenu.CATEGORY_MASK);
            }
        }
        return view;
    }
}
