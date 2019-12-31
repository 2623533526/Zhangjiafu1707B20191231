package com.bawei.zhangjiafu1707b20191231.utlis;

import android.content.Context;
import android.os.Handler;

import java.io.IOException;
import java.nio.channels.InterruptedByTimeoutException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author: 张家辅
 * @date: 2019/12/31
 */
public class OkHttpUtlis {
    //使用单例模式二次封装OKHTTP
    private static OkHttpUtlis okHttpUtlis;
    private final OkHttpClient okHttpClient;
    Handler handler=new Handler();

    public OkHttpUtlis() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                //封装OKHTTP的get方法，添加OKHTTP日志拦截器
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    public static OkHttpUtlis getInstance() {
        if(okHttpUtlis==null){
            synchronized (OkHttpUtlis.class){
                if(okHttpUtlis==null){
                    okHttpUtlis=new OkHttpUtlis();
                }
            }
        }
        return okHttpUtlis;
    }
    public void doGet(String URL,CallBack callBack){
        Request request = new Request.Builder()
                .get()
                .url(URL)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.error(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
             handler.post(new Runnable() {
                 @Override
                 public void run() {
                     try {
                         callBack.success(response.body().string());
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }
             });
            }
        });
    }
    public void doPost(String URL, Map<String,String> map,CallBack callBack){

    }
    public interface CallBack{
        void success(String string);
        void error(Throwable throwable);
    }
}
