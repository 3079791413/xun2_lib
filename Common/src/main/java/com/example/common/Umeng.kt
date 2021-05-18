package com.example.common

import android.content.Context
import android.widget.Toast
import com.umeng.socialize.UMShareListener
import com.umeng.socialize.bean.SHARE_MEDIA

class Umeng {

    val context : Context = TODO()


    private val shareListener: UMShareListener = object : UMShareListener {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        override fun onStart(platform: SHARE_MEDIA) {}

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        override fun onResult(platform: SHARE_MEDIA) {
            Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
        }

        /*
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        override fun onError(platform: SHARE_MEDIA, t: Throwable) {
            Toast.makeText(context, "失                                            败" + t.message, Toast.LENGTH_LONG).show()
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        override fun onCancel(platform: SHARE_MEDIA) {
            Toast.makeText(context, "取消                                          了", Toast.LENGTH_LONG).show()
        }
    }
}