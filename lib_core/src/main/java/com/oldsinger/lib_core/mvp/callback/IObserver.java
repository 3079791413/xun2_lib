package com.oldsinger.lib_core.mvp.callback;

import com.oldsinger.lib_core.mvp.bean.ErrorBean;

public interface IObserver <T>{
    void success(T bean);

    void fail(ErrorBean errorBean);
}
