package com.imrezwan.wise_brewer;

import static android.content.Context.MODE_PRIVATE;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefHelper {
    Context context;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    public static SharedPrefHelper sharedPrefHelper;

    public static SharedPrefHelper newInstance(Context context) {
        if(sharedPrefHelper == null) {
            sharedPrefHelper = new SharedPrefHelper(context);
        }
        return sharedPrefHelper;
    }

    public SharedPrefHelper(Context context) {
        this.context = context;
        this.pref = context.getSharedPreferences("CoffeePro", MODE_PRIVATE);
        this.editor = pref.edit();
    }

    public void setString(String key, String val) {
        this.editor.putString(key, val);
        editor.apply();
    }

    public String getString(String key, String defaultVal){
        return pref.getString(key, defaultVal);
    }
}
