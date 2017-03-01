package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base.BaseFragment;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.bean.BannerBean;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.bean.BriefBean;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.bean.DataBean;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.bean.FeaturedBean;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net.OnNetResultListener;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net.VolleyManager;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.util.LogTool;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.util.ToastTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/2/22.
 */
//                   精选页面
//                 作者:赵子文
//实体类的修改  zzy

public class ChoiceFragment extends BaseFragment {

    private RecyclerView choiceRv;
    private ChoiceAdapter adapter;
    //    up主推荐集合
    private List<DataBean.RecommendUpBean> upBeanList;
    //    轮播图集合
    private List<BannerBean> bannerBeanList;
    //    奥斯卡集合
    private List<DataBean.OfficalAlbumBean> resultListBeanList;
    //    今日推荐集合
    private List<BriefBean> todayRecommendBeanList;
    //    分类集合
    private List<DataBean.CategoryBean> categoryListBeanList;

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

        adapter = new ChoiceAdapter(mContext);
        getPostRequestContent();
        choiceRv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        choiceRv.setAdapter(adapter);

    }


    private void getPostRequestContent() {

        String postUrl = "http://api.rr.tv/v3plus/index/collection";
        String key = "clientVersion";
        String value = "3.5.2";
        final ArrayList<Integer> videoId = new ArrayList<>();

        Map<String, String> postMaps = new HashMap<>();
        postMaps.put(key, value);
        VolleyManager.getInstance().startStringRequestNet(Request.Method.POST, postUrl, postMaps, null, new OnNetResultListener() {
            @Override
            public void onSuccessful(String resultStr) {
                Gson gson = new Gson();
                FeaturedBean featuredBean = gson.fromJson(resultStr, FeaturedBean.class);
                adapter.setFeaturedBean(featuredBean);
                for (int i = 0; i < featuredBean.getData().getTodayRecommend().size(); i++) {
                    videoId.add(featuredBean.getData().getTodayRecommend().get(i).getId());

                }



////                up主推荐
//                upBeanList = featuredBean.getData().getRecommendUp();
////                轮播图
//                bannerBeanList = featuredBean.getData().getBannerTop();
////                奥斯卡
//                resultListBeanList = featuredBean.getData().getOfficalAlbum();
////                今日推荐
//                todayRecommendBeanList = featuredBean.getData().getTodayRecommend();
////                分类
//                categoryListBeanList = featuredBean.getData().getCategoryList();

//                adapter.setBannerBeanList(bannerBeanList);
//                adapter.setCategoryListBeanList(categoryListBeanList);
//                adapter.setResultListBeanList(resultListBeanList);
//                adapter.setTodayRecommendBeanList(todayRecommendBeanList);
//                adapter.setUpBeanList(upBeanList);
            }

            @Override
            public void onFailure(String errMsg) {
                LogTool.logI("精选界面", "网络异常");
                ToastTool.showDebugToast(getContext(), "网络异常");
            }
        });
    }
}
