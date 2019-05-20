package com.deepfinch.ndk.demo.adapter.operator;

import android.content.Context;
import android.content.Intent;

import com.deepfinch.ndk.demo.demo.DFCameraTestActivity;

/**
 * Copyright (c) 2018-2019 DEEPFINCH Corporation. All rights reserved.
 */

public class DFCameraDemoTestOperator implements DFTestListOperatorInterface {
    @Override
    public void onClick(Context context) {
        Intent intent = new Intent(context, DFCameraTestActivity.class);
        context.startActivity(intent);
    }
}
