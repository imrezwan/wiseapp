package com.imrezwan.wise_brewer;

import static com.imrezwan.wise_brewer.utils.Constants.mDataAmountOfWater;
import static com.imrezwan.wise_brewer.utils.Constants.mDataTemperature;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.imrezwan.wise_brewer.widgets.CustomListView;

public class ProfileSetupFragment extends Fragment {
    CustomListView mAmountOfWater, mTemperature;

    public ProfileSetupFragment() {
    }

    public static ProfileSetupFragment newInstance() {
        ProfileSetupFragment fragment = new ProfileSetupFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_setup, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        mAmountOfWater = view.findViewById(R.id.clv_amount_of_water);
        mTemperature = view.findViewById(R.id.clv_temperature);

        mAmountOfWater.setData(mDataAmountOfWater);
        mTemperature.setData(mDataTemperature);
    }
}