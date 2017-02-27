package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base.BaseFragment;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net.OnNetResultListener;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net.VolleyManager;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.util.ToastTool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/2/22.
 */
//                   精选页面
//                 作者:赵子文

public class ChoiceFragment extends BaseFragment {

    private RecyclerView choiceRv;
    private ChoiceAdapter adapter;
    //    up主推荐集合
    private List<ChoiceEntity.DataBean.RecommendUpBean> upBeanList;
    //    轮播图集合
    private List<ChoiceEntity.DataBean.BannerTopBean> bannerBeanList;
    //    奥斯卡集合
    private List<ChoiceEntity.DataBean.OfficalAlbumBean> resultListBeanList;
    //    今日推荐集合
    private List<ChoiceEntity.DataBean.TodayRecommendBean> todayRecommendBeanList;
    //    分类集合
    private List<ChoiceEntity.DataBean.CategoryListBean> categoryListBeanList;

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

        adapter = new ChoiceAdapter(getContext());
        getPostRequestContent();
        choiceRv.setAdapter(adapter);

    }


    private void getPostRequestContent() {

        String postUrl = "http://api.rr.tv/v3plus/index/collection";
        String key = "clientVersion";
        String value = "3.5.2";
        Map<String, String> postMaps = new HashMap<>();
        postMaps.put(key, value);
        VolleyManager.getInstance().startRequest(postUrl, postMaps, new OnNetResultListener() {
            @Override
            public void onSuccessful(String resultStr) {
                Gson gson = new Gson();
                ChoiceEntity choiceEntity = gson.fromJson(resultStr, ChoiceEntity.class);
//                up主推荐
                upBeanList = choiceEntity.getData().getRecommendUp();
//                轮播图
                bannerBeanList = choiceEntity.getData().getBannerTop();
//                奥斯卡
                resultListBeanList = choiceEntity.getData().getOfficalAlbum();
//                今日推荐
                todayRecommendBeanList = choiceEntity.getData().getTodayRecommend();
//                分类
                categoryListBeanList = choiceEntity.getData().getCategoryList();
                adapter.setBannerBeanList(bannerBeanList);
                adapter.setCategoryListBeanList(categoryListBeanList);
                adapter.setResultListBeanList(resultListBeanList);
                adapter.setTodayRecommendBeanList(todayRecommendBeanList);
                adapter.setUpBeanList(upBeanList);

            }

            @Override
            public void onFailure(String errMsg) {
                ToastTool.showDebugToast(getContext(), "解析失败");
            }
        });
    }
}
