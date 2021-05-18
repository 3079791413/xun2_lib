package com.oldsinger.lib_core.mvp.presenter;

import com.oldsinger.lib_core.mvp.model.IModel;
import com.oldsinger.lib_core.mvp.view.IView;

public abstract class BasePresenter <M extends IModel,V extends IView> implements IPresenter {
    protected M mIModel;
    protected V mView;

    public BasePresenter(M mIModel, V mView) {
        this.mIModel = mIModel;
        this.mView = mView;
    }

    public BasePresenter(){

    }

    @Override
    public void destory() {
        if (mIModel == null){
            mIModel.destory();
            mIModel = null;
        }
//        System.gc();
    }

    public void attachView(IView iView){
        mView = (V) iView;
    }

}
