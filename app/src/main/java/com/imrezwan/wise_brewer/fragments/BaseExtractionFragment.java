package com.imrezwan.wise_brewer.fragments;

import static com.imrezwan.wise_brewer.utils.Constants.mDataPause;
import static com.imrezwan.wise_brewer.utils.Constants.mDataSpeed;
import static com.imrezwan.wise_brewer.utils.Constants.mDataWater;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.imrezwan.wise_brewer.R;
import com.imrezwan.wise_brewer.view_models.ProfileCreationViewModel;
import com.imrezwan.wise_brewer.widgets.CustomRecyclerView;
import com.imrezwan.wise_brewer.widgets.SubtitleView;

public abstract class BaseExtractionFragment extends Fragment {
    ProfileCreationViewModel profileCreationViewModel;
    CustomRecyclerView mExtractionWater, mExtractionSpeed, mExtractionPause;
    protected TextView mNext, mPrev;

    SubtitleView mSubtitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);

        initialize(view);
        restoreValues();

        mNext.setOnClickListener(v -> onNextButtonClicked());
        mPrev.setOnClickListener(v -> onPrevButtonClicked());

        mSubtitle.setText(getSubtitle());

        return view;
    }

    private void initialize(View view) {
        mSubtitle = view.findViewById(R.id.subtitle);
        mExtractionWater = view.findViewById(R.id.crv_extraction_water);
        mExtractionSpeed = view.findViewById(R.id.crv_extraction_speed);
        mExtractionPause = view.findViewById(R.id.crv_extraction_pause);

        mNext = view.findViewById(R.id.tv_next);
        mPrev = view.findViewById(R.id.tv_back);

        profileCreationViewModel = new ViewModelProvider(requireActivity()).get(ProfileCreationViewModel.class);

        mExtractionWater.setData(mDataWater, position -> {
            onItemClickWater();
        });
        mExtractionSpeed.setData(mDataSpeed, position -> {
            onItemClickSpeed();
        });
        mExtractionPause.setData(mDataPause, position -> {
            onItemClickPause();
        });
    }

    private void restoreValues() {
        restoreExtractionWater();
        restoreExtractionSpeed();
        restoreExtractionPause();
    }

    private void restoreExtractionWater() {
        int prevWater = getExtractionWater();
        if (prevWater > 0 ) {
            mExtractionWater.setSelectedItem(Integer.toString(prevWater));
        }
    }

    private void restoreExtractionSpeed() {
        int prevSpeed = getExtractionSpeed();
        if (prevSpeed > 0 ) {
            mExtractionSpeed.setSelectedItem(Integer.toString(prevSpeed));
        }
    }

    private void restoreExtractionPause() {
        int prevPause = getExtractionPause();
        if (prevPause > 0 ) {
            mExtractionPause.setSelectedItem(Integer.toString(prevPause));
        }
    }

    private void onPrevButtonClicked() {
        requireActivity().getOnBackPressedDispatcher().onBackPressed();
    }

    protected abstract void setExtractionFlag();

    protected abstract int getExtractionWater();
    protected abstract int getExtractionSpeed();
    protected abstract int getExtractionPause();

    protected abstract int getLayoutResId();
    protected abstract void onNextButtonClicked();
    protected abstract String getSubtitle();
    protected abstract void onItemClickWater();
    protected abstract void onItemClickSpeed();
    protected abstract void onItemClickPause();
}

