package com.imrezwan.wise_brewer.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.imrezwan.wise_brewer.R;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private List<String> mDataList;
    private int selectedPosition = 0;

    public CustomListAdapter(Context context, List<String> dataList) {
        super(context, 0, dataList);
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.custom_list_item, parent, false);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String dataItem = mDataList.get(position);
        holder.textView.setText(dataItem);

        if (position == selectedPosition) {
            convertView.setBackgroundResource(R.color.colorPrimary);
            holder.textView.setTextColor(ContextCompat.getColor(mContext, R.color.colorWhite));
        } else {
            convertView.setBackgroundResource(R.color.colorWhite);
            holder.textView.setTextColor(ContextCompat.getColor(mContext, R.color.colorBlack));
        }

        convertView.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged();
        });

        return convertView;
    }

    public String getSelectedItem() {
        return mDataList.get(selectedPosition);
    }

    static class ViewHolder {
        TextView textView;
    }
}