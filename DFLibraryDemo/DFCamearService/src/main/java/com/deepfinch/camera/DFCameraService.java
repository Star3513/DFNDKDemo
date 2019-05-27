package com.deepfinch.camera;

import android.view.SurfaceView;

/**
 * Copyright (c) 2018-2019 DEEPFINCH Corporation. All rights reserved.
 */

public interface DFCameraService {
    /**
     * 设置预览view
     *
     * @param surfaceView
     */
    void setSurfaceView(SurfaceView surfaceView);

    /**
     * 开启相机
     */
    void openCamera();

    /**
     * 开启相机预览
     */
    void startPreviewView();

    /**
     * 关闭相机预览
     */
    void stopPreviewView();

    /**
     * 关闭相机
     */
    void closeCamera();

    /**
     * 预览数据返回
     */
    void setPreviewCallbackWithBuffer(DFPreviewCameraCallback previewCameraCallback);
}
