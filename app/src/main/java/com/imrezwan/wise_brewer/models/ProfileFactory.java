package com.imrezwan.wise_brewer.models;

import android.content.Context;
import android.util.Log;

import com.imrezwan.wise_brewer.SharedPrefHelper;
import com.imrezwan.wise_brewer.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class ProfileFactory {
    private SharedPrefHelper sharedPrefHelper;

    public ProfileFactory(Context context) {
        this.sharedPrefHelper = new SharedPrefHelper(context);
    }

    public ArrayList<ProfileInfo> getProfileInfoList() {
        ArrayList<ProfileInfo> profileInfos = new ArrayList<>();
        profileInfos.add(sharedPrefHelper.getProfileInfo(Constants.DEFAULT_PROFILE_NAME_KEY));
        profileInfos.add(sharedPrefHelper.getProfileInfo(Constants.PROFILE1_NAME_KEY));
        profileInfos.add(sharedPrefHelper.getProfileInfo(Constants.PROFILE2_NAME_KEY));
        profileInfos.add(sharedPrefHelper.getProfileInfo(Constants.PROFILE3_NAME_KEY));
        profileInfos.add(sharedPrefHelper.getProfileInfo(Constants.PROFILE4_NAME_KEY));
        return profileInfos;
    }

    public static class ProfileInfo {
        private String id;
        private String displayTitle;
        private String bluetoothValue;
        private String details;

        public ProfileInfo(String id, String displayTitle, String bluetoothValue, String details) {
            this.id = id;
            this.displayTitle = displayTitle;
            this.bluetoothValue = bluetoothValue;
            this.details = details;
        }

        public String getId() {
            return id;
        }

        public String getDisplayTitle() {
            return displayTitle;
        }

        public void setDisplayTitle(String newDisplayTitle) {
            this.displayTitle = newDisplayTitle;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setBluetoothValue(String bluetoothValue) {
            this.bluetoothValue = bluetoothValue;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getBluetoothValue() {
            return bluetoothValue;
        }

        public String getDetails() {
            return details;
        }



        @Override
        public String toString() {
            return "ProfileInfo{" +
                    "id='" + id + '\'' +
                    ", displayTitle='" + displayTitle + '\'' +
                    ", bluetoothValue='" + bluetoothValue + '\'' +
                    ", details='" + details + '\'' +
                    '}';
        }
    }
}