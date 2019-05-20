package com.deepfinch.ndk.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deepfinch.common.utils.collect.DFListUtils;
import com.deepfinch.common.utils.log.DFLog;
import com.deepfinch.ndk.demo.R;
import com.deepfinch.ndk.demo.adapter.bean.DFTestListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2018-2019 DEEPFINCH Corporation. All rights reserved.
 */

public class DFTestListAdapter extends RecyclerView.Adapter<DFTestListAdapter.TestListViewHolder> {
    private static final String TAG = "DFTestListAdapter";

    private List<DFTestListBean> mTestListBeanList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public DFTestListAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void refreshData(List<DFTestListBean> data) {
        mTestListBeanList = DFListUtils.refreshData(mTestListBeanList, data);
        notifyDataSetChanged();
    }

    @Override
    public TestListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.app_recycler_item_main_test_list, null);
        TestListViewHolder viewHolder = new TestListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TestListViewHolder holder, int position) {
        DFTestListBean testListBean = mTestListBeanList.get(position);

        holder.mTvTestShow.setText(testListBean.getTestShow());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickPosition = (int) v.getTag();
                DFLog.i(TAG, "clickPosition", clickPosition);
                DFTestListBean clickListBean = mTestListBeanList.get(clickPosition);
                clickListBean.onClick(mContext);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTestListBeanList == null ? 0 : mTestListBeanList.size();
    }

    class TestListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.id_test_show)
        TextView mTvTestShow;

        public TestListViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
