package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.second_choice_today_recommend;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base.BaseActivity;

import java.util.List;

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
    private List<Integer> videoId;

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
    }

    @Override
    protected void initData() {

    }
}
