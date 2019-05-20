package com.deepfinch.common.utils.sp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Copyright (c) 2017-2018 LINKFACE Corporation. All rights reserved.
 */

public class DFSpUtils {
    public static final String DF_SP_AI_SCANNER = "df_sp_ai_scanner";

    private static SharedPreferences sSharedPreferences = null;

    public static void init(Context context) {
        if (context != null) {
            sSharedPreferences = context.getSharedPreferences(DF_SP_AI_SCANNER, Activity.MODE_PRIVATE);
        }
    }

    public static void saveString(String key, String value) {
        if (sSharedPreferences != null) {
            sSharedPreferences.edit().putString(key, value)
                    .commit();
        }
    }

    public static String getString(String key) {
        String result = "";
        if (sSharedPreferences != null) {
            result = sSharedPreferences.getString(key, "");
        }
        return result;
    }

    public static void remove(String key) {
        if (sSharedPreferences != null) {
            sSharedPreferences.edit().remove(key)
                    .commit();
        }
    }

    public static void saveInt(String key, int value) {
        if (sSharedPreferences != null) {
            sSharedPreferences.edit().putInt(key, value)
                    .commit();
        }
    }

    public static int getInt(String key) {
        int result = 0;
        if (sSharedPreferences != null) {
            result = sSharedPreferences.getInt(key, 0);
        }
        return result;
    }

    public static int getInt(String key, int defaultValue) {
        int result = defaultValue;
        if (sSharedPreferences != null) {
            result = sSharedPreferences.getInt(key, defaultValue);
        }
        return result;
    }

    public static void saveBoolean(String key, boolean value) {
        if (sSharedPreferences != null) {
            sSharedPreferences.edit().putBoolean(key, value)
                    .commit();
        }
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        boolean result = defaultValue;
        if (sSharedPreferences != null) {
            result = sSharedPreferences.getBoolean(key, defaultValue);
        }
        return result;
    }

    public static void saveFloat(String key, float value) {
        if (sSharedPreferences != null) {
            sSharedPreferences.edit().putFloat(key, value)
                    .commit();
        }
    }

    public static float getFloat(String key) {
        float result = 0f;
        if (sSharedPreferences != null) {
            result = sSharedPreferences.getFloat(key, 0f);
        }
        return result;
    }

    public static float getFloat(String key, float defaultVaule) {
        float result = defaultVaule;
        if (sSharedPreferences != null) {
            result = sSharedPreferences.getFloat(key, defaultVaule);
        }
        return result;
    }

    public static void saveLong(String key, long value) {
        if (sSharedPreferences != null) {
            sSharedPreferences.edit().putLong(key, value)
                    .commit();
        }
    }

    public static long getLong(String key, long defaultVaule) {
        long result = defaultVaule;
        if (sSharedPreferences != null) {
            result = sSharedPreferences.getLong(key, defaultVaule);
        }
        return result;
    }

}
