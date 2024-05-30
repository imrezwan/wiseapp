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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.imrezwan.wise_brewer.R;
import com.imrezwan.wise_brewer.widgets.CustomRecyclerView;
import com.imrezwan.wise_brewer.widgets.SubtitleView;

public abstract class BaseExtractionFragment extends Fragment {
    CustomRecyclerView mExtractionWater, mExtractionSpeed, mExtractionPause;
    protected TextView mNext, mPrev;

    SubtitleView mSubtitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);

        initialize(view);

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

    protected abstract int getLayoutResId();
    protected abstract void onNextButtonClicked();
    protected abstract void onPrevButtonClicked();
    protected abstract String getSubtitle();
    protected abstract void onItemClickWater();
    protected abstract void onItemClickSpeed();
    protected abstract void onItemClickPause();
}

