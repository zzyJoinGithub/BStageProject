package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 2017/2/16-上午8:51.
 * ✎﹏﹏﹏.₯㎕*﹏﹏﹏
 * 　　忍把浮名，
 * 　　　　换了浅斟低唱。
 * 　　　　 ﹏﹏﹏♥♥赵子源✍♥♥﹏﹏
 *  规范
 * 1.谁创建的类就在类的声明上方写入 类的功能，作者
 *  Activity基类
 *  @author  Zzy
 *  例如：
 *  首页-精选
 *  分类-攻略
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        选择性完成的要求：
        把标题栏写入基类，统一个规范
        1.利用xml布局
        2.toolbar
         */

        // 定制界面流程
        setContentView(getLayout());

        initView();
        initData();
    }

    protected abstract int getLayout();

    protected abstract void initView();

    protected abstract void initData();

    //精简findViewById
    protected <T extends View> T byView(int resId){
        return (T)findViewById(resId);
    }

    protected void goTo(Class<? extends BaseActivity> to){
        Intent intent = new Intent();
        intent.setClass(this, to);
        startActivity(intent);
        // 界面切换动画
//        overridePendingTransition(R.anim.in, R.anim.out);
    }
}
