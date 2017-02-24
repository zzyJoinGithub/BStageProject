package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net;

import android.os.Handler;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dllo on 2017/2/24-上午10:28.
 * ✎﹏﹏﹏.₯㎕*﹏﹏﹏
 * 　　忍把浮名，
 * 　　　　换了浅斟低唱。
 * 　　　　 ﹏﹏﹏♥♥赵子源✍♥♥﹏﹏
 */

public class OkHttpManager {
    private Handler handler;
    private OkHttpClient client;
    private static OkHttpManager instance;
    private OkHttpManager() {
        // 获取okHttpClient
        getOkHttpClient();
        handler = new Handler();
    }

    public static OkHttpManager getInstance() {
        if (null == instance) {
            synchronized (OkHttpManager.class){
                if (null == instance) {
                    instance = new OkHttpManager();
                }
            }
        }
        return instance;
    }

    private void getOkHttpClient() {
        client = new OkHttpClient.Builder()
                // 等线程池，缓存，文件下载讲完之后
                // 在来修改添加
//                .retryOnConnectionFailure()
//                .cache()
//                .readTimeout()
                .build();
    }

    //get请求内部实现
    private void _startGet(final String url,
                           final OnNetResultListener listener){
        // 将网络请求的代码写在网络操作类内部
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                createCall(request, listener);
            }
        }).start();
    }

    //带请求头的get请求
    private void _startHeader(final String url,
                              final Map<String, String> headers,
                              final OnNetResultListener listener){

        new Thread(new Runnable() {
            @Override
            public void run() {
//                Request request = new Request.Builder()
//                        .url(url)
//                        .addHeader()
//                        .build();
                Request.Builder builder =
                        new Request.Builder();
                builder.url(url);

                // 1.遍历map
                Set set = headers.keySet();
                // 迭代器
                Iterator iterator = set.iterator();
                while (iterator.hasNext()){
                    // 循环获取map的key值
                    String key = (String) iterator.next();
                    // 根据key值获取value
                    String value = headers.get(key);
                    builder.addHeader(key,value);//循环添加请求头
                }

                Request request = builder.build();

                createCall(request, listener);
            }
        }).start();
    }

    //post请求的内部实现
    private void _startPost(final String url,
                            final Map<String,String> body,
                            final OnNetResultListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                FormBody.Builder builder =
                        new FormBody.Builder();
                Set set = body.keySet();
                Iterator iterator = set.iterator();
                while (iterator.hasNext()){
                    String key = (String) iterator.next();
                    String value = body.get(key);
                    builder.add(key,value);
                }
                RequestBody requestBody = builder.build();
                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();
                createCall(request, listener);
            }
        }).start();
    }

    private void createCall(Request request, final OnNetResultListener listener){
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                final String errMsg = e.getMessage();
                // 发回主线程
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onFailure(errMsg);
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String str = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onSuccessful(str);
                    }
                });
            }
        });
    }


    // 封装： 对外提供使用方式，具体的实现细节封装起来
    //get请求的对外方法
    public void startGet(String url, OnNetResultListener listener){
        _startGet(url,listener);
    }

    //带请求头的get请求对外方法
    public void startHeader(String url,
                            Map<String, String> headers,
                            OnNetResultListener listener){
        _startHeader(url,headers,listener);
    }

    //post请求对外的方法
    public void startPost(String url,
                          Map<String, String> body,
                          OnNetResultListener listener){
        _startPost(url,body,listener);
    }
}
