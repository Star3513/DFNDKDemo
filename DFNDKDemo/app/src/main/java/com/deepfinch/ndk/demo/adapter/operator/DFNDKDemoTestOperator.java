package com.deepfinch.ndk.demo.adapter.operator;

import android.content.Context;
import android.content.Intent;

import com.deepfinch.ndk.demo.demo.DFNDKTestActivity;

/**
 * Copyright (c) 2018-2019 DEEPFINCH Corporation. All rights reserved.
 */

public class DFNDKDemoTestOperator implements DFTestListOperatorInterface {
    @Override
    public void onClick(Context context) {
        Intent intent = new Intent(context, DFNDKTestActivity.class);
        context.startActivity(intent);
    }
}
