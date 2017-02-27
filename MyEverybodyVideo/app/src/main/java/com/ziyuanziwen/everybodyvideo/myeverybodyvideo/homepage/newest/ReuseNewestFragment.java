package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.newest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
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
 * Created by dllo on 17/2/24.
 */

/**
 * 复用的碎片
 * 作者:赵子文
 */

public class ReuseNewestFragment extends BaseFragment {

    //    定义变量
    private ListView reuseLv;
    private ReuseNewestAdapter adapter;
    private List<ReuseNewestEntity.DataBean.ResultsBean> resultsBeanList;

    //    ?????
    public static ReuseNewestFragment getReuseNewestFragment(int position) {
        ReuseNewestFragment reuseNewestFragment = new ReuseNewestFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("Id", position);
        reuseNewestFragment.setArguments(bundle);
        return reuseNewestFragment;
    }

    //    绑定布局
    @Override
    protected int getLayout() {
        return R.layout.fragment_newest_reuse;
    }

    //    使基类中的标题栏消失
    @Override
    protected void initTitle(RelativeLayout titleLayout, ImageView backIv, TextView titleTv, ImageView rightIv) {
        titleLayout.setVisibility(View.GONE);
    }

    //    绑定组件
    @Override
    protected void initView(View view) {
        reuseLv = byView(view, R.id.Fragment_newestReuseLv);
    }

    //    初始化数据(适配器, 网络数据)
    @Override
    protected void initData() {
        adapter = new ReuseNewestAdapter(getContext());
        postRequestContent();
        reuseLv.setAdapter(adapter);

    }

    //    获取网络数据
    private void postRequestContent() {
//    ??????
        Bundle id = getArguments();
        String positionId = id.getInt("Id") + "";
        String postUrl = "http://api.rr.tv/v3plus/video/query";
        String key = "clientVersion";
        String value = "3.5.2";
        Map<String, String> postMaps = new HashMap<>();
        postMaps.put(key, value);
        Map<String, String> postMaps1 = new HashMap<>();
        postMaps1.put("categoryId", positionId);

        VolleyManager.getInstance().startStringRequestNet(Request.Method.POST, postUrl, postMaps, postMaps1, new OnNetResultListener() {

            //            当获取成功网络数据传入适配器中
            @Override
            public void onSuccessful(String resultStr) {
                Gson gson = new Gson();
                ReuseNewestEntity reuseNewestEntity = gson.fromJson(resultStr, ReuseNewestEntity.class);
                resultsBeanList = reuseNewestEntity.getData().getResults();
                Log.d("ReuseNewestFragment", "resultsBeanList.size():" + resultsBeanList.size());
                adapter.setResultsBeanList(resultsBeanList);
            }

            @Override
            public void onFailure(String errMsg) {
                ToastTool.showDebugToast(getContext(), "没有解析成功");
            }
        });
    }
}
