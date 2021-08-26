package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppConfig {
    Context context;
    public static SharedPreferences sp;
    public static SharedPreferences.Editor spEdit;

    public AppConfig(Context context) {
        this.context = context;

    }

    public static SharedPreferences getSharedPreferencesInstance(Context context) {
        if (sp == null) {
            sp = PreferenceManager.getDefaultSharedPreferences(context);
            spEdit = sp.edit();
        }
        return sp;
    }

    public static boolean isUserLogin() {
        return sp.getInt("user_id", -1) != -1;
    }
    public void login(User user){
        spEdit.putInt("user_id",user.id);
        spEdit.putString("user_name",user.getName());
        spEdit.apply();
    }

    public void logout(){
        spEdit.remove("user_name");
        spEdit.putInt("user_id",-1);
        spEdit.apply();
    }
}
