package com.deepfinch.ndk.dfjnilib.jni;

/**
 * Copyright (c) 2018-2019 DEEPFINCH Corporation. All rights reserved.
 */

public class DFTransportBean {
    private int resultInt;
    private int[] resultIntArray;
    private float resultFloat;
    private float[] resultFloatArray;
    private String resultStringOne;
    private String resultStringTwo;
    private DFTransportModel transportModel;

    public int getResultInt() {
        return resultInt;
    }

    public void setResultInt(int resultInt) {
        this.resultInt = resultInt;
    }

    public int[] getResultIntArray() {
        return resultIntArray;
    }

    public void setResultIntArray(int[] resultIntArray) {
        this.resultIntArray = resultIntArray;
    }

    public float getResultFloat() {
        return resultFloat;
    }

    public void setResultFloat(float resultFloat) {
        this.resultFloat = resultFloat;
    }

    public String getResultStringOne() {
        return resultStringOne;
    }

    public void setResultStringOne(String resultStringOne) {
        this.resultStringOne = resultStringOne;
    }

    public String getResultStringTwo() {
        return resultStringTwo;
    }

    public void setResultStringTwo(String resultStringTwo) {
        this.resultStringTwo = resultStringTwo;
    }

    public float[] getResultFloatArray() {
        return resultFloatArray;
    }

    public void setResultFloatArray(float[] resultFloatArray) {
        this.resultFloatArray = resultFloatArray;
    }

    public DFTransportModel getTransportModel() {
        return transportModel;
    }

    public void setTransportModel(DFTransportModel transportModel) {
        this.transportModel = transportModel;
    }
}
