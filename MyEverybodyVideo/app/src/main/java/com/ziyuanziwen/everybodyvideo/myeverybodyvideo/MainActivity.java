package com.ziyuanziwen.everybodyvideo.myeverybodyvideo;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base.BaseActivity;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.channel.ChannelFragment;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.find.FindFragment;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.HomePageFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private RadioButton btnHomePage;
    private RadioButton btnChannel;
    private RadioButton btnFind;
    private RadioButton btnMine;
    private HomePageFragment homePageFragment;
    private ChannelFragment channelFragment;
    private FindFragment findFragment;

    /**
     * 接受Intent对象的生命周期
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btnHomePage = byView(R.id.mainAty_rbtHomePage);
        btnChannel = byView(R.id.mainAty_rbtChannel);
        btnFind = byView(R.id.mainAty_rbtFind);
        btnMine = byView(R.id.mainAty_rbtMine);

        btnChannel.setOnClickListener(this);
        btnFind.setOnClickListener(this);
        btnHomePage.setOnClickListener(this);
        btnMine.setOnClickListener(this);

        homePageFragment = new HomePageFragment();

        channelFragment = ChannelFragment.newInstance();
        findFragment = FindFragment.newInstance();
    }


    @Override
    protected void initData() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.mainAty_frameLayout, homePageFragment);
        transaction.commit();

    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.mainAty_rbtHomePage:
                transaction.replace(R.id.mainAty_frameLayout, homePageFragment);
                break;
            case R.id.mainAty_rbtChannel:
                transaction.replace(R.id.mainAty_frameLayout, channelFragment);
                break;
            case R.id.mainAty_rbtFind:
                transaction.replace(R.id.mainAty_frameLayout, findFragment);
                break;
            case R.id.mainAty_rbtMine:
                break;
        }
        transaction.commit();

    }
}
