package com.imrezwan.wise_brewer.fragments;

import android.os.Bundle;

import com.imrezwan.wise_brewer.R;
import com.imrezwan.wise_brewer.utils.FragmentHandler;

public class Extraction3Fragment  extends BaseExtractionFragment {

    public Extraction3Fragment() {
        // Required empty public constructor
    }

    public static Extraction3Fragment newInstance() {
        Extraction3Fragment fragment = new Extraction3Fragment();
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
        FragmentHandler.replaceFragment(getActivity(), Extraction4Fragment.newInstance(), "extraction4");
    }

    @Override
    protected void setExtractionFlag() {
        profileCreationViewModel.setExtraction3Flag(true);
    }

    @Override
    protected int getExtractionWater() {
        return profileCreationViewModel.getExtraction3Water();
    }

    @Override
    protected int getExtractionSpeed() {
        return profileCreationViewModel.getExtraction3Speed();
    }

    @Override
    protected int getExtractionPause() {
        return profileCreationViewModel.getExtraction3Pause();
    }

    @Override
    protected String getSubtitle() {
        return "Extraction #03";
    }

    @Override
    protected void onItemClickWater() {
        profileCreationViewModel.setExtraction3Water(Integer.parseInt(mExtractionWater.getSelectedItemData()));
    }

    @Override
    protected void onItemClickSpeed() {
        profileCreationViewModel.setExtraction3Speed(Integer.parseInt(mExtractionSpeed.getSelectedItemData()));
    }

    @Override
    protected void onItemClickPause() {
        profileCreationViewModel.setExtraction3Pause(Integer.parseInt(mExtractionPause.getSelectedItemData()));
    }
}