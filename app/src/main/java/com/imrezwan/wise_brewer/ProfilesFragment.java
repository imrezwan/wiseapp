package com.imrezwan.wise_brewer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imrezwan.wise_brewer.interfaces.IOnProfileEditListener;
import com.imrezwan.wise_brewer.models.ProfileFactory;
import com.imrezwan.wise_brewer.utils.FragmentHandler;

public class ProfilesFragment extends Fragment {
    private ListViewAdapter profileListAdapter;
    private SharedPrefHelper sharedPrefHelper;
    private ProfileFactory profileFactory;

    public ProfilesFragment() {
    }

    public static ProfilesFragment newInstance() {
        ProfilesFragment fragment = new ProfilesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_list, container, false);
        CardView mNewProfileBtn = view.findViewById(R.id.card_new_profile_btn);
        profileFactory = new ProfileFactory(getContext());
        sharedPrefHelper = new SharedPrefHelper(getContext());
        mNewProfileBtn.setOnClickListener(view1 ->
                FragmentHandler.replaceFragment(getActivity(), new ProfileSetupFragment(), "profilesetup")
        );

        RecyclerView rvProfileList = view.findViewById(R.id.rv_profile_list);

        profileListAdapter = new ListViewAdapter(profileFactory.getProfileInfoList(), new IOnProfileEditListener() {
            @Override
            public void onEditButtonClicked(ProfileFactory.ProfileInfo profileInfo) {
                showAlertDialogButtonClicked(profileInfo);
            }
        });

        rvProfileList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvProfileList.setAdapter(profileListAdapter);
        return view;
    }

    public void showAlertDialogButtonClicked(ProfileFactory.ProfileInfo profileInfo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.TransparentDialog);
        builder.setCancelable(true);

        final View customLayout = getLayoutInflater().inflate(R.layout.edit_profile_name, null);
        builder.setView(customLayout);

        EditText mEvProfileName = customLayout.findViewById(R.id.et_profile_name);
        CardView mSaveProfileName = customLayout.findViewById(R.id.card_save_profile_name);
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        mEvProfileName.setText(profileInfo.getDisplayTitle());
        mEvProfileName.setSelection(mEvProfileName.getText().length());
        AlertDialog dialog = builder.create();

        mSaveProfileName.setOnClickListener(view -> {
            String newProfileName = mEvProfileName.getText().toString().trim();
            profileInfo.setDisplayTitle(newProfileName);
            sharedPrefHelper.setProfileInfo(profileInfo);
            profileListAdapter.updateData(profileFactory.getProfileInfoList());
            imm.hideSoftInputFromWindow(mEvProfileName.getWindowToken(), 0);

            sendDialogDataToActivity("'"+mEvProfileName.getText().toString()+"' Profile Name is successfully saved.");

            dialog.dismiss();
        });
        dialog.show();
    }

    private void sendDialogDataToActivity(String data) {
        Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();
    }
}