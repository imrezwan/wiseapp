package com.imrezwan.wise_brewer.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imrezwan.wise_brewer.R;
import com.imrezwan.wise_brewer.adapters.CustomRecyclerAdapter;
import com.imrezwan.wise_brewer.interfaces.OnItemClickListener;

import java.util.List;

public class CustomRecyclerView extends RecyclerView {
    CustomRecyclerAdapter adapter;

    public CustomRecyclerView(Context context) {
        super(context);
        init(null);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setLayoutManager(new LinearLayoutManager(getContext()));

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomRecyclerView);

            int customBackground = a.getResourceId(R.styleable.CustomRecyclerView_customBackground, -1);
            if (customBackground != -1) {
                setBackground(ContextCompat.getDrawable(getContext(), customBackground));
            }

            a.recycle();
        }
    }

    public void setData(List<String> dataList, OnItemClickListener onItemClickListener) {
        adapter = new CustomRecyclerAdapter(getContext(), dataList, onItemClickListener);
        setAdapter(adapter);
    }

    public String getSelectedItemData() {
        return adapter.getSelectedItem();
    }

    public CustomRecyclerAdapter getAdapter() {
        return adapter;
    }
}
