package com.deepfinch.ndk.demo.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.deepfinch.ndk.demo.R;
import com.deepfinch.ndk.demo.base.DFBaseActivity;

/**
 * Copyright (c) 2018-2019 DEEPFINCH Corporation. All rights reserved.
 */

public class DFCameraTestActivity extends DFBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_camera_test);
        bindView();
    }
}
