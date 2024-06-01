package com.imrezwan.wise_brewer.fragments;

import android.os.Bundle;

import com.imrezwan.wise_brewer.R;
import com.imrezwan.wise_brewer.utils.FragmentHandler;

public class Extraction1Fragment extends BaseExtractionFragment {

    public Extraction1Fragment() {
        // Required empty public constructor
    }

    public static Extraction1Fragment newInstance() {
        Extraction1Fragment fragment = new Extraction1Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setExtractionFlag() {}

    @Override
    protected int getExtractionWater() {
        return profileCreationViewModel.getExtraction1Water();
    }

    @Override
    protected int getExtractionSpeed() {
        return profileCreationViewModel.getExtraction1Speed();
    }

    @Override
    protected int getExtractionPause() {
        return profileCreationViewModel.getExtraction1Pause();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_extraction;
    }

    @Override
    protected void onNextButtonClicked() {
        FragmentHandler.replaceFragment(getActivity(), Extraction2Fragment.newInstance(), "extraction2");
    }

    @Override
    protected String getSubtitle() {
        return "Extraction #01";
    }

    @Override
    protected void onItemClickWater() {
        profileCreationViewModel.setExtraction1Water(Integer.parseInt(mExtractionWater.getSelectedItemData()));
    }

    @Override
    protected void onItemClickSpeed() {
        profileCreationViewModel.setExtraction1Speed(Integer.parseInt(mExtractionSpeed.getSelectedItemData()));
    }

    @Override
    protected void onItemClickPause() {
        profileCreationViewModel.setExtraction1Pause(Integer.parseInt(mExtractionPause.getSelectedItemData()));
    }
}