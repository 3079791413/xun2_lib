package com.oldsinger.lib_core.mvp.callback;

import com.oldsinger.lib_core.mvp.bean.BaseDataBean;
import com.oldsinger.lib_core.mvp.bean.ErrorBean;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public abstract class CallObserver <T extends BaseDataBean> extends DisposableObserver<T> implements IObserver<T> {

    @Override
    public void onNext(@NonNull T t) {
        success(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        fail(new ErrorBean(-200,e.getMessage()));
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void success(T bean) {

    }

    @Override
    public void fail(ErrorBean errorBean) {

    }
}
