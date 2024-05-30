package com.imrezwan.wise_brewer.fragments;

import android.os.Bundle;

import com.imrezwan.wise_brewer.R;
import com.imrezwan.wise_brewer.utils.FragmentHandler;

public class Extraction4Fragment  extends BaseExtractionFragment {

    public Extraction4Fragment() {
        // Required empty public constructor
    }

    public static Extraction4Fragment newInstance() {
        Extraction4Fragment fragment = new Extraction4Fragment();
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
        FragmentHandler.replaceFragment(getActivity(), Extraction5Fragment.newInstance(), "extraction5");
    }

    @Override
    protected void onPrevButtonClicked() {

    }

    @Override
    protected String getSubtitle() {
        return "Extraction #04";
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
