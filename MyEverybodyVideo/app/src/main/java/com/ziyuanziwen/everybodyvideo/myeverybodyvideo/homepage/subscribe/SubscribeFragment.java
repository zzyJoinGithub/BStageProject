package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.subscribe;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base.BaseFragment;

/**
 * Created by dllo on 17/2/22.
 */
//               订阅界面
//               作者:赵子文

public class SubscribeFragment extends BaseFragment {

//    绑定布局
    @Override
    protected int getLayout() {
        return R.layout.fragment_subscribe;
    }

//    使基类中标题栏消失
    @Override
    protected void initTitle(RelativeLayout titleLayout, ImageView backIv, TextView titleTv, ImageView rightIv) {
        titleLayout.setVisibility(View.GONE);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }
}
