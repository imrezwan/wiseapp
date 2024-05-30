package com.imrezwan.wise_brewer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imrezwan.wise_brewer.view_models.ProfileCreationViewModel;

public class HomeFragment2 extends Fragment {
    private ProfileCreationViewModel viewModel;

    public HomeFragment2() {
    }

    public static HomeFragment2 newInstance(String param1, String param2) {
        HomeFragment2 fragment = new HomeFragment2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home2, container, false);
        return view;
    }
}