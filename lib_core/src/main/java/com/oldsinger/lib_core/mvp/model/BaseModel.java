package com.oldsinger.lib_core.mvp.model;

import com.oldsinger.lib_core.mvp.callback.CallObserver;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BaseModel implements IModel{

    private CallObserver callObserver;

    protected void request(Observable<?> observable, CallObserver callObserver){
        this.callObserver = callObserver;
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(callObserver);

    }

    @Override
    public void destory() {
        //销毁网络异步线程，解除观察者模式绑定
        if (callObserver != null){
            callObserver.dispose();
            callObserver = null;
        }
    }
}
