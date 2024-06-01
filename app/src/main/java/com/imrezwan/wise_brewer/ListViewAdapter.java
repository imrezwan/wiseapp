package com.imrezwan.wise_brewer;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imrezwan.wise_brewer.databinding.FragmentProfilesBinding;
import com.imrezwan.wise_brewer.interfaces.IOnProfileEditListener;
import com.imrezwan.wise_brewer.interfaces.OnItemClickListener;
import com.imrezwan.wise_brewer.models.ProfileFactory.ProfileInfo;

import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {

    private List<ProfileInfo> mValues;
    private IOnProfileEditListener mListener;

    public ListViewAdapter(List<ProfileInfo> items,IOnProfileEditListener mListener) {
        mValues = items;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentProfilesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    public void updateData(List<ProfileInfo> items) {
        this.mValues = items;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getDisplayTitle());
        holder.mEditProfileName.setOnClickListener(view -> mListener.onEditButtonClicked(mValues.get(position)));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mContentView;
        public final ImageView mEditProfileName;
        public ProfileInfo mItem;

        public ViewHolder(FragmentProfilesBinding binding) {
            super(binding.getRoot());
            mContentView = binding.profileName;
            mEditProfileName = binding.ivEditProfileName;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}