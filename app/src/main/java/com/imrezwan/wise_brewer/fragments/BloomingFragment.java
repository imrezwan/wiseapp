package com.imrezwan.wise_brewer.fragments;

import static com.imrezwan.wise_brewer.utils.Constants.mDataPause;
import static com.imrezwan.wise_brewer.utils.Constants.mDataSpeed;
import static com.imrezwan.wise_brewer.utils.Constants.mDataWater;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.imrezwan.wise_brewer.R;
import com.imrezwan.wise_brewer.utils.FragmentHandler;
import com.imrezwan.wise_brewer.widgets.CustomRecyclerView;

public class BloomingFragment extends Fragment {
    CustomRecyclerView mBloomingWater, mBloomingSpeed, mBloomingPause;

    TextView mNext;

    public BloomingFragment() {
        // Required empty public constructor
    }

    public static BloomingFragment newInstance() {
        BloomingFragment fragment = new BloomingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blooming, container, false);
        initialize(view);

        mNext.setOnClickListener(view1 ->
                FragmentHandler.replaceFragment(getActivity(), Extraction1Fragment.newInstance(), "extraction1")
        );

        return view;
    }

    private void initialize(View view) {
        mBloomingWater = view.findViewById(R.id.crv_extraction_water);
        mBloomingSpeed = view.findViewById(R.id.crv_extraction_speed);
        mBloomingPause = view.findViewById(R.id.crv_extraction_pause);

        mNext = view.findViewById(R.id.tv_next);

        mBloomingWater.setData(mDataWater, position -> Log.d("TAGGG", "Water: " + mBloomingWater.getSelectedItemData() + " -> Clicked"));
        mBloomingSpeed.setData(mDataSpeed, position -> Log.d("TAGGG", "Speed: " + mBloomingSpeed.getSelectedItemData() + " -> Clicked"));
        mBloomingPause.setData(mDataPause, position -> Log.d("TAGGG", "Pause: " + mBloomingPause.getSelectedItemData() + " -> Clicked"));
    }
}