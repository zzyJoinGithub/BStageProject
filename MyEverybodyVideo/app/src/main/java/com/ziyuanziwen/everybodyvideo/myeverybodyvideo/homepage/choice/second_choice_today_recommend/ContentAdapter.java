package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.second_choice_today_recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 17/3/1.
 */

public class ContentAdapter extends RecyclerView.Adapter {

    private Context context;


    public ContentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class TodayRecommendSecondTitleViewHolder extends RecyclerView.ViewHolder {
        TextView fragment_choiceTodayRecommendSecondTitleTv;
        TextView fragment_choiceTodayRecommendSecondPlayTimeTv;
        TextView fragment_choiceTodayRecommendSecondContentTv;
        RecyclerView fragment_choiceTodayRecommendSecondTagRv;

        public TodayRecommendSecondTitleViewHolder(View itemView) {
            super(itemView);
        }
    }


    class TodayRecommendSecondSubscribeViewHolder extends RecyclerView.ViewHolder {
        CircleImageView fragment_choiceTodayRecommendSecondSubscribeCircleIv;
        TextView fragment_choiceTodayRecommendSecondSubscribeTitleTv;
        TextView fragment_choiceTodayRecommendSecondSubscribeNumberOfPeopleTv;
        ImageView fragment_choiceTodayRecommendSecondSubscribeIv;


        public TodayRecommendSecondSubscribeViewHolder(View itemView) {
            super(itemView);
        }
    }

    class TodayRecommendSecondCollectionViewHolder extends RecyclerView.ViewHolder {
        TextView fragment_choiceTodayRecommendSecondCompilationsTv;
        TextView fragment_choiceTodayRecommendSecondCompilationsMoreTv;
        RecyclerView fragment_choiceTodayRecommendSecondCompilationsRv;

        public TodayRecommendSecondCollectionViewHolder(View itemView) {
            super(itemView);
        }
    }

    class TodayRecommendSecondCorrelationRecommendViewHolder extends RecyclerView.ViewHolder {
        ListView fragment_choiceTodayRecommendSecondCorrelationRecommendLv;

        public TodayRecommendSecondCorrelationRecommendViewHolder(View itemView) {
            super(itemView);
        }
    }
}
