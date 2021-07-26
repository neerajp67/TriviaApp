package com.example.triviaapp.database;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    SharedPreferences sharedPreferences;
    private Context context;

    public SharedPrefManager(Context context){
        this.context = context;
    }

    public String getPref(String key){
        sharedPreferences = context.getSharedPreferences
                ("com.example.triviaapp", Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    public void putPref(String key, String value){
        sharedPreferences = context.getSharedPreferences
                ("com.example.triviaapp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
}