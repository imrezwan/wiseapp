package com.imrezwan.wise_brewer;

import static com.imrezwan.wise_brewer.utils.Constants.mDataAmountOfWater;
import static com.imrezwan.wise_brewer.utils.Constants.mDataTemperature;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.imrezwan.wise_brewer.fragments.BloomingFragment;
import com.imrezwan.wise_brewer.interfaces.OnItemClickListener;
import com.imrezwan.wise_brewer.utils.FragmentHandler;
import com.imrezwan.wise_brewer.widgets.CustomRecyclerView;

public class ProfileSetupFragment extends Fragment {
    CustomRecyclerView mCrvAmountOfWater, mCrvTemperature;
    NestedScrollView nestedScrollView;
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

        nestedScrollView.setNestedScrollingEnabled(true);

        mNext.setOnClickListener(view1 ->
            FragmentHandler.replaceFragment(getActivity(), new BloomingFragment(), "blooming")
        );
        return view;
    }

    private void initialize(View view) {
        mCrvAmountOfWater = view.findViewById(R.id.crv_amount_of_water);
        mCrvTemperature = view.findViewById(R.id.crv_temperature);
        nestedScrollView = view.findViewById(R.id.nested_scrollview);
        mNext = view.findViewById(R.id.tv_next);

        mCrvAmountOfWater.setData(mDataAmountOfWater, new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("TAGGG", "Water: " + mCrvAmountOfWater.getSelectedItemData() + " -> Clicked");
            }
        });
        mCrvTemperature.setData(mDataTemperature, new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("TAGGG", "Temp: " + mCrvTemperature.getSelectedItemData() + " -> Clicked");
            }
        });
    }
}