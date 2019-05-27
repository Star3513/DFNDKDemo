package com.deepfinch.camera.config;

import android.hardware.Camera;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2018-2019 DEEPFINCH Corporation. All rights reserved.
 */

public class DFCameraConfig {
    private int mCameraId;
    private int mRotation;
    /**
     * 预览尺寸，有顺序，从第一个开始找到一个适合的预览尺寸
     */
    private List<DFCameraPreviewSize> mPreviewSizeList;
    private List<String> mFocusModeList;

    public DFCameraConfig() {
        mCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
        initPreviewSizeList();
        initFocusModeList();
    }

    private void initPreviewSizeList(){
        mPreviewSizeList = new ArrayList<>();
        mPreviewSizeList.add(new DFCameraPreviewSize(1280, 720));
        mPreviewSizeList.add(new DFCameraPreviewSize(1920, 1080));
        mPreviewSizeList.add(new DFCameraPreviewSize(640, 480));
    }

    private void initFocusModeList(){
        mFocusModeList = new ArrayList<>();
        mFocusModeList.add(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
        mFocusModeList.add(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        mFocusModeList.add(Camera.Parameters.FOCUS_MODE_AUTO);
    }

    public int getCameraId() {
        return mCameraId;
    }

    public void setCameraId(int cameraId) {
        this.mCameraId = cameraId;
    }

    public int getRotation() {
        return mRotation;
    }

    public void setRotation(int rotation) {
        this.mRotation = rotation;
    }

    public List<DFCameraPreviewSize> getPreviewSizeList() {
        return mPreviewSizeList;
    }

    public void setPreviewSizeList(List<DFCameraPreviewSize> mPreviewSizeList) {
        this.mPreviewSizeList = mPreviewSizeList;
    }

    public List<String> getFocusModeList() {
        return mFocusModeList;
    }

    public void setFocusModeList(List<String> focusModeList) {
        this.mFocusModeList = focusModeList;
    }
}
