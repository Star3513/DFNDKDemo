package com.deepfinch.common.utils.view;

import android.view.View;

/**
 * Created by Hanlz on 2017/4/11.
 */

public class DFViewUtils {
    public static void refreshVisibility(View view, boolean isShow){
        if (view != null){
            view.setVisibility(isShow ? View.VISIBLE : View.GONE);
        }
    }

    public static void refreshInVisible(View view, boolean isShow){
        if (view != null){
            view.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
        }
    }
}
