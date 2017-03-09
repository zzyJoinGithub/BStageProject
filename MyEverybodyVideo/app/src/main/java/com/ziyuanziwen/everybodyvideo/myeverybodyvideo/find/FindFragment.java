package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.find;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * created by Zzy
 * 发现界面 目前没确定做什么
 */
public class FindFragment extends BaseFragment {

    public static FindFragment newInstance() {

        Bundle args = new Bundle();

        FindFragment fragment = new FindFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initTitle(RelativeLayout titleLayout, ImageView backIv, TextView titleTv, ImageView rightIv) {
        titleTv.setText("发现界面");
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

}
