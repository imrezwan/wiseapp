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
    protected int getLayoutResId() {
        return R.layout.fragment_extraction;
    }

    @Override
    protected void onNextButtonClicked() {
        FragmentHandler.replaceFragment(getActivity(), Extraction2Fragment.newInstance(), "extraction2");
    }

    @Override
    protected void onPrevButtonClicked() {

    }

    @Override
    protected String getSubtitle() {
        return "Extraction #01";
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