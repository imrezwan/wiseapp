package com.imrezwan.wise_brewer;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.imrezwan.wise_brewer.models.ProfileFactory;

/**
 * A fragment representing a list of Items.
 */
public class ProfilesFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProfilesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ProfilesFragment newInstance(int columnCount) {
        ProfilesFragment fragment = new ProfilesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_list, container, false);

        RecyclerView rvProfileList = view.findViewById(R.id.rv_profile_list);
        if (rvProfileList instanceof RecyclerView) {
            Context context = view.getContext();
            if (mColumnCount <= 1) {
                rvProfileList.setLayoutManager(new LinearLayoutManager(context));
            } else {
                rvProfileList.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            rvProfileList.setAdapter(new ListViewAdapter(ProfileFactory.ITEMS));
        }
        return view;
    }
}