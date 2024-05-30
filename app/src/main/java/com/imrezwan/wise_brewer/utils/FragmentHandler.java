package com.imrezwan.wise_brewer.utils;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.imrezwan.wise_brewer.ProfileSetupFragment;
import com.imrezwan.wise_brewer.R;

public class FragmentHandler {
    public static void replaceFragment(FragmentActivity activity, Fragment newFragment, String newFragmentTag) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, newFragment, newFragmentTag)
                .addToBackStack(null)
                .commit();
    }
}
