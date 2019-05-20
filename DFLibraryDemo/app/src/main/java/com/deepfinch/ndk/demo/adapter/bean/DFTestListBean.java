package com.deepfinch.ndk.demo.adapter.bean;

import android.content.Context;

import com.deepfinch.ndk.demo.adapter.operator.DFTestListOperatorInterface;

/**
 * Copyright (c) 2018-2019 DEEPFINCH Corporation. All rights reserved.
 */

public class DFTestListBean {
    private String testShow;
    private DFTestListOperatorInterface testListOperator;

    public DFTestListBean() {
    }

    public DFTestListBean(String testShow) {
        this.testShow = testShow;
    }

    public DFTestListBean(String testShow, DFTestListOperatorInterface testListOperator) {
        this.testShow = testShow;
        this.testListOperator = testListOperator;
    }

    public void onClick(Context context){
        if (testListOperator != null){
            testListOperator.onClick(context);
        }
    }

    public String getTestShow() {
        return testShow;
    }

    public void setTestShow(String testShow) {
        this.testShow = testShow;
    }

    public DFTestListOperatorInterface getTestListOperator() {
        return testListOperator;
    }

    public void setTestListOperator(DFTestListOperatorInterface testListOperator) {
        this.testListOperator = testListOperator;
    }
}
