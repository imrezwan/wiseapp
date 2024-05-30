package com.imrezwan.wise_brewer.fragments;

import android.os.Bundle;

import com.imrezwan.wise_brewer.R;

public class Extraction5Fragment  extends BaseExtractionFragment {

    public Extraction5Fragment() {
        // Required empty public constructor
    }

    public static Extraction5Fragment newInstance() {
        Extraction5Fragment fragment = new Extraction5Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_extraction;
    }

    @Override
    protected void onNextButtonClicked() {

    }

    @Override
    protected void onPrevButtonClicked() {

    }

    @Override
    protected String getSubtitle() {
        return "Extraction #05";
    }

    @Override
    protected void onItemClickWater() {

    }

    @Override
    protected void onItemClickSpeed() {

    }

    @Override
    protected void onItemClickPause() {

    }
}
