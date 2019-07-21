package com.nszx.bbs.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public final class SharedPreferencesUtils {
    private static SharedPreferencesUtils mInstance;
    private static final String FILE_NAME = "BBS";

    private SharedPreferencesUtils() {
    }

    public static SharedPreferencesUtils getInstance() {
        if (mInstance == null) {
            synchronized (SharedPreferencesUtils.class) {
                if (mInstance == null) {
                    mInstance = new SharedPreferencesUtils();
                }
            }
        }
        return mInstance;
    }

    public void putShared(Context context, String key, Object value) {
        String type = value.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) value);
        } else if ("String".equals(type)) {
            editor.putString(key, (String) value);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (boolean) value);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (long) value);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (float) value);
        }
        editor.apply();
    }

    public Object get(Context context, String key, Object defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        String type = defValue.getClass().getSimpleName();
        if ("Integer".equals(type)) {
            sharedPreferences.getInt(key, (Integer) defValue);
        } else if ("String".equals(type)) {
            sharedPreferences.getString(key, (String) defValue);
        } else if ("Boolean".equals(type)) {
            sharedPreferences.getBoolean(key, (boolean) defValue);
        } else if ("Long".equals(type)) {
            sharedPreferences.getLong(key, (long) defValue);
        } else if ("Float".equals(type)) {
            sharedPreferences.getFloat(key, (float) defValue);
        }
        return null;
    }
}
