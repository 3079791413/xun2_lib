package com.example.common.utils

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.common.BaseApplication

public object SpUtils {
    lateinit var sp: SharedPreferences
    init {
         sp = BaseApplication.context.getSharedPreferences("user",MODE_PRIVATE)
    }

    public fun putString(key : String,value : String){
        sp.edit().putString(key,value).commit()
    }

    public fun putBollean(key: String,value: Boolean){
        sp.edit().putBoolean(key,value).commit()
    }

    public fun putInt(key: String,value: Int){
        sp.edit().putInt(key,value).commit()
    }

    public fun getString(key: String): String? {
        return sp.getString(key,"")
    }

    public fun getInt(key: String): Int? {
        return sp.getInt(key,-1)
    }

    public fun getBoolean(key: String): Boolean? {
        return sp.getBoolean(key,false)
    }

//    public fun put

}