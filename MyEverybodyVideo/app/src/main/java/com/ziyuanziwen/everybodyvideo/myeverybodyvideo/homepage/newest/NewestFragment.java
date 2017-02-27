package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.newest;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base.BaseFragment;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net.OnNetResultListener;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net.VolleyManager;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.util.ToastTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/2/22.
 */

//                    最新页面
//                   作者:赵子文
public class NewestFragment extends BaseFragment {

    private TabLayout newestTl;
    private ViewPager newestVp;
    private ImageView addIv;
    private List<NewestEntity.DataBean.CategoryBean> newestList;
    private NewestAdapter adapter;

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

    //    绑定组件
    @Override
    protected void initView(View view) {
        newestTl = byView(view, R.id.fragment_newestTabLayout);
        newestVp = byView(view, R.id.fragment_newestViewPager);
        addIv = byView(view, R.id.fragment_newestAddIv);
    }

    //    初始化数据(网络解析, 设置导航栏的颜色, 设置切换文字颜色)
    @Override
    protected void initData() {
        newestList = new ArrayList<>();
        adapter = new NewestAdapter(getChildFragmentManager());

        postRequestTitle();
        newestVp.setAdapter(adapter);
        newestTl.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorBlue));
        newestTl.setTabTextColors(getResources()
                .getColor(R.color.colorGray), getResources().getColor(R.color.colorBlue));
        newestTl.setupWithViewPager(newestVp);

    }

    //    获取网络数据
    private void postRequestTitle() {

        String postUrl = "http://api.rr.tv/v3plus/user/myFoucsCategory";
        String key = "clientVersion";
        String value = "3.5.2";

        Map<String, String> postMap = new HashMap<>();
        postMap.put(key, value);
        VolleyManager.getInstance().startStringRequestNet(Request.Method.POST, postUrl, postMap, null, new OnNetResultListener() {
            @Override
            public void onSuccessful(String resultStr) {
                Gson gson = new Gson();
                NewestEntity newestEntity = gson.fromJson(resultStr, NewestEntity.class);
                newestList = newestEntity.getData().getCategory();
                adapter.setNewestList(newestList);
            }

            @Override
            public void onFailure(String errMsg) {
                ToastTool.showDebugToast(getContext(), "没有解析成功");
            }
        });

    }

}
