package com.deepfinch.ndk.demo.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Copyright (c) 2018-2019 DEEPFINCH Corporation. All rights reserved.
 */

public class PermissionRequestUtils {
    private static final int PERMISSION_REQUEST_CAMERA = 0;

    public static void requestCameraPermission(Activity context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Permission has not been granted and must be
            // requested.

            if (ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.CAMERA)) {
                // Provide an additional rationale to the user if
                // the permission was not granted
                // and the user would benefit from additional
                // context for the use of the permission.
            }
            // Request the permission. The result will be received
            // in onRequestPermissionResult()
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.CAMERA},
                    PERMISSION_REQUEST_CAMERA);
        }

    }
}
