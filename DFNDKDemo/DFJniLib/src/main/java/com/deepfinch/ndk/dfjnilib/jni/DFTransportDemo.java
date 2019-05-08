package com.deepfinch.ndk.dfjnilib.jni;

/**
 * Copyright (c) 2018-2019 DEEPFINCH Corporation. All rights reserved.
 */

public class DFTransportDemo {
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    public native int getCurrentTimeHour();
    public native DFTransportBean getTransportBean();
}
