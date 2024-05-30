package com.imrezwan.wise_brewer;

import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.imrezwan.wise_brewer.models.ProfileFactory;
import com.imrezwan.wise_brewer.utils.FragmentHandler;
import com.imrezwan.wise_brewer.view_models.ProfileCreationViewModel;

public class ProfilesFragment extends Fragment {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    public ProfilesFragment() {
    }

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
        CardView mNewProfileBtn = view.findViewById(R.id.card_new_profile_btn);
        mNewProfileBtn.setOnClickListener(view1 ->
                FragmentHandler.replaceFragment(getActivity(), new ProfileSetupFragment(), "profilesetup")
        );

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