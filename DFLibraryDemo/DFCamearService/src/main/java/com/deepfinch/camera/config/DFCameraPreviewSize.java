package com.deepfinch.camera.config;

/**
 * Copyright (c) 2018-2019 DEEPFINCH Corporation. All rights reserved.
 */

public class DFCameraPreviewSize {
    private int previewWidth;
    private int previewHeight;

    public DFCameraPreviewSize(int previewWidth, int previewHeight) {
        this.previewWidth = previewWidth;
        this.previewHeight = previewHeight;
    }

    public int getPreviewWidth() {
        return previewWidth;
    }

    public void setPreviewWidth(int previewWidth) {
        this.previewWidth = previewWidth;
    }

    public int getPreviewHeight() {
        return previewHeight;
    }

    public void setPreviewHeight(int previewHeight) {
        this.previewHeight = previewHeight;
    }

    @Override
    public String toString() {
        return "DFCameraPreviewSize{" +
                "previewWidth=" + previewWidth +
                ", previewHeight=" + previewHeight +
                '}';
    }
}
