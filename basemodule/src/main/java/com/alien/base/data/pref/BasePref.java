package com.alien.base.data.pref;

import android.content.Context;
import android.content.SharedPreferences;

public class BasePref {

    public static final String PREF_APP = "_app";
    public static final String PREF_USER = "_user";
    private SharedPreferences mPreferences;

    public void init(Context context, String spName){
        mPreferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    public void recycle(){
        mPreferences = null;
    }

    public String getString(String key){
        return mPreferences.getString(key, "");
    }

    public boolean getBoolean(String key){
        return mPreferences.getBoolean(key, false);
    }

    public int getInt(String key){
        return mPreferences.getInt(key, 0);
    }

    public long getLong(String key){
        return mPreferences.getLong(key, 0);
    }

    public float getFloat(String key){
        return mPreferences.getFloat(key, 0f);
    }

    public void putLong(String key, long value){
        mPreferences.edit().putLong(key, value).apply();
    }

    public void putFloat(String key, float value){
        mPreferences.edit().putFloat(key, value).apply();
    }

    public void putString(String key, String value){
        mPreferences.edit().putString(key, value).apply();
    }

    public void putInt(String key, int value){
        mPreferences.edit().putInt(key, value).apply();
    }

    public void putBoolean(String key, boolean value){
        mPreferences.edit().putBoolean(key, value).apply();
    }

    public void remove(String key){
        mPreferences.edit().remove(key).apply();
    }

    public void clear(){
        mPreferences.edit().clear().apply();
    }
}
