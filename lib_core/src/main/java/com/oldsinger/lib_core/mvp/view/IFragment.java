package com.oldsinger.lib_core.mvp.view;

import android.view.View;

import androidx.annotation.IdRes;

public interface IFragment extends IAcitivity{

    <T extends View> T findViewById(@IdRes int id);

}
