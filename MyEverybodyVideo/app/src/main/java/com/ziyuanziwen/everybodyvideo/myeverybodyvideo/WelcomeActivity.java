package com.ziyuanziwen.everybodyvideo.myeverybodyvideo;

import android.os.CountDownTimer;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {


    private CountDownTimer timer;

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        timer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                goTo(MainActivity.class);

            }
        }.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        timer.start();
    }
}
