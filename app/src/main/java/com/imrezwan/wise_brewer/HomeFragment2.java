package com.imrezwan.wise_brewer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.imrezwan.wise_brewer.enums.Connection;
import com.imrezwan.wise_brewer.view_models.BluetoothViewModel;

public class HomeFragment2 extends Fragment {
    private BluetoothViewModel bluetoothViewModel;
    private TextView mBluetoothStatus;
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

        bluetoothViewModel.getBluetoothStatus().observe(getViewLifecycleOwner(), this::updateBluetoothStatus);
        return view;
    }

    private void initialize(View view) {
        mBluetoothStatus = view.findViewById(R.id.tv_bluetooth_status);
        bluetoothViewModel= new ViewModelProvider(requireActivity()).get(BluetoothViewModel.class);
    }

    private void updateBluetoothStatus(Connection connection) {
        switch (connection) {
            case Error:
                mBluetoothStatus.setText("Connection Error");
                break;
            case True:
                mBluetoothStatus.setText("Connected");
                break;
            case Pending:
                mBluetoothStatus.setText("Connecting...");
                break;
            case False:
                mBluetoothStatus.setText("Not Connected");
                break;
            default:
                mBluetoothStatus.setText("Connection Undetermined");
                break;
        }
    }


}