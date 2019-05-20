package com.deepfinch.common.utils.view;

import android.content.Context;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.deepfinch.common.utils.log.DFLog;

/**
 * Copyright (c) 2017-2018 LINKFACE Corporation. All rights reserved.
 */

public class DFScreenUtils {

    public static final String TAG = "DFScreenUtils";

    public static int sScreenWidth;
    public static int sScreenHeight;
    public static float sScreenDensityDpi;

    public static void initialize(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        display.getRealMetrics(dm);
        sScreenWidth = dm.widthPixels;
        sScreenHeight = dm.heightPixels;
        sScreenDensityDpi = context.getResources().getDisplayMetrics().densityDpi;

        DFLog.i(TAG, "sScreenWidth", sScreenWidth);
        DFLog.i(TAG, "sScreenHeight", sScreenHeight);
        DFLog.i(TAG, "sScreenDensityDpi", sScreenDensityDpi);

    }

    public static int dip2px(float dpValue) {
        return (int) (dpValue * (sScreenDensityDpi / 160));
    }

    public static float px2dip(int px) {
        return px * 160 / sScreenDensityDpi;
    }

    /**
     * 设置屏幕常亮
     */
    public static void setScreenLighted(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "mylock");

        wakeLock.setReferenceCounted(false);
        wakeLock.acquire();     // 保持屏幕常亮
//        wakeLock.release();     //解除常亮限制
    }

    /**
     * 取消屏幕常亮
     */
    public static void removeScreenLighted(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "mylock");

        wakeLock.setReferenceCounted(false);
//        wakeLock.acquire();     // 保持屏幕常亮
        wakeLock.release();     //解除常亮限制
    }


}
