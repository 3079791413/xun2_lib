package com.oldsinger.lib_core.mvp.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.oldsinger.lib_core.mvp.presenter.BasePresenter;

import java.lang.reflect.Field;


public abstract class BaseActivity <P extends BasePresenter> extends AppCompatActivity implements IAcitivity,IView {
    protected P mIPresenterImpl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bandLayout());
        /**
         * 通过反射获取多个Presenter层
         */
        try {
            Field[] declaredFields = getClass().getDeclaredFields();
            for( int q=0 ; q<declaredFields.length;q++ ){
                GetPresenter annotation = declaredFields[q].getAnnotation(GetPresenter.class);
                if( annotation!=null ){
                    declaredFields[q].setAccessible(true);//对属性进行赋值，必须设置这个属性为true
                    Class<BasePresenter> type = (Class<BasePresenter>) declaredFields[q].getType();
                    BasePresenter basePresenter = type.newInstance();
                    basePresenter.attachView(this);
                    declaredFields[q].set(this,basePresenter);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        if (mIPresenterImpl == null){
            mIPresenterImpl = null;
        }
        super.onDestroy();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }
}
