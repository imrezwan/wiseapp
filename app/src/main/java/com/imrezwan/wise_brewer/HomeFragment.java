package com.imrezwan.wise_brewer;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;

public class HomeFragment extends Fragment implements IProfileSender, ServiceConnection, SerialListener {
    private static final String FORMAT = "%02d:%02d:%02d";
    private static final int totalDisableTime = 10000;

    @Override
    public void sendProfileName(String profileName) {
        Log.d("PROFILE", profileName);
        retrieveDeviceAddress();
        send(profileName);
    }

    private enum Connected { False, Pending, True }

    private String deviceAddress;
    private SerialService service;

    private String receiveText = "";
    private String sendText = "";

    private Connected connected = Connected.False;
    private boolean initialStart = true;
    private boolean hexEnabled = false;
    private boolean pendingNewline = false;
    private String newline = TextUtil.newline_crlf;

    private CardView cardStart;
    private CardView cardProfiling;
    private SharedPrefHelper sharedPrefHelper;
    private TextView tvConnectionStatus;
    private LinearLayout llStart;

    private View thermometerView;
    private TextView tvTime, tvStart, tvStatus, tvTemparature, tvWater, tvTDS;

    private boolean isStarted = false;

    /*
     * Lifecycle
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);

        sharedPrefHelper = SharedPrefHelper.newInstance(getContext());
    }

    public void retrieveDeviceAddress() {
        if(getArguments() != null){
            deviceAddress = getArguments().getString("device");
            sharedPrefHelper.setString("deviceAddress", deviceAddress);

            setStatus("Connecting...", R.color.textColor);
            cardStart.setClickable(true);
        }
        else if(!sharedPrefHelper.getString("deviceAddress", "").isEmpty() &&
                BluetoothAdapter.checkBluetoothAddress(sharedPrefHelper.getString("deviceAddress", ""))
        ){
            deviceAddress = sharedPrefHelper.getString("deviceAddress", "");

            setStatus("Connecting...", R.color.textColor);
            cardStart.setClickable(true);
        }
        else {
            setStatus("Not Connected !", android.R.color.holo_red_light);
            cardStart.setClickable(false);
        }
    }




    @Override
    public void onDestroy() {
        Log.d("TAGGGGGGGG", "DESTROYINGGGGGGGGGGGGGGGGGGGGGG HOME");
        if (connected != Connected.False)
            disconnect();
        getActivity().stopService(new Intent(getActivity(), SerialService.class));
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(service != null)
            service.attach(this);
        else
            getActivity().startService(new Intent(getActivity(), SerialService.class)); // prevents service destroy on unbind from recreated activity caused by orientation change
    }

    @Override
    public void onStop() {
        if(service != null && !getActivity().isChangingConfigurations())
            service.detach();
        super.onStop();
    }

    @SuppressWarnings("deprecation") // onAttach(context) was added with API 23. onAttach(activity) works for all API versions
    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        getActivity().bindService(new Intent(getActivity(), SerialService.class), this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onDetach() {
        try { getActivity().unbindService(this); } catch(Exception ignored) {}
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(initialStart && service != null) {
            initialStart = false;
            getActivity().runOnUiThread(this::connect);
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        service = ((SerialService.SerialBinder) binder).getService();
        service.attach(this);
        if(initialStart && isResumed()) {
            initialStart = false;
            getActivity().runOnUiThread(this::connect);
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        service = null;
    }

    /*
     * UI
     */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);


        cardStart.setOnClickListener(view1 -> {

            if (isStarted) {
                send("START");
                tvStart.setText("START");
                tvStart.setTextColor(getResources().getColor(android.R.color.black));
                isStarted = false;
            } else {
                send("STOP");
                tvStart.setText("STOP");
                tvStart.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                //llStart.setBackgroundResource(R.drawable.button_border_enable);
                cardStart.setCardBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                isStarted = true;
            }

//            startCountDownTimer();
        });


        cardProfiling.setOnClickListener(view1 -> {
            getParentFragmentManager().beginTransaction()
                    .hide(this)
                    .add(R.id.fragment, ProfilingFragment.newInstance(this), "profiling")
                    .addToBackStack(null)
                    .commit();
        });

        retrieveDeviceAddress();
        updateStartButton(true);
        return view;
    }


    private void init(View view) {
        cardStart = view.findViewById(R.id.card_start);
        //TextView startStopTextView = view.findViewById(R.id.tv_start);
        tvConnectionStatus = view.findViewById(R.id.tv_connect_status);
        llStart = view.findViewById(R.id.ll_start);
        tvStart = view.findViewById(R.id.tv_start);

        tvTime = view.findViewById(R.id.tv_time);

        tvStatus = view.findViewById(R.id.tv_status);
        tvTemparature = view.findViewById(R.id.tv_temparature);
        tvWater = view.findViewById(R.id.tv_water);
        tvTDS = view.findViewById(R.id.tv_tds);

        thermometerView = view.findViewById(R.id.themometer_view);

        //profiling
        cardProfiling = view.findViewById(R.id.card_profiling);
    }

    private void setUpProgressOfTemparature(int temparature) {
        int progress = (int)((360/120) * temparature);
        Log.d("COFFEE_TAG", progress + "");
        Rect clipRect = new Rect(0, progress, 100, 0);
        thermometerView.setClipBounds(clipRect);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.bluetooth_search) {
            receiveText = "";
            getParentFragmentManager().beginTransaction().replace(R.id.fragment, new DevicesFragment(), "device").addToBackStack(null).commit();
            return true;
        }
        else if (id == R.id.bluetooth_connect) {
            receiveText = "";
            if(!sharedPrefHelper.getString("deviceAddress", "").isEmpty() &&
                    BluetoothAdapter.checkBluetoothAddress(sharedPrefHelper.getString("deviceAddress", ""))
            ){
                deviceAddress = sharedPrefHelper.getString("deviceAddress", "");

                setStatus("Connecting...", R.color.textColor);
                cardStart.setClickable(true);
                connect();
            }
            else {
                Toast.makeText(getActivity(), "Not Bluetooth device was connected. Please connect a new one.", Toast.LENGTH_SHORT).show();
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    /*
     * Serial + UI
     */
    private void connect() {
        try {
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            BluetoothDevice device = bluetoothAdapter.getRemoteDevice(deviceAddress);
            connected = Connected.Pending;
            setStatus("Connecting...", R.color.textColor);
            SerialSocket socket = new SerialSocket(getActivity().getApplicationContext(), device);
            service.connect(socket);
        } catch (Exception e) {
            onSerialConnectError(e);
        }
    }

    private void disconnect() {
        connected = Connected.False;
        service.disconnect();
    }

    private void send(String str) {
        if(connected != Connected.True) {
            Toast.makeText(getActivity(), "Not connected", Toast.LENGTH_SHORT).show();
            setStatus("Not Connected", android.R.color.holo_red_light);
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

    boolean isCounting = false;
    private void startCountDownTimer() {
        updateStartButton(false);
        new CountDownTimer(totalDisableTime, 1000) {
            public void onTick(long millisUntilFinished) {
                isCounting = true;
                tvTime.setText(""+String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }
            public void onFinish() {
                isCounting = false;
                updateStartButton(true);
            }

        }.start();
    }

    private void updateStartButton(boolean isEnable){
        if (isEnable) {
            llStart.setBackgroundResource(R.drawable.button_border_enable);
            tvTime.setVisibility(View.GONE);
            tvStart.setText("START");
            cardStart.setClickable(true);
        }
        else {
            llStart.setBackgroundResource(R.drawable.button_border_disable);
            tvTime.setVisibility(View.GONE);
            cardStart.setClickable(false);
        }
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
        Log.d("COFFEE_TAG", receiveText);
        Log.d("COFFEE_TAG_Length",Integer.toString(newString.length()));
        String[] coffeeDataArr = receiveText.split(",");

//        Toast.makeText(getActivity(), "Received: " + receiveText, Toast.LENGTH_SHORT).show();
        if(newString.equals("OK")||receiveText.equalsIgnoreCase("OK") /*&& !isCounting*/) {
            //startCountDownTimer(); // disable
            Toast.makeText(getContext(), "STARTED", Toast.LENGTH_SHORT).show();
            MediaPlayer mPlayer = MediaPlayer.create(getContext(), R.raw.beep_start);
            mPlayer.start();
        }
        else if(newString.equals("DONE") || receiveText.equals("DONE")) {
            Toast.makeText(getContext(), "YOUR COFFEE IS READY", Toast.LENGTH_LONG ).show();
            MediaPlayer mPlayer = MediaPlayer.create(getContext(), R.raw.beep);
            mPlayer.start();
            updateStartButton(true);
            tvStart.setText("START");
            tvStart.setTextColor(getResources().getColor(android.R.color.black));
            tvStart.setTextColor(getResources().getColor(android.R.color.black));
        }
        else if(coffeeDataArr.length == 5) {
            long currentTime = System.currentTimeMillis();
//            Log.d("COFFEE_TAG", lastCallTime + " => " + currentTime);
            if( (currentTime - lastCallTime) > 100) {
               //Log.d("COFFEE_TAG", "INSIDE ====>>>>>>>>>>>");
                setupCoffeeDataOnScreen(coffeeDataArr);
            }
            lastCallTime = currentTime;
        }
    }

    private long lastCallTime = System.currentTimeMillis();

    boolean isRendering = false;
    private void setupCoffeeDataOnScreen(String[] coffeeDataArr) {
        if(!isRendering) {
            isRendering = true;
            String status = coffeeDataArr[0];
            String temparature = coffeeDataArr[1];
            String base_temp = coffeeDataArr[2];
            String water = coffeeDataArr[3];
            String tds = coffeeDataArr[4];

            //base_temp = base_temp.substring(0, base_temp.length() - 1);

            if(status.equals("1")){
                tvStatus.setText("READY");
                tvStatus.setTextColor(Color.parseColor("#05AE0C"));
            }
            else{
                tvStatus.setText("WAIT");
                tvStatus.setTextColor(Color.parseColor("#FF5722"));
            }
            tvTemparature.setText(base_temp+"°C");
            setUpProgressOfTemparature(Integer.parseInt(temparature));

            tvWater.setText(water+"mL");
            tvTDS.setText("TDS VALUE "+tds);
            isRendering = false;
        }
    }

    private void setStatus(String status, int colorId) {
        tvConnectionStatus.setText(status);
        tvConnectionStatus.setTextColor(getResources().getColor(colorId));
    }



    /*
     * SerialListener
     */
    @Override
    public void onSerialConnect() {
        setStatus("Connected", android.R.color.holo_green_dark);
        connected = Connected.True;
    }

    @Override
    public void onSerialConnectError(Exception e) {
        setStatus("Connection failed !", android.R.color.holo_red_light);
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

    @Override
    public void onSerialIoError(Exception e) {
        setStatus("Connection Lost !", android.R.color.holo_red_light);
        disconnect();
    }

}
