package com.deepfinch.common.utils;

import android.content.Context;
import android.os.Environment;
import android.text.format.Formatter;

import com.deepfinch.common.utils.log.DFLog;

import java.io.File;

/**
 * Copyright (c) 2018-2019 DEEPFINCH Corporation. All rights reserved.
 */

public class DFCommon {

    private static final String TAG = "DFCommon";

    public static void readSystem(Context context) {
        File sdcard_filedir = Environment.getExternalStorageDirectory();//得到sdcard的目录作为一个文件对象
        long usableSpace = sdcard_filedir.getUsableSpace();//获取文件目录对象剩余空间
        long totalSpace = sdcard_filedir.getTotalSpace();
        //将一个long类型的文件大小格式化成用户可以看懂的M，G字符串
        String usableSpace_str = Formatter.formatFileSize(context, usableSpace);
        String totalSpace_str = Formatter.formatFileSize(context, totalSpace);

        DFLog.i(TAG, "readSystem", "usableSpace_str", usableSpace_str);
        DFLog.i(TAG, "readSystem", "totalSpace_str", totalSpace_str);
    }
}
