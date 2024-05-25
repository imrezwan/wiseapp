package com.imrezwan.wise_brewer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProfileSetupFragment extends Fragment {

    public ProfileSetupFragment() {

    }

    public static ProfileSetupFragment newInstance(String param1, String param2) {
        ProfileSetupFragment fragment = new ProfileSetupFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_setup, container, false);
        return view;
    }
}