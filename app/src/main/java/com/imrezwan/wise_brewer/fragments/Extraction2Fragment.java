package com.imrezwan.wise_brewer.fragments;

import android.os.Bundle;

import com.imrezwan.wise_brewer.R;
import com.imrezwan.wise_brewer.utils.FragmentHandler;

public class Extraction2Fragment extends BaseExtractionFragment {

    public Extraction2Fragment() {
        // Required empty public constructor
    }

    public static Extraction2Fragment newInstance() {
        Extraction2Fragment fragment = new Extraction2Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_extraction;
    }

    @Override
    protected void setExtractionFlag() {
        profileCreationViewModel.setExtraction2Flag(true);
    }

    @Override
    protected int getExtractionWater() {
        return profileCreationViewModel.getExtraction2Water();
    }

    @Override
    protected int getExtractionSpeed() {
        return profileCreationViewModel.getExtraction2Speed();
    }

    @Override
    protected int getExtractionPause() {
        return profileCreationViewModel.getExtraction2Pause();
    }

    @Override
    protected void onNextButtonClicked() {
        FragmentHandler.replaceFragment(getActivity(), Extraction3Fragment.newInstance(), "extraction3");
    }

    @Override
    protected String getSubtitle() {
        return "Extraction #02";
    }

    @Override
    protected void onItemClickWater() {
        profileCreationViewModel.setExtraction2Water(Integer.parseInt(mExtractionWater.getSelectedItemData()));
    }

    @Override
    protected void onItemClickSpeed() {
        profileCreationViewModel.setExtraction2Speed(Integer.parseInt(mExtractionSpeed.getSelectedItemData()));
    }

    @Override
    protected void onItemClickPause() {
        profileCreationViewModel.setExtraction2Pause(Integer.parseInt(mExtractionPause.getSelectedItemData()));
    }
}