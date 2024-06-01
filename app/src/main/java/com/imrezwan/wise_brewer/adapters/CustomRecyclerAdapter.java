package com.imrezwan.wise_brewer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.imrezwan.wise_brewer.R;
import com.imrezwan.wise_brewer.interfaces.OnItemClickListener;

import java.util.List;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {
    private List<String> dataList;
    private Context mContext;
    private int selectedItem = 0;
    private OnItemClickListener onItemClickListener;

    public CustomRecyclerAdapter(Context mContext, List<String> dataList, OnItemClickListener onItemClickListener) {
        this.dataList = dataList;
        this.mContext = mContext;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(dataList.get(position));
        boolean isSelected = selectedItem == holder.getAdapterPosition();
        holder.itemView.setSelected(isSelected);

        if (isSelected) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorPrimary));
            holder.textView.setTextColor(ContextCompat.getColor(mContext, R.color.colorWhite));

        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorWhite));
            holder.textView.setTextColor(ContextCompat.getColor(mContext, R.color.colorBlack));
        }
        holder.itemView.setOnClickListener(v -> {
            updateSelectedItem(holder.getAdapterPosition());
        });

        holder.itemView.setSelected(selectedItem == position);
    }

    private void updateSelectedItem(int currentSelectedItem) {
        int previousSelectedItem = selectedItem;
        selectedItem = currentSelectedItem;

        notifyItemChanged(previousSelectedItem);
        notifyItemChanged(selectedItem);

        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(selectedItem);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public String getSelectedItem() {
        return selectedItem >= 0 ? dataList.get(selectedItem) : null;
    }

    public void setSelectedItem(String item) {
        int position = dataList.indexOf(item);
        updateSelectedItem(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text1);
        }
    }
}
