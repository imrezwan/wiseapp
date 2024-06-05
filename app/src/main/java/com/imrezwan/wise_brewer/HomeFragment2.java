package com.imrezwan.wise_brewer;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.imrezwan.wise_brewer.enums.Connection;
import com.imrezwan.wise_brewer.interfaces.IBluetoothConnector;
import com.imrezwan.wise_brewer.interfaces.IBluetoothDataCommunication;
import com.imrezwan.wise_brewer.models.ProfileFactory;
import com.imrezwan.wise_brewer.utils.Constants;
import com.imrezwan.wise_brewer.view_models.BluetoothViewModel;

import java.util.ArrayList;

public class HomeFragment2 extends Fragment {
    private BluetoothViewModel bluetoothViewModel;
    private TextView mBluetoothStatus, mSelectedProfile;
    private ImageView mIvSelectedProfileArrow;
    private SharedPrefHelper sharedPrefHelper;
    private IBluetoothDataCommunication bluetoothDataCommunicationlistener;
    public HomeFragment2() {
    }

    public static HomeFragment2 newInstance() {
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
        initialize(view);
        restoreValues();

        mIvSelectedProfileArrow.setOnClickListener(view1 -> showPopupMenu(view1));

        bluetoothViewModel.getBluetoothStatus().observe(getViewLifecycleOwner(), this::updateBluetoothStatus);
        return view;
    }

    private void restoreValues() {
        String selectedProfileName = sharedPrefHelper.getString(Constants.SELECTED_PROFILE_KEY, "");
        if(!selectedProfileName.isEmpty()) {
            mSelectedProfile.setText(selectedProfileName);
        }
    }

    private void initialize(View view) {
        mBluetoothStatus = view.findViewById(R.id.tv_bluetooth_status);
        bluetoothViewModel= new ViewModelProvider(requireActivity()).get(BluetoothViewModel.class);
        mIvSelectedProfileArrow = view.findViewById(R.id.iv_selected_profile_arrow_down);
        mSelectedProfile = view.findViewById(R.id.tv_selected_profile);
        sharedPrefHelper = new SharedPrefHelper(requireActivity());
    }

    private void updateBluetoothStatus(Connection connection) {
        switch (connection) {
            case Error:
                setBluetoothStatusOnUI("Connection Error", R.color.colorAccent);
                break;
            case True:
                setBluetoothStatusOnUI("Connected", R.color.colorGreen);
                break;
            case Pending:
                setBluetoothStatusOnUI("Connecting...", R.color.colorDarkBlue);
                break;
            case False:
                setBluetoothStatusOnUI("Not Connected", R.color.colorText);
                break;
            default:
                setBluetoothStatusOnUI("Connection Undetermined", R.color.colorBlack);
                break;
        }
    }

    private void setBluetoothStatusOnUI(String str, int color) {
        mBluetoothStatus.setText(str);
        mBluetoothStatus.setTextColor(getResources().getColor(color));
    }


    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        ArrayList<ProfileFactory.ProfileInfo> profileInfos = new ProfileFactory(getContext()).getProfileInfoList();

        for (ProfileFactory.ProfileInfo profileInfo : profileInfos) {
            popupMenu.getMenu().add(profileInfo.getDisplayTitle());
        }

        popupMenu.setOnMenuItemClickListener(item -> {
            Log.d(Constants.LOGGER_TAG, item.toString());
            return handleMenuItemClick(item);
        });

        popupMenu.show();
    }

    private boolean handleMenuItemClick(MenuItem item) {
        Toast.makeText(getContext(), "Selected: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        sharedPrefHelper.setString(Constants.SELECTED_PROFILE_KEY, item.getTitle().toString());
        mSelectedProfile.setText(item.getTitle());
        return true;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            bluetoothDataCommunicationlistener = (IBluetoothDataCommunication) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement IBluetoothDataCommunication");
        }
    }
}
