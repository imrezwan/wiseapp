package com.imrezwan.wise_brewer.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ListView;
import androidx.core.content.ContextCompat;

import com.imrezwan.wise_brewer.R;
import com.imrezwan.wise_brewer.adapters.CustomListAdapter;

import java.util.List;

public class CustomListView extends ListView {
    CustomListAdapter adapter;

    public CustomListView(Context context) {
        super(context);
        init(null);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomListView);

            int customBackground = a.getResourceId(R.styleable.CustomListView_customBackground, -1);
            if (customBackground != -1) {
                setBackground(ContextCompat.getDrawable(getContext(), customBackground));
            }

            a.recycle();
        }
    }

    // Method to set data to the ListView using a custom adapter
    public void setData(List<String> dataList) {
        adapter = new CustomListAdapter(getContext(), dataList);
        setAdapter(adapter);
    }

    public String getSelectedItemData() {
        return adapter.getSelectedItem();
    }

    public CustomListAdapter getAdapter() {
        return adapter;
    }
}

