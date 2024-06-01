package com.imrezwan.wise_brewer;

import static android.content.Context.MODE_PRIVATE;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.imrezwan.wise_brewer.models.ProfileFactory.ProfileInfo;
import com.imrezwan.wise_brewer.utils.Constants;

import java.util.ArrayList;

public class SharedPrefHelper {
    Context context;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    public static SharedPrefHelper sharedPrefHelper;
    private final Gson gson;

    public static SharedPrefHelper newInstance(Context context) {
        if(sharedPrefHelper == null) {
            sharedPrefHelper = new SharedPrefHelper(context);
        }
        return sharedPrefHelper;
    }

    public SharedPrefHelper(Context context) {
        this.context = context;
        this.gson = new Gson();
        this.pref = context.getSharedPreferences("WiSE_Brewer", MODE_PRIVATE);
        this.editor = pref.edit();
    }

    public void setString(String key, String val) {
        this.editor.putString(key, val);
        editor.apply();
    }

    public String getString(String key, String defaultVal){
        return pref.getString(key, defaultVal);
    }

    public void setDefaultPreferences() {
        boolean isDefaultPrefSet = pref.getBoolean(Constants.IS_DEFAULT_PROFILE_PREF_SET_KEY, false);
        if (!isDefaultPrefSet) {
            editor.putString(Constants.DEFAULT_PROFILE_NAME_KEY, gson.toJson(Constants.DEFAULT_PROFILE_NAME_VALUE));
            editor.putString(Constants.PROFILE1_NAME_KEY, gson.toJson(Constants.PROFILE1_NAME_VALUE));
            editor.putString(Constants.PROFILE2_NAME_KEY, gson.toJson(Constants.PROFILE2_NAME_VALUE));
            editor.putString(Constants.PROFILE3_NAME_KEY, gson.toJson(Constants.PROFILE3_NAME_VALUE));
            editor.putString(Constants.PROFILE4_NAME_KEY, gson.toJson(Constants.PROFILE4_NAME_VALUE));

            editor.putBoolean(Constants.IS_DEFAULT_PROFILE_PREF_SET_KEY, true);

            editor.apply();
        }
    }

    public ProfileInfo getProfileInfo(String key) {
        return gson.fromJson(pref.getString(key, null), ProfileInfo.class);
    }

    public void setProfileInfo(ProfileInfo profileInfo) {
        String key = "";
        switch (profileInfo.getId()) {
            case Constants.DEFAULT_PROFILE_NAME_ID:
                editor.putString(Constants.DEFAULT_PROFILE_NAME_KEY, gson.toJson(profileInfo));
                break;
            case Constants.PROFILE1_NAME_KEY_ID:
                editor.putString(Constants.PROFILE1_NAME_KEY, gson.toJson(profileInfo));
                break;
            case Constants.PROFILE2_NAME_ID:
                editor.putString(Constants.PROFILE2_NAME_KEY, gson.toJson(profileInfo));
                break;
            case Constants.PROFILE3_NAME_ID:
                editor.putString(Constants.PROFILE3_NAME_KEY, gson.toJson(profileInfo));
                break;
            case Constants.PROFILE4_NAME_ID:
                editor.putString(Constants.PROFILE4_NAME_KEY, gson.toJson(profileInfo));
                break;
            default:
                break;
        }
        editor.apply();
    }
}
