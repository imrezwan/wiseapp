package com.imrezwan.wise_brewer;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.imrezwan.wise_brewer.enums.Connected;
import com.imrezwan.wise_brewer.utils.FragmentHandler;
import com.imrezwan.wise_brewer.view_models.ProfileCreationViewModel;

import java.util.ArrayDeque;

public class MainActivity extends AppCompatActivity implements IProfileSender, ServiceConnection, SerialListener, FragmentManager.OnBackStackChangedListener, SharedPreferences.OnSharedPreferenceChangeListener {
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    private SharedPrefHelper sharedPrefHelper;

    private String deviceAddress;
    
    private SerialService service;
    private String receiveText = "";

    private boolean initialStart = true;

    private Connected connected = Connected.False;
    private boolean isActivityResumed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setupToolbar();
        setupBottomNavigationBar();
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        retrieveDeviceAddress();
        if (savedInstanceState == null)
            FragmentHandler.replaceFragment(this, HomeFragment2.newInstance(), "home");
        else
            onBackStackChanged();
    }

    private void setupBottomNavigationBar() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new HomeFragment2(), "home").addToBackStack(null).commit();
                    return true;
                case R.id.nav_profiles:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new ProfilesFragment(), "profiles").addToBackStack(null).commit();
                    return true;
            }
            return false;
        });

    }

    private void setupToolbar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    public void init() {
        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        sharedPrefHelper = SharedPrefHelper.newInstance(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.bluetooth_search) {
            FragmentHandler.replaceFragment(this, new DevicesFragment(), "device");
            return true;
        }
        else if (id == R.id.bluetooth_connect) {
            if(!sharedPrefHelper.getString("deviceAddress", "").isEmpty() &&
                    BluetoothAdapter.checkBluetoothAddress(sharedPrefHelper.getString(Constants.DEVICE_ADDRESS_KEY, ""))
            ){
                deviceAddress = sharedPrefHelper.getString("deviceAddress", "");

//                setStatus("Connecting...", R.color.textColor);
//                cardStart.setClickable(true);
                connect();
            }
            else {
                Toast.makeText(this, "Not Bluetooth device was connected. Please connect a new one.", Toast.LENGTH_SHORT).show();
            }

            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackStackChanged() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(getSupportFragmentManager().getBackStackEntryCount()>0);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    
    @Override
    public void onDestroy() {
        Log.d("TAGGGGGGGG", "DESTROYINGGGGGGGGGGGGGGGGGGGGGG HOME");
        if (connected != Connected.False)
            disconnect();
        this.stopService(new Intent(this, SerialService.class));
        SharedPreferences sharedPreferences = getSharedPreferences("your_prefs", Context.MODE_PRIVATE);
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(service != null)
            service.attach(this);
        else
            this.startService(new Intent(this, SerialService.class)); // prevents service destroy on unbind from recreated activity caused by orientation change
    }

    @Override
    public void onStop() {
        if(service != null && !this.isChangingConfigurations())
            service.detach();
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(initialStart && service != null) {
            initialStart = false;
            isActivityResumed = true;
            this.runOnUiThread(this::connect);
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        service = ((SerialService.SerialBinder) binder).getService();
        service.attach(this);
        if(initialStart && isActivityResumed()) {
            initialStart = false;
            this.runOnUiThread(this::connect);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isActivityResumed = false;
    }

    public boolean isActivityResumed() {
        return isActivityResumed;
    }

    private void disconnect() {
        connected = Connected.False;
        service.disconnect();
    }

    private void connect() {
        try {
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            BluetoothDevice device = bluetoothAdapter.getRemoteDevice(deviceAddress);
            connected = Connected.Pending;
//            setStatus("Connecting...", R.color.textColor);
            SerialSocket socket = new SerialSocket(this.getApplicationContext(), device);
            service.connect(socket);
        } catch (Exception e) {
            onSerialConnectError(e);
        }
    }

    public void retrieveDeviceAddress() {
        if(!sharedPrefHelper.getString("deviceAddress", "").isEmpty() &&
                BluetoothAdapter.checkBluetoothAddress(sharedPrefHelper.getString("deviceAddress", ""))
        ){
            deviceAddress = sharedPrefHelper.getString("deviceAddress", "");

//            setStatus("Connecting...", R.color.textColor);
//            cardStart.setClickable(true);
        }
        else {
//            setStatus("Not Connected !", android.R.color.holo_red_light);
//            cardStart.setClickable(false);
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        service = null;
    }

    @Override
    public void sendProfileName(String profileName) {
        Log.d("PROFILE", profileName);
        retrieveDeviceAddress();
//        send(profileName);
    }

    @Override
    public void onSerialConnect() {
//        setStatus("Connected", android.R.color.holo_green_dark);
        connected = Connected.True;
        Toast.makeText(this, "Bluetooth CONNECTED", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSerialConnectError(Exception e) {
//        setStatus("Connection failed !", android.R.color.holo_red_light);
        Toast.makeText(this, "CONNECTION ERROR", Toast.LENGTH_LONG).show();
        disconnect();
    }

    @Override
    public void onSerialRead(byte[] data) {
        ArrayDeque<byte[]> datas = new ArrayDeque<>();
        datas.add(data);
//        receive(datas);
    }

    public void onSerialRead(ArrayDeque<byte[]> datas) {
//        receive(datas);
    }

    @Override
    public void onSerialIoError(Exception e) {
//        setStatus("Connection Lost !", android.R.color.holo_red_light);
        disconnect();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, @Nullable String key) {
        if (key.equals(Constants.DEVICE_ADDRESS_KEY)) {
            deviceAddress = sharedPreferences.getString(key, "");
            connect();
        }
    }
}
