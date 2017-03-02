package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.second_choice_today_recommend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base.BaseActivity;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net.OnNetResultListener;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net.VolleyManager;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.util.LogTool;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.util.ToastTool;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 17/3/1.
 */

public class ContentActivity extends BaseActivity {

    private VideoView videoView;
    private ImageView playIv;
    private ImageView fullScreenIv;
    private ImageView commentsIv;
    private ImageView likeIv;
    private ImageView cacheIv;
    private ImageView shareIv;
    private TextView commentsTv;
    private RecyclerView recyclerView;
    private Bundle bundle;
    private ContentAdapter adapter;

    protected int getLayout() {
        return R.layout.activity_second_today_recommend;
    }

    @Override
    protected void initView() {
        videoView = byView(R.id.fragment_choiceTodayRecommendVideoView);
        playIv = byView(R.id.fragment_choiceTodayRecommendPlayIv);
        fullScreenIv = byView(R.id.fragment_choiceTodayRecommendFullScreenIv);
        commentsIv = byView(R.id.fragment_choiceSecondTodayRecommendCommentsIv);
        likeIv = byView(R.id.fragment_choiceSecondTodayRecommendLikeIv);
        cacheIv = byView(R.id.fragment_choiceSecondTodayRecommendCacheIv);
        shareIv = byView(R.id.fragment_choiceSecondTodayRecommendShareIv);
        commentsTv = byView(R.id.fragment_choiceSecondTodayRecommendCommentsTv);
        recyclerView = byView(R.id.fragment_choiceSecondTodayRecommendRv);
    }

    @Override
    protected void initData() {

        bundle = new Bundle();
        Intent intent = getIntent();
        bundle = intent.getExtras();
        adapter = new ContentAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        getPostRequest();
        recyclerView.setAdapter(adapter);


    }


    private void getPostRequest() {

        String postUrl = "http://api.rr.tv/v3plus/video/detail";
        String key1 = "clientVersion";
        String value1 = "3.5.3.1";
        String key2 = "videoId";
        String value2 = String.valueOf(bundle.getInt("id"));

        Map<String, String> mapBody = new HashMap<>();
        mapBody.put(key2, value2);
        Map<String, String> mapHeader = new HashMap<>();
        mapHeader.put(key1, value1);
        VolleyManager.getInstance().startStringRequestNet(Request.Method.POST, postUrl, mapHeader, mapBody, new OnNetResultListener() {
            @Override
            public void onSuccessful(String resultStr) {
                Gson gson = new Gson();
                ContentEntity contentEntity = gson.fromJson(resultStr, ContentEntity.class);
                LogTool.logI("ContentActivity", contentEntity.getData().getRecommendVideoList().get(0).getTitle());
                adapter.setContentEntity(contentEntity);

            }

            @Override
            public void onFailure(String errMsg) {
                ToastTool.showDebugToast(ContentActivity.this, "解析失败");
            }
        });
    }
}
