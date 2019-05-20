package com.deepfinch.ndk.demo.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Copyright (c) 2019-2020 DeepFinch Corporation. All rights reserved.
 */

public abstract class DFBaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getRooterLayoutRes(), null);
        return view;
    }

    protected abstract int getRooterLayoutRes();

    protected void bindView(View view) {
        ButterKnife.bind(this, view);
    }

    protected void setResult(int resultCode, Intent data) {
        Activity activity = getActivity();
        if (activity != null) {
            activity.setResult(resultCode, data);
        }
    }

    protected void finishActivity() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    public void hideView(){

    }
}
