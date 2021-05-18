package com.example.common

import android.app.Application
import android.content.Context
import com.alipay.sdk.app.EnvUtils
import java.util.logging.Handler
import android.util.Log
import com.tencent.bugly.crashreport.CrashReport
//import com.umeng.commonsdk.UMConfigure
//import com.umeng.message.IUmengRegisterCallback
//import com.umeng.message.PushAgent
import com.umeng.socialize.PlatformConfig


class BaseApplication : Application() {

//    var pushAgent: PushAgent? = null

    companion object{
        lateinit var context : Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext


//        UMConfigure.init(
//            this,
//            "5fc49f82c26e8f1b3e966171",
//            "Umeng",
//            UMConfigure.DEVICE_TYPE_PHONE,
//            "099f7eac984e8105c76f9aa3985c615e"
//        )
//
//        pushAgent = PushAgent.getInstance(this)
//
//
//        pushAgent!!.register(object : IUmengRegisterCallback {
//            override fun onSuccess(s: String) {
//                Log.i("Oldsinger", "注册成功$s")
//            }
//
//            override fun onFailure(s: String, s1: String) {
//                Log.d("Oldsinger", "注册失败s:$s,s1:$s1")
//            }
//        })

        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0")
        //豆瓣RENREN平台目前只能在服务器端配置
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setSinaWeibo(
            "3921700954",
            "04b48b094faeb16683c32669824ebdad",
            "http://sns.whalecloud.com"
        )
        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf")
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba")

        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX)
        CrashReport.initCrashReport(applicationContext, "f0b7004682", BuildConfig.DEBUG);

    }

}