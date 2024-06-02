package com.imrezwan.wise_brewer.utils;

import com.imrezwan.wise_brewer.BuildConfig;
import com.imrezwan.wise_brewer.models.ProfileFactory.ProfileInfo;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final String LOGGER_TAG = "TAGGG";
    public static final String DEVICE_ADDRESS_KEY = "deviceAddress";
    public static final String INTENT_ACTION_DISCONNECT = BuildConfig.APPLICATION_ID + ".Disconnect";
    public static final String NOTIFICATION_CHANNEL = BuildConfig.APPLICATION_ID + ".Channel";
    public static final String INTENT_CLASS_MAIN_ACTIVITY = BuildConfig.APPLICATION_ID + ".MainActivity";
    public static final int NOTIFY_MANAGER_START_FOREGROUND_SERVICE = 1001;
    public static final int REQUEST_BLUETOOTH_CONNECT = 111;


    public static List<String> mDataAmountOfWater = Arrays.asList("200", "250", "300", "350", "400", "450", "500");
    public static List<String> mDataTemperature = Arrays.asList("88c", "89c", "90c", "92c", "93c", "94c", "95c", "96c");
    public static List<String> mDataWater = Arrays.asList("10", "20", "30", "40", "50", "50", "60", "70", "80", "90");
    public static List<String> mDataSpeed = Arrays.asList("3", "4", "5", "6", "7", "8", "9");
    public static List<String> mDataPause = Arrays.asList("10", "20", "30", "40", "50", "60");

    public static String DEFAULT_PROFILE_NAME_KEY = "defaultProfileKey";
    public static final String DEFAULT_PROFILE_NAME_ID = "1";
    public static ProfileInfo DEFAULT_PROFILE_NAME_VALUE = new ProfileInfo(DEFAULT_PROFILE_NAME_ID, "Default", "default", "");
    public static String PROFILE1_NAME_KEY = "profile1Key";
    public static final String PROFILE1_NAME_KEY_ID = "2";
    public static ProfileInfo PROFILE1_NAME_VALUE = new ProfileInfo(PROFILE1_NAME_KEY_ID, "Profile 1", "profile1", "");
    public static String PROFILE2_NAME_KEY = "profile2Key";
    public static final String PROFILE2_NAME_ID = "3";
    public static ProfileInfo PROFILE2_NAME_VALUE = new ProfileInfo(PROFILE2_NAME_ID, "Profile 2", "profile2", "");
    public static String PROFILE3_NAME_KEY = "profile3Key";
    public static final String PROFILE3_NAME_ID = "4";
    public static ProfileInfo PROFILE3_NAME_VALUE = new ProfileInfo(PROFILE3_NAME_ID, "Profile 3", "profile3", "");
    public static String PROFILE4_NAME_KEY = "profile4Key";
    public static final String PROFILE4_NAME_ID = "5";
    public static ProfileInfo PROFILE4_NAME_VALUE = new ProfileInfo(PROFILE4_NAME_ID, "Profile 4", "profile4", "");
    public static String IS_DEFAULT_PROFILE_PREF_SET_KEY = "isDefaultProfilePrefSet";


    public static final String SELECTED_PROFILE_KEY = "selectedProfileKey";

}
