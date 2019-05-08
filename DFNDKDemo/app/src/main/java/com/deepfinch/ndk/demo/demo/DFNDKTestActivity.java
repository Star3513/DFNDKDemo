package com.deepfinch.ndk.demo.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.deepfinch.ndk.demo.R;
import com.deepfinch.ndk.demo.base.DFBaseActivity;
import com.deepfinch.ndk.dfjnilib.jni.DFTransportBean;
import com.deepfinch.ndk.dfjnilib.jni.DFTransportDemo;

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

    @OnClick(R.id.id_btn_ndk_test)
    public void onClickNdkTest() {
        DFTransportDemo transportDemo = new DFTransportDemo();
        DFTransportBean transportBean = transportDemo.getTransportBean();

        StringBuilder sb = new StringBuilder();
        sb.append("int结果:")
                .append(transportBean.getResultInt())
                .append("\n")
                .append("float结果:")
                .append(transportBean.getResultFloat())
                .append("\n")
                .append("测试字符串===1:")
                .append(transportBean.getResultStringOne())
                .append("\n")
                .append("测试字符串===2:")
                .append(transportBean.getResultStringTwo());
        mTvNdkShow.setText(sb);
    }
}
