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
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.login.LoginActivity;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net.OnNetResultListener;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net.VolleyManager;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.util.LogTool;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.util.ToastTool;

import java.util.HashMap;
import java.util.Map;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by dllo on 17/3/1.
 */

public class ContentActivity extends BaseActivity implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnInfoListener, View.OnClickListener {

    //视频地址
//    private String path = "http://baobab.wdjcdn.com/145076769089714.mp4";
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
    private boolean isLike = false;
    private boolean isLogin = false;
    //所有视频获取的接口
    private String videoPath = "http://api.rr.tv/video/findM3u8ByVideoId";
    private ContentEntity contentEntity;

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
        pb = (ProgressBar) findViewById(R.id.probar);
        downloadRateView = (TextView) findViewById(R.id.download_rate);
        loadRateView = (TextView) findViewById(R.id.load_rate);
        shareIv.setOnClickListener(this);
        cacheIv.setOnClickListener(this);
        likeIv.setOnClickListener(this);

    }

    @Override
    protected void initData() {

        ShareSDK.initSDK(this);//这句话一定要在调用方法之前写出来
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
                contentEntity = gson.fromJson(resultStr, ContentEntity.class);
                LogTool.logI("ContentActivity", contentEntity.getData().getRecommendVideoList().get(0).getTitle());
                adapter.setContentEntity(contentEntity);

                final String videoKey1 = "clientVersion";
                String videoValue1 = "3.5.2";
                String videoKey2 = "videoId";
                String videoValue2 = contentEntity.getData().getVideoDetailView().getId() + "";
                Map<String, String> mapBodyVideo = new HashMap<>();
                mapBodyVideo.put(videoKey2, videoValue2);
                Map<String, String> mapHeaderVideo = new HashMap<>();
                mapHeaderVideo.put(videoKey1, videoValue1);

                VolleyManager.getInstance().startStringRequestNet(Request.Method.POST, videoPath, mapHeaderVideo, mapBodyVideo, new OnNetResultListener() {
                    @Override
                    public void onSuccessful(String resultStr) {
                        VideoPlayBean videoPlayBean = new Gson().fromJson(resultStr, VideoPlayBean.class);

                        uri = Uri.parse(videoPlayBean.getData().getM3u8().getUrl());
                        mVideoView.setVideoURI(uri);//设置视频播放地址

                        mCustomMediaController.show(5000);
                        mVideoView.setMediaController(mCustomMediaController);
                        mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);//高画质
                        mVideoView.requestFocus();
                        mVideoView.setOnInfoListener(ContentActivity.this);
                        mVideoView.setOnBufferingUpdateListener(ContentActivity.this);
                        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.setPlaybackSpeed(1.0f);
                            }
                        });
                    }

                    @Override
                    public void onFailure(String errMsg) {
                        LogTool.logI("contentAty","获取播放路径失败");
                    }
                });

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
        if (mVideoView != null) {
            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_choiceSecondTodayRecommendLikeIv:
                if (isLogin == true){
                    if (isLike == true){
                        likeIv.setImageResource(R.mipmap.ic_like_h);
                        LogTool.logI("Adapter", "红色");
                        isLike = false;
                    }else{
                        likeIv.setImageResource(R.mipmap.ic_like_);
                        LogTool.logI("Adapter", "无色");
                        isLike = true;
                    }
                }else {
                    LogTool.logI("Adapter", "跳转,boolean类型值都变为true");
                    goTo(LoginActivity.class);
                    isLike = true;
                    isLogin = true;
                }
                break;
            case R.id.fragment_choiceSecondTodayRecommendCacheIv:
                break;
            case R.id.fragment_choiceSecondTodayRecommendShareIv:
                showShare(contentEntity.getData().getVideoDetailView().getTitle(), contentEntity.getData().getVideoDetailView().getBrief(),
                        contentEntity.getData().getVideoDetailView().getCover(), "");
                break;
        }
    }

    private void showShare(String title, String content, String imageUrl, String shareUrl) {
//        ShareSDK.initSDK(context);//这句话一定要在调用方法之前写出来

        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle(title);
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(shareUrl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(content);
//        if (musicTransferBean.getTransferBeens().get(musicPosition).getMusicImage().equals("没有专辑图片")) {
//            //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
//            oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
//        } else {
//            oks.setImageUrl(musicTransferBean.getTransferBeens().get(musicPosition).getMusicLoad());
//        }
        oks.setImageUrl(imageUrl);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(shareUrl);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(shareUrl);
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(shareUrl);

// 启动分享GUI
        oks.show(this);
    }
}
