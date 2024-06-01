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
    protected void setExtractionFlag() {
        profileCreationViewModel.setExtraction5Flag(true);
    }

    @Override
    protected int getExtractionWater() {
        return profileCreationViewModel.getExtraction5Water();
    }

    @Override
    protected int getExtractionSpeed() {
        return profileCreationViewModel.getExtraction5Speed();
    }

    @Override
    protected int getExtractionPause() {
        return profileCreationViewModel.getExtraction5Pause();
    }

    @Override
    protected String getSubtitle() {
        return "Extraction #05";
    }

    @Override
    protected void onItemClickWater() {
        profileCreationViewModel.setExtraction5Water(Integer.parseInt(mExtractionWater.getSelectedItemData()));
    }

    @Override
    protected void onItemClickSpeed() {
        profileCreationViewModel.setExtraction5Speed(Integer.parseInt(mExtractionSpeed.getSelectedItemData()));
    }

    @Override
    protected void onItemClickPause() {
        profileCreationViewModel.setExtraction5Pause(Integer.parseInt(mExtractionPause.getSelectedItemData()));
    }
}
