package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.second_choice_today_recommend;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;

/**
 * Created by dllo on 17/3/1.
 */

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TodayRecommendSecondTagViewHolder> {

    @Override
    public TodayRecommendSecondTagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TodayRecommendSecondTagViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class TodayRecommendSecondTagViewHolder extends RecyclerView.ViewHolder {
        TextView fragmentChoiceTodayRecommendSecondTagTv;

        public TodayRecommendSecondTagViewHolder(View itemView) {
            super(itemView);
            fragmentChoiceTodayRecommendSecondTagTv = (TextView) itemView.findViewById(R.id.fragment_choiceTodayRecommendSecondTagTv);
        }

    }
}
