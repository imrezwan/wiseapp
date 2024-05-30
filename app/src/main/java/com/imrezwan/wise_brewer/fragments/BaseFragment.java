package com.imrezwan.wise_brewer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.imrezwan.wise_brewer.R;

public abstract class BaseFragment extends Fragment {
    protected TextView mNext, mPrev;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);

        mNext = view.findViewById(R.id.tv_next);
        mPrev = view.findViewById(R.id.tv_back);

        mNext.setOnClickListener(v -> onNextButtonClicked());
        mPrev.setOnClickListener(v -> onPrevButtonClicked());

        return view;
    }

    protected abstract int getLayoutResId();
    protected abstract int onNextButtonClicked();
    protected abstract int onPrevButtonClicked();
}
