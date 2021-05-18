package com.example.http.interceptor;

import com.example.common.conatant.UserConstant;
import com.example.common.utils.SpUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MTokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request build = request.newBuilder()
                .addHeader("Content_Type", "application/json")
                .addHeader("charset", "UTF-8")
                .addHeader("token", SpUtils.INSTANCE.getString(UserConstant.TOKEN))
                .build();
        return chain.proceed(build);
    }
}
