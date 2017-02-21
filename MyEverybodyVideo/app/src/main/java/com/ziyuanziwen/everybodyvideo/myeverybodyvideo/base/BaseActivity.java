package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 17/2/20.
 */

//           Activity基类
//            作者:赵子文

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


    //    findViewById精简
    protected <layout extends View> layout byView(int resId) {
        return (layout) findViewById(resId);
    }

    //    跳转
    protected void goTo(Class<? extends BaseActivity> to) {
        Intent intent = new Intent();
        intent.setClass(this, to);
        startActivity(intent);
//        overridePendingTransition();
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
