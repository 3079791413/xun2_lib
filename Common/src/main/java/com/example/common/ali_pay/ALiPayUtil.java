package com.example.common.ali_pay;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;
import com.example.common.PayEvent;
import com.example.common.R;
import com.example.common.ali_pay.util.OrderInfoUtil2_0;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

public class ALiPayUtil {
    private AppCompatActivity mActivity;
    public ALiPayUtil(AppCompatActivity activity){
        mActivity = activity;
    }
    
    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "2021000117602210";

    /**
     * 用于支付宝账户登录授权业务的入参 pid。
     */
    public static final String PID = "";

    /**
     * 用于支付宝账户登录授权业务的入参 target_id。
     */
    public static final String TARGET_ID = "";

    /**
     *  pkcs8 格式的商户私钥。
     *
     * 	如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个，如果两个都设置了，本 Demo 将优先
     * 	使用 RSA2_PRIVATE。RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议商户使用
     * 	RSA2_PRIVATE。
     *
     * 	建议使用支付宝提供的公私钥生成工具生成和获取 RSA2_PRIVATE。
     * 	工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCPe/UbmgVwkoA6/FcLkpOclPS81OgPk6KubchhwfYcoyKM8JQYI3id4nWJj0+gQuperX8gdViF5xbaOWGtsP5y36wftKxjUNUdCTgGEfnZUTlc9dm+SN+SCEMWu+EClxYhRfUvuNQ60wQ5H5dWxbM2/lxh7TDvAJJ6+t5tDHmdULI0gu8bA5eVbAckvOm3Wj8lU6GtFvIQax99G5YZPhS9KqavWw6KInmGgbM0v5/z1XbUuiNgLddqUiKl/6eN8n8w+xBZMVFgtqjm1A/l+EdUKlgsFUFgDpe4t+D/vcgeO6YvsEUvh0NMeaNVYreVl7zj7fdXpTThJt9GMCnUbizlAgMBAAECggEBAIixW+Iuyo3jU7xeGAMTYgMjOUEISAhG6w3n0qWnJfNESy8gJsYUTN2FF9q0J/SSqzsFaH4grPPsIq5FSlQxsUb45b7S3gRDxrPBy5ik2XT6V37DTOxl+m/h5SAsXwGbtF6AikQrfiyISZauDPyCneh/TL2SPTMERYhZpTsYTmfaOZiT6faQ6ntG2vUitgZpcEf66unjAp8Gw59EjOkOPGqv7ZRMMZB04lI4tzt7mFWmmQ5UnpdcqXdvcHE1R6BYLitdpxc6PkP10Q8P98lSgefuLC2goIh7cB7v4V4JLdZBb6c8+VYFBGliMBiblhh7UXoZ1qU5opGGX0fN0e1uDYECgYEA73KzR/4SnnfRm4CHWYEKIq4G1ZPSlj6kzOs0wvDbxC/O5ML0vwcfQr1WjXOa4vUzaRf4tAgDfjbeJ9GdrTXpdGdlrwAm/5ERI4+f96GIkjqEkH68K5LY2qhK8cPXY3b/0HubJoTsbvL4s3Un71UstCZ6mpMsBznLGSoxVB9fxUUCgYEAmWcR+OtoEKmkanw2Zzm0v9TEz/uB82TM5psRs1htHe49/hsvQcTkzaJUqB0E0pLGne5+PoXKyMboOhX5zZyOPcBhECiyYyu50jmmD/BBuNZ8JDAo1jGZ1Or/kSEQy2ZmNAPUf00tx/xVwl87hYDOlJ1fezH9gctrqcabZn+1MyECgYEAwIvflti8aCm5p+3m6RZW7CQ84qoIAUFGjHHm74t3bX90Q4hx/trfTG+slfqMZkz+llfsafi3tlqwshxhStO7cxtiLSNWONSSj6IC5MJEbDIaP4dqES/4qXwDJ/5vyU/7zc5XqNXiuiUv0D85ab94PzViCcKm+PiEVSwUlqzT+nECgYAcZIldCyUtwFhgJTFklbewsc7jt+drh0KthBIYoctAePIccCelAq5eqScGHqjf2K7OWHAhXl4UH0yoQ50Cw3FIWK0econh9RvlnynvVUa00W4UvJ0yV+fjEsPOttIG86MDCZpxHXXVyAyPd1Tq9tZFh4M+qBgFyALbX5kvtX9yQQKBgGTAbPO/izl1tPuNdkGKFZ334MVMVKm3jx0xW0LdcuXexo4z8RKl2Sb5Ffmxs1SzukL4DQ4DKxnhO3ol/o+rbuZ/x+Pcc9MbfGwrdzY8WHNrE0duT+JPD5wZXXPC5IYrJBSnjurroKSVX/LlxAYPEwzGfrckhdwH/hpVp76AL0T9";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        showAlert(mActivity, mActivity.getString(R.string.pay_success) + payResult);
                        EventBus.getDefault().post(new PayEvent(true));
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        showAlert(mActivity, mActivity.getString(R.string.pay_failed) + payResult);
                        EventBus.getDefault().post(new PayEvent(false));
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        showAlert(mActivity, mActivity.getString(R.string.auth_success) + authResult);
                    } else {
                        // 其他状态值则为授权失败
                        showAlert(mActivity, mActivity.getString(R.string.auth_failed) + authResult);
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };


    /**
     * 支付宝支付业务示例
     */
    public void payV2(int price) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            showAlert(mActivity, mActivity.getString(R.string.error_missing_appid_rsa_private));
            return;
        }

        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo 的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2,price);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(mActivity);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 支付宝账户授权业务示例
     */
    public void authV2(View view) {
        if (TextUtils.isEmpty(PID) || TextUtils.isEmpty(APPID)
                || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))
                || TextUtils.isEmpty(TARGET_ID)) {
            showAlert(mActivity, mActivity.getString(R.string.error_auth_missing_partner_appid_rsa_private_target_id));
            return;
        }

        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * authInfo 的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID, rsa2);
        String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2);
        final String authInfo = info + "&" + sign;
        Runnable authRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造AuthTask 对象
                AuthTask authTask = new AuthTask(mActivity);
                // 调用授权接口，获取授权结果
                Map<String, String> result = authTask.authV2(authInfo, true);

                Message msg = new Message();
                msg.what = SDK_AUTH_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }

    /**
     * 获取支付宝 SDK 版本号。
     */
    public void showSdkVersion(View v) {
        PayTask payTask = new PayTask(mActivity);
        String version = payTask.getVersion();
        showAlert(mActivity, mActivity.getString(R.string.alipay_sdk_version_is) + version);
    }



    private static void showAlert(Context ctx, String info) {
        showAlert(ctx, info, null);
    }

    private static void showAlert(Context ctx, String info, DialogInterface.OnDismissListener onDismiss) {
        new AlertDialog.Builder(ctx)
                .setMessage(info)
                .setPositiveButton(R.string.confirm, null)
                .setOnDismissListener(onDismiss)
                .show();
    }

    private static void showToast(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }

    private static String bundleToString(Bundle bundle) {
        if (bundle == null) {
            return "null";
        }
        final StringBuilder sb = new StringBuilder();
        for (String key: bundle.keySet()) {
            sb.append(key).append("=>").append(bundle.get(key)).append("\n");
        }
        return sb.toString();
    }
}
