package com.oldsinger.lib_core.mvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.oldsinger.lib_core.mvp.presenter.BasePresenter;

public abstract class BaseFragment <P extends BasePresenter> extends Fragment implements IFragment,IView {

    protected P mIPresenter;
    protected View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view = inflater.inflate(bandLayout(),container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    @Override
    public void onDestroy() {
        if (mIPresenter != null){
            mIPresenter.destory();
            mIPresenter = null;
        }
        super.onDestroy();
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return view.findViewById(id);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), ""+msg, Toast.LENGTH_SHORT).show();
    }
}
