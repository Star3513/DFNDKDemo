package com.deepfinch.common.utils.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.linkface.common.utils.R;

/**
 * Created by linkface on 2017/2/22.
 */

public class DFToastUtils {

    public static final int DURATION_DEFAULT = 2500;

    /**
     * 单例
     */
    static private DFToastUtils instance;
    private static Toast sTextToast;
    private static Toast sViewToast;
    static byte[] mLocker = new byte[0];


    static public DFToastUtils getInstance() {
        synchronized (mLocker) {

            if (instance == null) {
                instance = new DFToastUtils();
            }
        }
        return instance;
    }

    /**
     * 初始化上下文和布局
     */
    static int toastLayout;
    static Context mCtx;
    static Handler sHandler;

    public void setContext(Context context) {
        setContext(context, R.layout.commonutils_toast_utils);
    }

    public void setContext(Context context, int layout) {
        if (!isMainThread()) {
            throw new RuntimeException("DFToastUtils::setContext must be in UI thread");
        }
        mCtx = context;
        toastLayout = layout;
        sHandler = new Handler(Looper.getMainLooper());
    }

    static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    static boolean useSysToast = false;

    private static final int LENGTH_LIMIT = 8;      //超过8个字则显示更长时间
    private static final int DURATION_UNSET = -1;   //未设定时长，按文本长度控制时长

    public static void showNetworkError() {
        show("网络繁忙，请稍后重试", DURATION_UNSET, null);
    }

    /**
     * 最简单的showToast
     */
    public static void show(CharSequence text) {
        show(text, DURATION_UNSET, null);
    }

    public static void show(View view) {
        show("", DURATION_UNSET, view);
    }

    public static void show(CharSequence text, View view) {
        show(text, DURATION_UNSET, view);
    }

    public static void show(int stringResourceId, View view) {
        if (mCtx != null) {
            show(mCtx.getResources().getString(stringResourceId), view);
        }
    }

    /**
     * 带背景布局的showToast
     */
    public static void show(View view, int drawableId, CharSequence text) {

    }

    /**
     * 增加显示时长
     */
    public static void show(CharSequence text, int duration, View view) {
        show(text, duration, Gravity.NO_GRAVITY, 0, view);
    }

    /**
     * 增加位置属性：marginDp代表距离的dp值
     * 如果Gravity是Center，marginDp代表往下移
     * 如果Gravity是Bottom，marginDp代表距离底边
     * 如果Gravity是Top，marginDp代表距离上边
     */
    public static void show(final CharSequence text, int duration, int gravity, int marginDp, View view) {
        show(text, duration, gravity, marginDp, 0, view);
    }

    /**
     * 增加延时
     */
    public static void show(final CharSequence text, final int duration,
                            final int gravity, final int marginDp, int delay, final View view) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showSysToast(mCtx, text, duration, gravity, marginDp, view);
            }
        }, delay);
    }

    /**
     * 主线程运行，并附带延时
     */
    private static void runOnUiThread(Runnable runnable, int delay) {
        if (delay <= 0 && isMainThread()) {
            runnable.run();
        } else {
            sHandler.postDelayed(runnable, delay);
        }
    }


    /**
     * 调用系统Toast
     */
    private static void showSysToast(final Context context, CharSequence text, int duration,
                                     final int gravity, final int marginDp, View view) {
        if (duration > DURATION_DEFAULT) {   //大于默认值是长消息
            duration = Toast.LENGTH_LONG;
        } else if (duration < 0) {                           //值小于零，文本超长是长消息，短的是短消息
            if (!TextUtils.isEmpty(text) && text.length() > LENGTH_LIMIT) {
                duration = Toast.LENGTH_LONG;
            } else {
                duration = Toast.LENGTH_SHORT;
            }
        } else if (duration == Toast.LENGTH_LONG) {          //原本是长的，设定为长的
            duration = Toast.LENGTH_LONG;
        } else {                                            //其余情况都认为是短的
            duration = Toast.LENGTH_SHORT;
        }

        if (sViewToast == null) {
            sViewToast = Toast.makeText(context, text, duration);
        }
        if (sTextToast == null) {
            sTextToast = Toast.makeText(context, text, duration);
        }
        Toast toast = null;
        if (view != null) {
            toast = sViewToast;
        } else {
            toast = sTextToast;
        }

        if (view != null) {
            toast.setView(view);
        } else {
            if (!TextUtils.isEmpty(text)) {
                toast.setText(text);
            } else {
                toast.setText("");
            }
        }

        if (gravity != Gravity.NO_GRAVITY) {
            float screenDensityDpi = context.getResources().getDisplayMetrics().densityDpi;
            int yOffset = dip2px(screenDensityDpi, marginDp);
            toast.setGravity(gravity, 0, yOffset);
        }
        toast.show();
    }

    public static int dip2px(float screenDensityDpi, float dpValue) {
        return (int) (dpValue * (screenDensityDpi / 160));
    }

    public static void removeAllToast() {
        if (sViewToast != null) {
            sViewToast.cancel();
        }
        if (sTextToast != null) {
            sTextToast.cancel();
        }
    }

}
