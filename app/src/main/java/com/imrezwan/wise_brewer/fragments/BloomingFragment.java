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
import androidx.lifecycle.ViewModelProvider;

import com.imrezwan.wise_brewer.R;
import com.imrezwan.wise_brewer.utils.FragmentHandler;
import com.imrezwan.wise_brewer.view_models.ProfileCreationViewModel;
import com.imrezwan.wise_brewer.widgets.CustomRecyclerView;

public class BloomingFragment extends Fragment {
    ProfileCreationViewModel profileCreationViewModel;
    CustomRecyclerView mBloomingWater, mBloomingSpeed, mBloomingPause;

    TextView mNext, mBack;

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

        restoreValues();

        mNext.setOnClickListener(view1 ->
                FragmentHandler.replaceFragment(getActivity(), Extraction1Fragment.newInstance(), "extraction1")
        );
        mBack.setOnClickListener(v -> requireActivity().getOnBackPressedDispatcher().onBackPressed());

        return view;
    }

    private void restoreValues() {
        int prevBloomWater = profileCreationViewModel.getBloomWater();
        if (prevBloomWater > 0 ) {
            mBloomingWater.setSelectedItem(Integer.toString(prevBloomWater));
        }

        int prevBloomSpeed = profileCreationViewModel.getBloomSpeed();
        if (prevBloomSpeed > 0 ) {
            mBloomingSpeed.setSelectedItem(Integer.toString(prevBloomSpeed));
        }

        int prevBloomPause = profileCreationViewModel.getBloomPause();
        if (prevBloomPause > 0 ) {
            mBloomingPause.setSelectedItem(Integer.toString(prevBloomPause));
        }
    }

    private void initialize(View view) {
        mBloomingWater = view.findViewById(R.id.crv_extraction_water);
        mBloomingSpeed = view.findViewById(R.id.crv_extraction_speed);
        mBloomingPause = view.findViewById(R.id.crv_extraction_pause);
        profileCreationViewModel = new ViewModelProvider(requireActivity()).get(ProfileCreationViewModel.class);

        mNext = view.findViewById(R.id.tv_next);
        mBack = view.findViewById(R.id.tv_back);

        mBloomingWater.setData(mDataWater, position -> {
            Log.d("TAGGG", "Water: " + mBloomingWater.getSelectedItemData() + " -> Clicked");
            profileCreationViewModel.setBloomWater(Integer.parseInt(mBloomingWater.getSelectedItemData()));
        });
        mBloomingSpeed.setData(mDataSpeed, position -> {
            Log.d("TAGGG", "Speed: " + mBloomingSpeed.getSelectedItemData() + " -> Clicked");
            profileCreationViewModel.setBloomSpeed(Integer.parseInt(mBloomingSpeed.getSelectedItemData()));
        });
        mBloomingPause.setData(mDataPause, position -> {
            Log.d("TAGGG", "Pause: " + mBloomingPause.getSelectedItemData() + " -> Clicked");
            profileCreationViewModel.setBloomPause(Integer.parseInt(mBloomingPause.getSelectedItemData()));
        });
    }
}