package com.imrezwan.wise_brewer;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.imrezwan.wise_brewer.enums.Connection;
import com.imrezwan.wise_brewer.interfaces.IBluetoothConnector;
import com.imrezwan.wise_brewer.interfaces.IBluetoothFragmentDataDispatcher;
import com.imrezwan.wise_brewer.models.ProfileData;
import com.imrezwan.wise_brewer.utils.Constants;
import com.imrezwan.wise_brewer.utils.FragmentHandler;
import com.imrezwan.wise_brewer.view_models.BluetoothViewModel;
import com.imrezwan.wise_brewer.view_models.ProfileCreationViewModel;

import java.util.ArrayDeque;

public class MainActivity extends AppCompatActivity implements
        IProfileSender, ServiceConnection, SerialListener,
        FragmentManager.OnBackStackChangedListener,
        IBluetoothConnector,
        IBluetoothFragmentDataDispatcher
{
    ProfileCreationViewModel profileCreationViewModel;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    private SharedPrefHelper sharedPrefHelper;

    private BluetoothViewModel bluetoothViewModel;

    private String deviceAddress;
    
    private SerialService service;
    private String receiveText = "";

    private boolean hexEnabled = false;
    private boolean pendingNewline = false;
    private String newline = TextUtil.newline_crlf;
    private long lastCallTime = System.currentTimeMillis();

    private boolean initialStart = true;
    private boolean isActivityResumed = false;

    private HomeFragment2 homeFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setupToolbar();
        setupBottomNavigationBar();
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        if (savedInstanceState == null) {
            FragmentHandler.replaceFragment(this, getHomeFragment(), "home");
        }else
            onBackStackChanged();
    }

    private void setupBottomNavigationBar() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, getHomeFragment(), "home").addToBackStack(null).commit();
                    return true;
                case R.id.nav_profiles:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new ProfilesFragment(), "profiles").addToBackStack(null).commit();
                    return true;
            }
            return false;
        });

    }

    private HomeFragment2 getHomeFragment() {
        if (homeFragment != null) {
            return homeFragment;
        }
        homeFragment = HomeFragment2.newInstance();
        return homeFragment;
    }

    private void setupToolbar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    public void init() {
        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bluetoothViewModel= new ViewModelProvider(this).get(BluetoothViewModel.class);
        sharedPrefHelper = new SharedPrefHelper(this);
        profileCreationViewModel = new ViewModelProvider(this).get(ProfileCreationViewModel.class);
        profileCreationViewModel.setProfileData(new ProfileData());

        sharedPrefHelper.setDefaultPreferences();

        bindSerialService();
    }

    public Connection getBluetoothStatus() {
        return bluetoothViewModel.getBluetoothStatus().getValue();
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
            if(!sharedPrefHelper.getString(Constants.DEVICE_ADDRESS_KEY, "").isEmpty() &&
                    BluetoothAdapter.checkBluetoothAddress(sharedPrefHelper.getString(Constants.DEVICE_ADDRESS_KEY, ""))
            ){
                deviceAddress = sharedPrefHelper.getString(Constants.DEVICE_ADDRESS_KEY, "");
                Log.d(Constants.LOGGER_TAG, "conneting device - " + deviceAddress);

//                setStatus("Connecting...", R.color.textColor);
                bluetoothViewModel.setBluetoothStatus(Connection.Pending);
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

        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        boolean showBackArrow = backStackEntryCount > 0;

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (currentFragment instanceof HomeFragment2) {
            showBackArrow = false;
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(showBackArrow);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    
    @Override
    public void onDestroy() {
        Log.d("TAGGGGGGGG", "DESTROYINGGGGGGGGGGGGGGGGGGGGGG HOME");
        if (getBluetoothStatus() != Connection.False)
            disconnect();
        this.stopService(new Intent(this, SerialService.class));
        sharedPrefHelper.pref.unregisterOnSharedPreferenceChangeListener((sharedPreferences1, s) -> {});
        super.onDestroy();
    }

    private void bindSerialService() {
        if(service == null)  {
            Log.e(Constants.LOGGER_TAG, "Serivce null , but trying to bind");
        }
        this.bindService(new Intent(this, SerialService.class), this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(service != null)
            service.attach(this);
        else {
            this.startService(new Intent(this, SerialService.class));
            Log.d(Constants.LOGGER_TAG, "Starting service");
        }
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
            retrieveDeviceAddress();
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
        bluetoothViewModel.setBluetoothStatus(Connection.False);
        service.disconnect();
    }

    private void connect() {
        try {
            Log.d(Constants.LOGGER_TAG, "Connecting..." + deviceAddress);
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            BluetoothDevice device = bluetoothAdapter.getRemoteDevice(deviceAddress);
//            setStatus("Connecting...", R.color.textColor);
            bluetoothViewModel.setBluetoothStatus(Connection.Pending);
            SerialSocket socket = new SerialSocket(this.getApplicationContext(), device);
            service.connect(socket);

        } catch (Exception e) {
            onSerialConnectError(e);
        }
    }

    public void retrieveDeviceAddress() {
        if(!sharedPrefHelper.getString(Constants.DEVICE_ADDRESS_KEY, "").isEmpty() &&
                BluetoothAdapter.checkBluetoothAddress(sharedPrefHelper.getString(Constants.DEVICE_ADDRESS_KEY, ""))
        ){
            deviceAddress = sharedPrefHelper.getString(Constants.DEVICE_ADDRESS_KEY, "");
            Log.d(Constants.LOGGER_TAG, "Retrive Device-" + deviceAddress);

//            setStatus("Connecting...", R.color.textColor);
            bluetoothViewModel.setBluetoothStatus(Connection.Pending);
//            cardStart.setClickable(true);
            connect();
        }
        else {
//            setStatus("Not Connected !", android.R.color.holo_red_light);
            bluetoothViewModel.setBluetoothStatus(Connection.False);
//            cardStart.setClickable(false);
        }
    }

    private void send(String str) {
        if(bluetoothViewModel.getBluetoothStatus().getValue() != Connection.True) {
            Toast.makeText(this, "Not connected", Toast.LENGTH_SHORT).show();
            bluetoothViewModel.setBluetoothStatus(Connection.False);
            return;
        }
        try {
            String msg;
            byte[] data;
            if(hexEnabled) {
                StringBuilder sb = new StringBuilder();
                TextUtil.toHexString(sb, TextUtil.fromHexString(str));
                TextUtil.toHexString(sb, newline.getBytes());
                msg = sb.toString();
                data = TextUtil.fromHexString(msg);
            } else {
                msg = str;
                data = (str + newline).getBytes();
            }
            SpannableStringBuilder spn = new SpannableStringBuilder(msg + '\n');
            spn.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorSendText)), 0, spn.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//            Toast.makeText(getActivity(), "Sent: " + str, Toast.LENGTH_SHORT).show();
            service.write(data);

        } catch (Exception e) {
            onSerialIoError(e);
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        service = null;
        bluetoothViewModel.setBluetoothStatus(Connection.False);
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
        bluetoothViewModel.setBluetoothStatus(Connection.True);
        Toast.makeText(this, "Bluetooth CONNECTED", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSerialConnectError(Exception e) {
//        setStatus("Connection failed !", android.R.color.holo_red_light);
        bluetoothViewModel.setBluetoothStatus(Connection.Error);
        Log.d(Constants.LOGGER_TAG, "Exception-" + e.getMessage() + "==>>" + e.getCause());
        Toast.makeText(this, "CONNECTION ERROR", Toast.LENGTH_LONG).show();
        disconnect();
    }

    @Override
    public void onSerialRead(byte[] data) {
        ArrayDeque<byte[]> datas = new ArrayDeque<>();
        datas.add(data);
        receive(datas);
    }



    public void onSerialRead(ArrayDeque<byte[]> datas) {
        receive(datas);
    }

    private void receive(ArrayDeque<byte[]> datas) {
        SpannableStringBuilder spn = new SpannableStringBuilder();
        for (byte[] data : datas) {
            if (hexEnabled) {
                spn.append(TextUtil.toHexString(data)).append('\n');
            } else {
                String msg = new String(data);
                if (newline.equals(TextUtil.newline_crlf) && msg.length() > 0) {
                    // don't show CR as ^M if directly before LF
                    msg = msg.replace(TextUtil.newline_crlf, TextUtil.newline_lf);
                    // special handling if CR and LF come in separate fragments
                    if (pendingNewline && msg.charAt(0) == '\n') {
                        if(spn.length() >= 2) {
                            spn.delete(spn.length() - 2, spn.length());
                        }
                    }
                    pendingNewline = msg.charAt(msg.length() - 1) == '\r';
                }
                spn.append(TextUtil.toCaretString(msg, newline.length() != 0));
            }
        }
        receiveText = spn.toString();
        String newString = receiveText.substring(0, receiveText.length() - 1);
        Log.d(Constants.LOGGER_TAG, "Receieved Text: " + receiveText);
        Log.d(Constants.LOGGER_TAG, "Text Length: " + newString.length());
        String[] coffeeDataArr = receiveText.split(" ");

        if(Integer.parseInt(coffeeDataArr[0]) == Constants.INITIAL_SCREEN_DATA) {
            homeFragment.dispatchDataFromMainActivityToFragment(newString);
        }


//
////        Toast.makeText(getActivity(), "Received: " + receiveText, Toast.LENGTH_SHORT).show();
//        if(newString.equals("OK")||receiveText.equalsIgnoreCase("OK") /*&& !isCounting*/) {
//            //startCountDownTimer(); // disable
//            Toast.makeText(this, "STARTED", Toast.LENGTH_SHORT).show();
//            MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.beep_start);
//            mPlayer.start();
//        }
//        else if(newString.equals("DONE") || receiveText.equals("DONE")) {
//            Toast.makeText(this, "YOUR COFFEE IS READY", Toast.LENGTH_LONG ).show();
//            MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.beep);
//            mPlayer.start();
////            updateStartButton(true);
////            tvStart.setText("START");
////            tvStart.setTextColor(getResources().getColor(android.R.color.black));
////            tvStart.setTextColor(getResources().getColor(android.R.color.black));
//        }
//        else if(coffeeDataArr.length == 5) {
//            long currentTime = System.currentTimeMillis();
////            Log.d("COFFEE_TAG", lastCallTime + " => " + currentTime);
//            if( (currentTime - lastCallTime) > 100) {
//                //Log.d("COFFEE_TAG", "INSIDE ====>>>>>>>>>>>");
////                setupCoffeeDataOnScreen(coffeeDataArr);
//            }
//            lastCallTime = currentTime;
//        }
    }

    @Override
    public void onSerialIoError(Exception e) {
//        setStatus("Connection Lost !", android.R.color.holo_red_light);
        disconnect();
    }

    @Override
    public void onBluetoothConnect(String deviceAddress) {
        Log.d(Constants.LOGGER_TAG, "Trying to connect with Bluetooth - " + deviceAddress);
        sharedPrefHelper.setString(Constants.DEVICE_ADDRESS_KEY, deviceAddress);
        this.deviceAddress = deviceAddress;
        connect();
    }

    @Override
    public void dispatchDataFromFragmentToMainActivity(String data) {
        send(data);
    }
}
