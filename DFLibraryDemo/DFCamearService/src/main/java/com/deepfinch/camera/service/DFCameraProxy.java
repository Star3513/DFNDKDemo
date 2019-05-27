package com.deepfinch.camera.service;

import android.hardware.Camera;
import android.support.annotation.NonNull;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.deepfinch.camera.DFCameraService;
import com.deepfinch.camera.DFPreviewCameraCallback;
import com.deepfinch.camera.config.DFCameraConfig;
import com.deepfinch.camera.config.DFCameraPreviewSize;
import com.deepfinch.camera.utils.DFCameraServiceUtils;

import java.io.IOException;
import java.util.List;

/**
 * Copyright (c) 2018-2019 DEEPFINCH Corporation. All rights reserved.
 */

public class DFCameraProxy implements DFCameraService, SurfaceHolder.Callback, Camera.PreviewCallback {
    private static final String TAG = "DFCameraProxy";

    private static final int CAMERA_CONNECT_TIMEOUT = 200;
    private static final int CAMERA_CONNECT_RETRY_INTERVAL = 50;

    private Camera mCamera;
    private DFCameraConfig mCameraConfig;
    private SurfaceHolder mPreviewHolder;
    private DFPreviewCameraCallback mPreviewCameraCallback;

    public DFCameraProxy() {
        mCameraConfig = new DFCameraConfig();
    }

    public DFCameraProxy(@NonNull DFCameraConfig cameraConfig) {
        this.mCameraConfig = cameraConfig;
    }

    @Override
    public void setSurfaceView(SurfaceView surfaceView) {
        mPreviewHolder = surfaceView.getHolder();
        mPreviewHolder.addCallback(this);
    }

    @Override
    public void openCamera() {
        if (mCamera == null) {
            mCamera = connectToCamera(CAMERA_CONNECT_RETRY_INTERVAL, CAMERA_CONNECT_TIMEOUT);
            if (mCamera != null) {
                initCameraParameters();
            }
        }
    }

    @Override
    public void startPreviewView() {
        if (mCamera != null) {
            try {
                mCamera.setPreviewDisplay(mPreviewHolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mCamera.setPreviewCallbackWithBuffer(this);
            mCamera.startPreview();
        }
    }

    @Override
    public void stopPreviewView() {
        if (mCamera != null) {
            mCamera.setPreviewCallbackWithBuffer(null);
            mCamera.stopPreview();
        }
    }

    @Override
    public void closeCamera() {
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    @Override
    public void setPreviewCallbackWithBuffer(DFPreviewCameraCallback previewCameraCallback) {
        mPreviewCameraCallback = previewCameraCallback;
    }

    /**
     * 连接相机
     *
     * @param checkInterval
     * @param maxTimeout
     * @return
     */
    private Camera connectToCamera(int checkInterval, int maxTimeout) {
        long start = System.currentTimeMillis();
        do {
            try {
                int numberOfCameras = Camera.getNumberOfCameras();
                Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                for (int i = 0; i < numberOfCameras; i++) {
                    Camera.getCameraInfo(i, cameraInfo);
                    if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                        return openCamera(i);
                    }
                }
            } catch (RuntimeException e) {
                try {
                    DFCameraServiceUtils.logI(TAG, String.format("暂时无法启用摄像头，等待%d毫秒后重试...", checkInterval));
                    Thread.sleep(checkInterval);
                } catch (InterruptedException e1) {
                    DFCameraServiceUtils.logE(TAG, "等待启用摄像头过程中出现异常", e1);
                }
            } catch (Exception e) {
                DFCameraServiceUtils.logE(TAG, "发生了未知错误，请与我们联系 https://www.deepfinch.cn", e);
                maxTimeout = 0;
            }

        } while (System.currentTimeMillis() - start < maxTimeout);
        return null;
    }

    private Camera openCamera(int cameraId) {
        Camera camera = Camera.open(cameraId);
        try {
            Camera.Parameters parameters = camera.getParameters();
            camera.setParameters(parameters);
        } catch (Exception e) {
            camera = null;
        }
        return camera;
    }

    /**
     * 初始化相机参数
     */
    private void initCameraParameters() {
        int rotation = mCameraConfig.getRotation();
        int cameraId = mCameraConfig.getCameraId();
        DFCameraServiceUtils.logI(TAG, "initCameraParameters", "rotation", rotation);
        DFCameraServiceUtils.setCameraDisplayOrientation(rotation, cameraId, mCamera);

        Camera.Parameters parameters = mCamera.getParameters();
        String flatten = parameters.flatten();
        DFCameraServiceUtils.logI(TAG, "flatten", flatten);

        String bestFocusModel = findBestFocusModel(parameters);
        parameters.setFocusMode(bestFocusModel);

        DFCameraPreviewSize previewSize = findBestPreviewSize(parameters);
        DFCameraServiceUtils.logI(TAG, "PreviewWidth", previewSize.getPreviewWidth(), "PreviewHeight", previewSize.getPreviewHeight());
        parameters.setPreviewSize(previewSize.getPreviewWidth(), previewSize.getPreviewHeight());
        mCamera.setParameters(parameters);
    }

    private String findBestFocusModel(Camera.Parameters parameters) {
        String focusModel = null;
        List<String> focusModeList = mCameraConfig.getFocusModeList();
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        for (String tempFocusModel : focusModeList) {
            if (supportedFocusModes.contains(tempFocusModel)) {
                focusModel = tempFocusModel;
                break;
            }
        }
        return focusModel;
    }

    private DFCameraPreviewSize findBestPreviewSize(Camera.Parameters parameters) {
        DFCameraPreviewSize previewSize = null;
        List<DFCameraPreviewSize> previewSizeList = mCameraConfig.getPreviewSizeList();
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        for (DFCameraPreviewSize size : previewSizeList) {
            if (isSupportPreviewSize(supportedPreviewSizes, size)) {
                previewSize = size;
                break;
            }
        }
        return previewSize;
    }

    public boolean isSupportPreviewSize(List<Camera.Size> supportedPreviewSizes, DFCameraPreviewSize previewSize) {
        boolean isSupport = false;
        if (supportedPreviewSizes != null) {
            int size = supportedPreviewSizes.size();
            for (int i = 0; i < size; i++) {
                Camera.Size supportedPreviewSize = supportedPreviewSizes.get(i);
                if (supportedPreviewSize.width == previewSize.getPreviewWidth()
                        && supportedPreviewSize.height == previewSize.getPreviewHeight()) {
                    isSupport = true;
                    break;
                }
            }
        }
        return isSupport;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        DFCameraServiceUtils.logI(TAG, "surfaceCreated");
        openCamera();
        startPreviewView();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        DFCameraServiceUtils.logI(TAG, "surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        DFCameraServiceUtils.logI(TAG, "surfaceDestroyed");
        stopPreviewView();
        closeCamera();
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        if (mPreviewCameraCallback != null) {
            mPreviewCameraCallback.onPreviewFrame(data);
        }
    }
}
