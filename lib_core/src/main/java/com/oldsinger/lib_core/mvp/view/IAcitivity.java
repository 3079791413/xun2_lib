package com.oldsinger.lib_core.mvp.view;

import androidx.annotation.LayoutRes;

public interface IAcitivity {

    @LayoutRes
    int bandLayout();
    void initView();
    void initData();

}
