package com.tapdaq.sdk.debug;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.tapdaq.sdk.layout.LogItemLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TMDebugAdapter extends ArrayAdapter<String> {
    private static String DATE_FORMAT = "dd-MM HH:mm:ss";
    private Context mContext;

    private static class ViewHolder {
        TextView mTextView;

        private ViewHolder() {
        }
    }

    public TMDebugAdapter(Context context, ArrayList<String> arrayList) {
        super(context, 0, arrayList);
        this.mContext = context;
    }

    public void insert(String str, int i) {
        super.insert(String.format(Locale.getDefault(), "%s: %s", new Object[]{DateFormat.format(DATE_FORMAT, new Date()), str}), i);
    }

    @NonNull
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = new LogItemLayout(this.mContext);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = ((LogItemLayout) view).getTextView();
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (getItem(i) != null) {
            viewHolder.mTextView.setText((CharSequence) getItem(i));
        }
        return view;
    }

    public List<String> getItems() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getCount(); i++) {
            arrayList.add(getItem(i));
        }
        return arrayList;
    }
}
