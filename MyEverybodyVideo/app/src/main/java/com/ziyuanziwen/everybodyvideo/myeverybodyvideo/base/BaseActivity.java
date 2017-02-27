package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
<<<<<<< HEAD
 * Created by dllo on 17/2/20.
 * Created by dllo on 2017/2/16-上午8:51.
 * ✎﹏﹏﹏.₯㎕*﹏﹏﹏
 * 　　忍把浮名，
 * 　　　　换了浅斟低唱。
 * 　　　　 ﹏﹏﹏♥♥赵子源✍♥♥﹏﹏
 *  规范
 * 1.谁创建的类就在类的声明上方写入 类的功能，作者
 *  Activity基类
 *
 *  @author  Zzy
 *  例如：
 *  首页-精选
 *  分类-攻略
 */

//           Activity基类
//            作者:赵子文

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

    //    构建视图
    protected abstract int getLayout();

    //    初始化组件
    protected abstract void initView();

    //    逻辑代码
    protected abstract void initData();


    //    findViewById精简 (组件)
    protected <T extends View> T byView(int resId) {
        return (T) findViewById(resId);
    }

    //toast专用方法
    protected void showToast(String info){
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }

    //    跳转
    protected void goTo(Class<? extends BaseActivity> to) {
        Intent intent = new Intent();
        intent.setClass(this, to);
        startActivity(intent);
//        overridePendingTransition(R.anim.in, R.anim.out);
    }

    //    跳转传String
    protected void goTo(Class<? extends BaseActivity> to, String key, String extraValue) {
        Intent intent = new Intent(this, to);
        intent.putExtra(key, extraValue);
        startActivity(intent);
    }

    //    传集合
    protected void goTo(Class<? extends BaseActivity> to, Bundle bundle) {
        Intent intent = new Intent(this, to);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
