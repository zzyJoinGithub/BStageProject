package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base.BaseFragment;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.ChoiceFragment;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.newest.NewestFragment;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.subscribe.SubscribeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/2/22.
 */


//                  首页界面
//                 作者:赵子文
public class HomePageFragment extends BaseFragment {

//         定义各个组件
    private TabLayout tabLayoutTitle;
    private ImageView searchIv;
    private ViewPager homepageVp;
    private List<Fragment> data;
    private NewestFragment newestFragment;
    private ChoiceFragment choiceFragment;
    private SubscribeFragment subscribeFragment;

//          获取布局
    @Override
    protected int getLayout() {
        return R.layout.fragment_homepage;
    }

//    使基类中标题栏消失
    @Override
    protected void initTitle(RelativeLayout titleLayout, ImageView backIv, TextView titleTv, ImageView rightIv) {
        titleLayout.setVisibility(View.GONE);
    }

//    绑定组件
    @Override
    protected void initView(View view) {
        tabLayoutTitle = byView(view, R.id.fragment_homepageTl);
        searchIv = byView(view, R.id.fragment_homepageSearch);
        homepageVp = byView(view, R.id.fragment_homepageVp);

    }

//    逻辑代码
    @Override
    protected void initData() {
        initFragment();
        data = getFragment();
        HomePageAdapter adapter = new HomePageAdapter(getChildFragmentManager());  // 初始化适配器
        adapter.setDatas(data);
        homepageVp.setAdapter(adapter);

//        设置标题栏未选中与选中的颜色
        tabLayoutTitle.setTabTextColors(getResources().
                getColor(R.color.colorGray), getResources().getColor(R.color.colorBlue));
//        设置导航线不显示
        tabLayoutTitle.setSelectedTabIndicatorColor(Color.alpha(0));
        tabLayoutTitle.setupWithViewPager(homepageVp);
        homepageVp.setCurrentItem(1);


    }

//    初始化给个fragment
    private void initFragment() {
        newestFragment = new NewestFragment();
        choiceFragment = new ChoiceFragment();
        subscribeFragment = new SubscribeFragment();
    }


//    将给个fragment加入集合
    private List<Fragment> getFragment() {
        data = new ArrayList<>();
        data.add(newestFragment);
        data.add(choiceFragment);
        data.add(subscribeFragment);
        return data;
    }
}
