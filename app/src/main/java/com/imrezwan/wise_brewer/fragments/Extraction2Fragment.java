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
    protected void onNextButtonClicked() {
        FragmentHandler.replaceFragment(getActivity(), Extraction3Fragment.newInstance(), "extraction3");
    }

    @Override
    protected void onPrevButtonClicked() {

    }

    @Override
    protected String getSubtitle() {
        return "Extraction #02";
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