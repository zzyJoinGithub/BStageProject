package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.second_choice_today_recommend;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base.BaseFragment;

import java.util.List;

/**
 * Created by dllo on 17/2/28.
 */

public class ContentFragment extends BaseFragment {

    private VideoView videoView;
    private ImageView playIv;
    private ImageView fullScreenIv;
    private ImageView commentsIv;
    private ImageView likeIv;
    private ImageView cacheIv;
    private ImageView shareIv;
    private TextView commentsTv;
    private List<Integer> videoId;



    @Override
    protected int getLayout() {
        return R.layout.fragment_second_today_recommend;
    }

    @Override
    protected void initTitle(RelativeLayout titleLayout, ImageView backIv, TextView titleTv, ImageView rightIv) {
        titleLayout.setVisibility(View.GONE);
    }

    @Override
    protected void initView(View view) {
        videoView = byView(view, R.id.fragment_choiceTodayRecommendVideoView);
        playIv = byView(view, R.id.fragment_choiceTodayRecommendPlayIv);
        fullScreenIv = byView(view, R.id.fragment_choiceTodayRecommendFullScreenIv);
        commentsIv = byView(view, R.id.fragment_choiceSecondTodayRecommendCommentsIv);
        likeIv = byView(view, R.id.fragment_choiceSecondTodayRecommendLikeIv);
        cacheIv = byView(view, R.id.fragment_choiceSecondTodayRecommendCacheIv);
        shareIv = byView(view, R.id.fragment_choiceSecondTodayRecommendShareIv);
        commentsTv = byView(view, R.id.fragment_choiceSecondTodayRecommendCommentsTv);


    }

    @Override
    protected void initData() {

        
        String postUrl = "http://api.rr.tv/v3plus/video/detail";
        String key1 = "clientVersion";
        String value1 = "3.5.3.1";
        String key2 = "videoId";


//        VolleyManager.getInstance().startStringRequestNet(Request.Method.POST, );

    }
}
