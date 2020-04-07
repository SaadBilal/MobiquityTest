package com.mobiquity.mobiquityproducts.utils;


import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.security.PrivateKey;

/**
 * Created for AER Android App by Infiniun Healthcare.
 */

public class SharedPrefsHelper {

    public static final String PREF_KEY_NAME = "syne_shared_pref_name";
    public static final String CHANGES_SAVED = "SharedPrefsHelper";
    private SharedPreferences mSharedPreferences;

    public SharedPrefsHelper(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }
    public void put(String key, String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }

    public void put(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).apply();
    }

    public void put(String key, float value) {
        mSharedPreferences.edit().putFloat(key, value).apply();
    }

    public void put(PrivateKey key, String defaultValue) {

    }

    public void put(String key, boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public String get(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public Integer get(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public Float get(String key, float defaultValue) {
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    public Boolean get(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public void deleteSavedData(String key) {
        mSharedPreferences.edit().remove(key).apply();
    }


    public void setObject(String key, Object value) {
        Gson gson = new Gson();
        String json = gson.toJson(value);
        mSharedPreferences.edit().putString(key, json).commit();

    }

    public Object getObject(String key, Class<?> expectedClass) {
        Gson gson = new Gson();
        String json = mSharedPreferences.getString(key, "");
        // Type collectionType = new TypeToken<passedObj>(){}.getType();
        Object obj = gson.fromJson(json, expectedClass);
        return obj;
    }
}
