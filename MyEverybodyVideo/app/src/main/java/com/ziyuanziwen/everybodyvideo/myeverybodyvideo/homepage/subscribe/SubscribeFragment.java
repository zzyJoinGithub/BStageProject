package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.subscribe;

import android.util.Log;
import android.view.LayoutInflater;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/2/22.
 */
//               订阅界面
//               作者:赵子文

public class SubscribeFragment extends BaseFragment {

    //    定义ListView, 实体类集合
    private ListView subscribeLv;
    private List<SubscribeEntity.DataBean.UperListBean> subscribeEntityList;
    private SubscribeAdapter adapter;


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

    //    绑定组件
    @Override
    protected void initView(View view) {
        subscribeLv = (ListView) view.findViewById(R.id.fragment_subscribeLv);

    }

    //    初始化实体类集合,适配器,加入尾视图,将listview组件与适配器绑定
    @Override
    protected void initData() {

        subscribeEntityList = new ArrayList<>();
        adapter = new SubscribeAdapter(getContext());
        postRequest();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_fragment_subscribe_foot, null);
        subscribeLv.addFooterView(view);
        subscribeLv.setAdapter(adapter);
    }

    //    post网络请求
    private void postRequest() {

//        网址
        String postUrl = "http://api.rr.tv/v3plus/uper/recommendList";

        String key1 = "clientVersion";
        String value1 = "3.5.2";

//        String key2 = "token";
//        String value2 = "b4292fc39d254360f91a35eea62b4f07e7e352a8";
//
//        String key3 = "clientType";
//        String value3 = "android_%E7%99%BE%E5%BA%A6";

//        初始化集合,并将请求体添加到集合中
        Map<String, String> postMap = new HashMap<>();
        postMap.put(key1, value1);
//        postMap.put(key2, value2);
//        postMap.put(key3, value3);

//        调用封装好的post网络请求,通过gson解析将结果传入适配器
        VolleyManager.getInstance().startStringRequestNet(Request.Method.POST, postUrl, postMap, null, new OnNetResultListener() {
            @Override
            public void onSuccessful(String resultStr) {
                Gson gson = new Gson();

                Log.d("SubscribeFragment", resultStr);
                SubscribeEntity subscribeEntity = gson.fromJson(resultStr, SubscribeEntity.class);
                subscribeEntityList = subscribeEntity.getData().getUperList();
                Log.d("SubscribeFragment", "subscribeEntityList.size():" + subscribeEntityList.size());
                adapter.setSubscribeEntityList(subscribeEntityList);
            }

//            当没有解析成功弹出toast
            @Override
            public void onFailure(String errMsg) {

                ToastTool.showDebugToast(getContext(), "没有解析成功");
            }
        });

    }
}
