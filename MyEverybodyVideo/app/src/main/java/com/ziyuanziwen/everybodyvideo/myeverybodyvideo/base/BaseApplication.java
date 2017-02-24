package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 2017/2/24-上午9:23.
 * ✎﹏﹏﹏.₯㎕*﹏﹏﹏
 * 　　忍把浮名，
 * 　　　　换了浅斟低唱。
 * 　　　　 ﹏﹏﹏♥♥赵子源✍♥♥﹏﹏
 */

public class BaseApplication extends Application {

    //注意:在清单文件注册这个Application
//    Application能做什么?
    //1.提供全局Context
    //2.一些全局使用的对象初始化
    //      极光推送,支付sdk初始化,ShareSdk
    //3.记录版本
    //4.设置调试模式  debug - release

    /**
     * 全局Context使用的情况,环境:
     * 1.数据库
     * 2.网络
     * 3.线程池
     */
    private static Context context;

    public static Context getContext() {
        return context;
    }

    //最全局的onCreate生命周期
    // 这个app第一个生命周期
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        /**
         * AppLication:应用程序
         * 就代表整个app应用
         * 当app应用被打开时,就会自动生成一个AppLication对象
         *
         * 当app应用退出时,该对象自动销毁
         *
         * AppLication也有一个context
         * 这个context通常称为全局的context
         * Context:
         * context是一个抽象类
         * AppLication和Activity都间接的继承自该类
         * 在通常情况下,称之为上下文
         *
         * (讲师理解:环境
         * 1.获取资源文件,获取手机某路径
         * 2.数据库,网络,流相关操作
         * 3.Toast,加载布局,创建某View
         *
         * 以上三种操作都抽象的会与界面或手机相关
         * 只要我们去操作界面或手机都会基于这个手机去执行
         * context在某种程度上 == 手机)
         * 我的理解 : 就是一个连接对象
         * 代理模式 单例模式
         *
         */
    }

}
