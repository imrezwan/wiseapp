package com.imrezwan.wise_brewer;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;

import com.imrezwan.wise_brewer.interfaces.IBluetoothConnector;
import com.imrezwan.wise_brewer.utils.Constants;
import com.imrezwan.wise_brewer.utils.FragmentHandler;
import com.imrezwan.wise_brewer.view_models.BluetoothViewModel;

import java.util.ArrayList;
import java.util.Collections;

public class DevicesFragment extends ListFragment {
    private BluetoothViewModel bluetoothViewModel;
    private IBluetoothConnector bluetoothConnector;
    private BluetoothAdapter bluetoothAdapter;
    private final ArrayList<BluetoothDevice> listItems = new ArrayList<>();
    private ArrayAdapter<BluetoothDevice> listAdapter;
    ActivityResultLauncher<String> requestBluetoothPermissionLauncherForRefresh;
    private Menu menu;
    private boolean permissionMissing;

    private SharedPrefHelper sharedPrefHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        sharedPrefHelper = SharedPrefHelper.newInstance(getContext());
        bluetoothViewModel= new ViewModelProvider(requireActivity()).get(BluetoothViewModel.class);
        if(getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH))
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        listAdapter = new ArrayAdapter<BluetoothDevice>(getActivity(), 0, listItems) {
            @NonNull
            @Override
            public View getView(int position, View view, @NonNull ViewGroup parent) {
                BluetoothDevice device = listItems.get(position);
                if (view == null)
                    view = getActivity().getLayoutInflater().inflate(R.layout.device_list_item, parent, false);
                TextView text1 = view.findViewById(R.id.text1);
                TextView text2 = view.findViewById(R.id.text2);
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    String deviceName = device.getName();
                    text1.setText(deviceName);
                    text2.setText(device.getAddress());
                }
                return view;
            }
        };
        requestBluetoothPermissionLauncherForRefresh = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                granted -> BluetoothUtil.onPermissionsResult(this, granted, this::refresh));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(null);
        View header = getActivity().getLayoutInflater().inflate(R.layout.device_list_header, null, false);
        getListView().addHeaderView(header, null, false);
        setEmptyText("initializing...");
        ((TextView) getListView().getEmptyView()).setTextSize(18);
        setListAdapter(listAdapter);
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        this.menu = menu;
        inflater.inflate(R.menu.menu_devices, menu);
        if(permissionMissing)
            menu.findItem(R.id.bt_refresh).setVisible(true);
        if(bluetoothAdapter == null)
            menu.findItem(R.id.bt_settings).setEnabled(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            bluetoothConnector = (IBluetoothConnector) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement IBluetoothConnector");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.bt_settings) {
            Intent intent = new Intent();
            intent.setAction(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
            startActivity(intent);
            return true;
        } else if (id == R.id.bt_refresh) {
            if(BluetoothUtil.hasPermissions(this, requestBluetoothPermissionLauncherForRefresh))
                refresh();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    @SuppressLint("MissingPermission")
    void refresh() {
        listItems.clear();
        if(bluetoothAdapter != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                permissionMissing = getActivity().checkSelfPermission(Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED;
                if(menu != null && menu.findItem(R.id.bt_refresh) != null)
                    menu.findItem(R.id.bt_refresh).setVisible(permissionMissing);
            }
            if(!permissionMissing) {
                for (BluetoothDevice device : bluetoothAdapter.getBondedDevices()) {
                    if (device.getType() != BluetoothDevice.DEVICE_TYPE_LE)
                        listItems.add(device);
                }
                Collections.sort(listItems, BluetoothUtil::compareTo);
            }
        }
        if(bluetoothAdapter == null)
            setEmptyText("<bluetooth not supported>");
        else if(!bluetoothAdapter.isEnabled())
            setEmptyText("<bluetooth is disabled>");
        else if(permissionMissing)
            setEmptyText("<permission missing, use REFRESH>");
        else
            setEmptyText("<no bluetooth devices found>");
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        BluetoothDevice device = listItems.get(position-1);
        sharedPrefHelper.setString(Constants.DEVICE_ADDRESS_KEY, device.getAddress());

        Log.d(Constants.LOGGER_TAG, "Device Address - " + device.getAddress());

        bluetoothConnector.onBluetoothConnect(device.getAddress());
        FragmentHandler.replaceFragment(getActivity(), HomeFragment2.newInstance(), "home");
    }
}
