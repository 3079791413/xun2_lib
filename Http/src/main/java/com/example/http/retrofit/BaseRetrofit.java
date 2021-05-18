package com.example.http.retrofit;



import com.example.http.BuildConfig;
import com.example.http.interceptor.MTokenInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseRetrofit implements IRetrofit {

    private Retrofit mRetrofit;
    //单例模式
    private BaseRetrofit(){
        mRetrofit = createRetrofit();
    }

    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.SERVER_URL)
                .client(createClient())
                .build();
    }

    private OkHttpClient createClient() {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new MTokenInterceptor())
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .build();
    }

    private static volatile BaseRetrofit instance;
    public static BaseRetrofit getInstance(){
        if( instance==null ){
            synchronized (BaseRetrofit.class){
                if( instance==null ){
                    instance = new BaseRetrofit();
                }
            }
        }
        return instance;
    }

    @Override
    public <T> T createClass(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
}
