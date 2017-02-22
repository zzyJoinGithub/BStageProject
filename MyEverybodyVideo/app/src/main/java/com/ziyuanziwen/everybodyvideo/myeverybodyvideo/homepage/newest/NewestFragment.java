package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.newest;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base.BaseFragment;

/**
 * Created by dllo on 17/2/22.
 */

//                    最新页面
//                   作者:赵子文
public class NewestFragment extends BaseFragment {

//    绑定布局
    @Override
    protected int getLayout() {
        return R.layout.fragment_newest;
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
