package com.imrezwan.coffeepro_bluetooth_terminal;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilingFragment extends Fragment {
    private TextView tvDefault, tvProfile1, tvProfile2, tvProfile3, tvProfile4;
    private CardView mNewProfile;
    private HomeFragment receiverFragment ;

    public ProfilingFragment(HomeFragment receiverFragment) {
        this.receiverFragment = receiverFragment;
    }

    public static ProfilingFragment newInstance(HomeFragment receiverFragment) {
        ProfilingFragment fragment = new ProfilingFragment(receiverFragment);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profiling, container, false);
        init(view);
        handleClickEvents();

        return view;
    }

    private void handleClickEvents() {
        tvDefault.setOnClickListener(view -> {
            sendProfileName("default");
        });
        tvProfile1.setOnClickListener(view -> {
            sendProfileName("profile1");
        });
        tvProfile2.setOnClickListener(view -> {
            sendProfileName("profile2");
        });
        tvProfile3.setOnClickListener(view -> {
            sendProfileName("profile3");
        });

        tvProfile4.setOnClickListener(view -> {
            sendProfileName("profile4");
        });

        mNewProfile.setOnClickListener(view -> {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment,
                            new ProfileRecipeFragment(),
                            "profile_recipe")
                    .addToBackStack(null)
                    .commit();
        });
    }

    private void sendProfileName(String name) {
        this.receiverFragment.sendProfileName(name);
        Toast.makeText(getActivity(), "'"+name+"' profile has been sent", Toast.LENGTH_SHORT).show();
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment,
                        new HomeFragment(),
                        "home")
                .addToBackStack(null).commit();
    }

    private void init(View view) {
        tvDefault = view.findViewById(R.id.tv_default_profile);
        tvProfile1 = view.findViewById(R.id.tv_profile1);
        tvProfile2 = view.findViewById(R.id.tv_profile2);
        tvProfile3 = view.findViewById(R.id.tv_profile3);
        tvProfile4 = view.findViewById(R.id.tv_profile4);
        mNewProfile = view.findViewById(R.id.card_new_profile);
    }


}