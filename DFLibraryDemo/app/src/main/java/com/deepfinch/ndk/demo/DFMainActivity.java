package com.deepfinch.ndk.demo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.deepfinch.ndk.demo.adapter.DFTestListAdapter;
import com.deepfinch.ndk.demo.adapter.bean.DFTestListBean;
import com.deepfinch.ndk.demo.adapter.operator.DFCameraDemoTestOperator;
import com.deepfinch.ndk.demo.adapter.operator.DFNDKDemoTestOperator;
import com.deepfinch.ndk.demo.base.DFBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DFMainActivity extends DFBaseActivity {
    @BindView(R.id.id_rv_test_list)
    RecyclerView mRvTestView;

    private DFTestListAdapter mTestListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_main);
        bindView();

        init();
    }

    private void init(){
        initView();
        initData();
    }

    private void initView(){
        mRvTestView.setLayoutManager(new LinearLayoutManager(this));
        mTestListAdapter = new DFTestListAdapter(this);
        mRvTestView.setAdapter(mTestListAdapter);
    }

    private void initData(){
        List<DFTestListBean> testListBeanList = new ArrayList<>();
        DFTestListBean testListBean = new DFTestListBean("NDK测试", new DFNDKDemoTestOperator());
        DFTestListBean testCameraBean = new DFTestListBean("相机测试", new DFCameraDemoTestOperator());
        testListBeanList.add(testListBean);
        testListBeanList.add(testCameraBean);
        mTestListAdapter.refreshData(testListBeanList);
    }
}
