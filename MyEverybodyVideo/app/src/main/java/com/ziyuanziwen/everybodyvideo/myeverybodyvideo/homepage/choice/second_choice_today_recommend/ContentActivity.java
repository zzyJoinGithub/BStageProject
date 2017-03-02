package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.second_choice_today_recommend;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by dllo on 17/3/1.
 */

public class ContentActivity extends BaseActivity implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnInfoListener {

    //视频地址
    private String path = "http://baobab.wdjcdn.com/145076769089714.mp4";
    private Uri uri;
    private ProgressBar pb;
    private TextView downloadRateView, loadRateView;
    private CustomMediaController mCustomMediaController;
    private VideoView mVideoView;
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

        commentsIv = byView(R.id.fragment_choiceSecondTodayRecommendCommentsIv);
        likeIv = byView(R.id.fragment_choiceSecondTodayRecommendLikeIv);
        cacheIv = byView(R.id.fragment_choiceSecondTodayRecommendCacheIv);
        shareIv = byView(R.id.fragment_choiceSecondTodayRecommendShareIv);
        commentsTv = byView(R.id.fragment_choiceSecondTodayRecommendCommentsTv);
        recyclerView = byView(R.id.fragment_choiceSecondTodayRecommendRv);
        mVideoView = (VideoView) findViewById(R.id.buffer);
        mCustomMediaController = new CustomMediaController(this, mVideoView, this);
        mCustomMediaController.setVideoName("白火锅 x 红火锅");
        pb = (ProgressBar) findViewById(R.id.probar);
        downloadRateView = (TextView) findViewById(R.id.download_rate);
        loadRateView = (TextView) findViewById(R.id.load_rate);
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

        uri = Uri.parse(path);
        mVideoView.setVideoURI(uri);//设置视频播放地址

        mCustomMediaController.show(5000);
        mVideoView.setMediaController(mCustomMediaController);
        mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);//高画质
        mVideoView.requestFocus();
        mVideoView.setOnInfoListener(this);
        mVideoView.setOnBufferingUpdateListener(this);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });

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


    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                if (mVideoView.isPlaying()) {
                    mVideoView.pause();
                    pb.setVisibility(View.VISIBLE);
                    downloadRateView.setText("");
                    loadRateView.setText("");
                    downloadRateView.setVisibility(View.VISIBLE);
                    loadRateView.setVisibility(View.VISIBLE);

                }
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                mVideoView.start();
                pb.setVisibility(View.GONE);
                downloadRateView.setVisibility(View.GONE);
                loadRateView.setVisibility(View.GONE);
                break;
            case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                downloadRateView.setText("" + extra + "kb/s" + "  ");
                break;
        }
        return true;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        loadRateView.setText(percent + "%");

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //屏幕切换时，设置全屏
        if (mVideoView != null){
            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        }
        super.onConfigurationChanged(newConfig);
    }

}
