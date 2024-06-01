package com.imrezwan.wise_brewer;

import static com.imrezwan.wise_brewer.utils.Constants.mDataAmountOfWater;
import static com.imrezwan.wise_brewer.utils.Constants.mDataTemperature;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.imrezwan.wise_brewer.fragments.BloomingFragment;
import com.imrezwan.wise_brewer.fragments.Extraction1Fragment;
import com.imrezwan.wise_brewer.interfaces.OnItemClickListener;
import com.imrezwan.wise_brewer.utils.FragmentHandler;
import com.imrezwan.wise_brewer.view_models.ProfileCreationViewModel;
import com.imrezwan.wise_brewer.widgets.CustomRecyclerView;

public class ProfileSetupFragment extends Fragment {
    ProfileCreationViewModel profileCreationViewModel;
    CustomRecyclerView mCrvAmountOfWater, mCrvTemperature;
    NestedScrollView nestedScrollView;

    CheckBox mIsBlooming;
    TextView mNext;

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

        restoreValues();

        nestedScrollView.setNestedScrollingEnabled(true);

        mNext.setOnClickListener(view1 -> {
            if( mIsBlooming.isChecked()) {
                FragmentHandler.replaceFragment(getActivity(), BloomingFragment.newInstance(), "blooming");
            }
            else {
                FragmentHandler.replaceFragment(getActivity(), Extraction1Fragment.newInstance(), "extraction1");
            }
        });
        return view;
    }

    private void restoreValues() {
        int prevAmountOfWater = profileCreationViewModel.getBaseAmountOfWater();
        if(prevAmountOfWater > 0 ) {
            mCrvAmountOfWater.setSelectedItem(Integer.toString(prevAmountOfWater));
        }

        int prevTemperature = profileCreationViewModel.getBaseTemperature();
        if(prevTemperature > 0 ) {
            mCrvTemperature.setSelectedItem(profileCreationViewModel.getBaseTemperatureString());
        }

        boolean isBlooming = profileCreationViewModel.isBloomFlag();
        mIsBlooming.setChecked(isBlooming);
    }

    private void initialize(View view) {
        mCrvAmountOfWater = view.findViewById(R.id.crv_amount_of_water);
        mCrvTemperature = view.findViewById(R.id.crv_temperature);
        nestedScrollView = view.findViewById(R.id.nested_scrollview);
        mIsBlooming = view.findViewById(R.id.cb_is_blooming);
        mNext = view.findViewById(R.id.tv_next);
        profileCreationViewModel = new ViewModelProvider(requireActivity()).get(ProfileCreationViewModel.class);

        mCrvAmountOfWater.setData(mDataAmountOfWater, new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("TAGGG", "Water: " + mCrvAmountOfWater.getSelectedItemData() + " -> Clicked");
                profileCreationViewModel.setBaseAmountOfWater(Integer.parseInt(mCrvAmountOfWater.getSelectedItemData()));
            }
        });
        mCrvTemperature.setData(mDataTemperature, new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("TAGGG", "Temp: " + mCrvTemperature.getSelectedItemData() + " -> Clicked");
                profileCreationViewModel.setBaseTemperature(mCrvTemperature.getSelectedItemData());
            }
        });

        mIsBlooming.setOnCheckedChangeListener((compoundButton, b) -> {
            profileCreationViewModel.setBloomFlag(b);
        });
    }
}