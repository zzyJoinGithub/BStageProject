package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.login;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base.BaseActivity;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.util.LogTool;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 17/2/25.
 */


/**
 * 登录界面
 * 作者:赵子文
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, PlatformActionListener {

    private ImageView qqIv;
    private ImageView backIv;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        backIv = byView(R.id.activity_loginBack);
        qqIv = byView(R.id.activity_loginQQ);
        backIv.setOnClickListener(this);
        qqIv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        ShareSDK.initSDK(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_loginBack:
                finish();
                break;
            case R.id.activity_loginQQ:
                mobLogin();
                break;
        }
        // 登录成功
        // 1.Sp,吧登录结果存入sp ; 用户名, boolean = true
    }

    public void mobLogin() {
        Platform platform = ShareSDK.getPlatform(QQ.NAME);
        if (platform == null) {
            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
            return;
        }

        // 第二次登录会进入的方法, 不在走网页, 而是直接从本地数据库中取数据
        if (platform.isAuthValid()) {
            String name = platform.getDb().getUserName();
            String icon = platform.getDb().getUserIcon();
            Log.d("LoginActivity", name);

        } else {
            platform.setPlatformActionListener(this);  // 回调接口返回
            platform.SSOSetting(false);
            platform.showUser(null);
        }
    }

    // 第一次登录会进入的方法
    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        String name = platform.getDb().getUserName();
        String icon = platform.getDb().getUserIcon();

        SharedPreferences sp = getSharedPreferences("message", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", name);
        editor.putString("icon", icon);
//        editor.putBoolean("isLogin", true);
        editor.commit();
        platform.removeAccount();
        finish();
        LogTool.logI("是否登录", "登录");

    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCancel(Platform platform, int i) {
        Toast.makeText(this, "取消登录", Toast.LENGTH_SHORT).show();

    }
}
