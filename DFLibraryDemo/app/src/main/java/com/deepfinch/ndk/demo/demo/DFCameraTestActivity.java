package com.deepfinch.ndk.demo.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.SurfaceView;

import com.deepfinch.camera.DFCameraService;
import com.deepfinch.camera.config.DFCameraConfig;
import com.deepfinch.camera.config.DFCameraConfigFactory;
import com.deepfinch.camera.service.DFCameraProxy;
import com.deepfinch.ndk.demo.R;
import com.deepfinch.ndk.demo.base.DFBaseActivity;

import butterknife.BindView;

/**
 * Copyright (c) 2018-2019 DEEPFINCH Corporation. All rights reserved.
 */

public class DFCameraTestActivity extends DFBaseActivity {

    @BindView(R.id.sv_camera_preview)
    SurfaceView mSvCameraPreview;

    private DFCameraService mCameraService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_camera_test);
        bindView();

        initCamera();
    }

    private void initCamera() {
        DFCameraConfig cameraConfig = DFCameraConfigFactory.createCameraConfig();
        mCameraService = new DFCameraProxy(cameraConfig);
        mCameraService.setSurfaceView(mSvCameraPreview);
    }
}
