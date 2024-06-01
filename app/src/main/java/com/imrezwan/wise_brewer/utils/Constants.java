package com.imrezwan.wise_brewer.utils;

import com.imrezwan.wise_brewer.BuildConfig;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final String LOGGER_TAG = "TAGGG";
    public static final String DEVICE_ADDRESS_KEY = "deviceAddress";
    public static final String INTENT_ACTION_DISCONNECT = BuildConfig.APPLICATION_ID + ".Disconnect";
    public static final String NOTIFICATION_CHANNEL = BuildConfig.APPLICATION_ID + ".Channel";
    public static final String INTENT_CLASS_MAIN_ACTIVITY = BuildConfig.APPLICATION_ID + ".MainActivity";
    public static final int NOTIFY_MANAGER_START_FOREGROUND_SERVICE = 1001;


    public static List<String> mDataAmountOfWater = Arrays.asList("200", "250", "300", "350", "400", "450", "500");
    public static List<String> mDataTemperature = Arrays.asList("88c", "89c", "90c", "92c", "93c", "94c", "95c", "96c");
    public static List<String> mDataWater = Arrays.asList("10", "20", "30", "40", "50", "50", "60", "70", "80", "90");
    public static List<String> mDataSpeed = Arrays.asList("3", "4", "5", "6", "7", "8", "9");
    public static List<String> mDataPause = Arrays.asList("10", "20", "30", "40", "50", "60");
}
