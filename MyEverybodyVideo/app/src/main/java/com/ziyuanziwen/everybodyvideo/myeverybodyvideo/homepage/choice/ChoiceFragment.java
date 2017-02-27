package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base.BaseFragment;

/**
 * Created by dllo on 17/2/22.
 */
//                   精选页面
//                 作者:赵子文

public class ChoiceFragment extends BaseFragment {

    private RecyclerView choiceRv;

    //    绑定布局
    @Override
    protected int getLayout() {
        return R.layout.fragment_choice;
    }

//    使基类中标题栏消失
    @Override
    protected void initTitle(RelativeLayout titleLayout, ImageView backIv, TextView titleTv, ImageView rightIv) {
        titleLayout.setVisibility(View.GONE);

    }

    @Override
    protected void initView(View view) {
        choiceRv = byView(view, R.id.fragment_choiceRv);
    }

    @Override
    protected void initData() {



    }
}
