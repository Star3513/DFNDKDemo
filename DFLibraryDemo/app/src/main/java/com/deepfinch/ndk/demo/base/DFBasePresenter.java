package com.deepfinch.ndk.demo.base;

/**
 * Copyright (c) 2019-2020 DeepFinch Corporation. All rights reserved.
 */

public interface DFBasePresenter {
    interface DFBasePresenterView {
        void showLoadingDialog();

        void hideLoadingDialog();

        void showError(String error);
    }
}
