package com.deepfinch.ndk.demo.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.deepfinch.ndk.demo.R;
import com.deepfinch.ndk.demo.base.DFBaseActivity;
import com.deepfinch.ndk.dfjnilib.jni.DFTransportBean;
import com.deepfinch.ndk.dfjnilib.jni.DFTransportDemo;
import com.deepfinch.ndk.dfjnilib.jni.DFTransportModel;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Copyright (c) 2018-2019 DEEPFINCH Corporation. All rights reserved.
 */

public class DFNDKTestActivity extends DFBaseActivity {
    @BindView(R.id.id_tv_ndk_show)
    TextView mTvNdkShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_ndk_test);
        bindView();
    }

    private String getIntArrayResult(int[] intArrayResult) {
        StringBuilder sb = new StringBuilder();
        if (intArrayResult != null) {
            for (int i = 0; i < intArrayResult.length; i++) {
                sb.append(intArrayResult[i])
                        .append(",");
            }
        }
        return sb.toString();
    }

    private String getFloatArrayResult(float[] floatArrayResult) {
        StringBuilder sb = new StringBuilder();
        if (floatArrayResult != null) {
            for (int i = 0; i < floatArrayResult.length; i++) {
                sb.append(floatArrayResult[i])
                        .append(",");
            }
        }
        return sb.toString();
    }

    private String getTransportModelDescription(DFTransportModel transportModel) {
        String description = "";
        if (transportModel != null) {
            description = transportModel.getDescription();
        }
        return description;
    }

    @OnClick(R.id.id_btn_ndk_test)
    public void onClickNdkTest() {
        DFTransportDemo transportDemo = new DFTransportDemo();
        DFTransportBean transportBean = transportDemo.getTransportBean();

        StringBuilder sb = new StringBuilder();
        sb.append("int结果:")
                .append(transportBean.getResultInt())
                .append("\n")
                .append("intArray结果:")
                .append(getIntArrayResult(transportBean.getResultIntArray()))
                .append("\n")
                .append("float结果:")
                .append(transportBean.getResultFloat())
                .append("\n")
                .append("floatArray结果:")
                .append(getFloatArrayResult(transportBean.getResultFloatArray()))
                .append("\n")
                .append("测试字符串===1:")
                .append(transportBean.getResultStringOne())
                .append("\n")
                .append("测试字符串===2:")
                .append(transportBean.getResultStringTwo())
                .append("\n")
                .append("字段一个类:")
                .append(getTransportModelDescription(transportBean.getTransportModel()))
                .append("\n");
        mTvNdkShow.setText(sb);
    }
}
