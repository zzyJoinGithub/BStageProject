package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.second_choice_today_recommend;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;

/**
 * Created by dllo on 17/3/1.
 */

public class  CorrelationRecommendAdapter extends RecyclerView.Adapter<CorrelationRecommendAdapter.CorrelationRecommendViewHolder> {

    @Override
    public CorrelationRecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CorrelationRecommendViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CorrelationRecommendViewHolder extends RecyclerView.ViewHolder {
        ImageView fragmentChoiceSecondTodayRecommendCorrelationRecommendVideoIv;
        TextView fragmentChoiceSecondTodayRecommendCorrelationRecommendTimeTv;
        TextView fragmentChoiceSecondTodayRecommendCorrelationRecommendVideoTitleTv;
        TextView fragmentChoiceSecondTodayRecommendCorrelationRecommendVideoNumberOfPeopleTv;

        public CorrelationRecommendViewHolder(View itemView) {
            super(itemView);
            fragmentChoiceSecondTodayRecommendCorrelationRecommendVideoIv = (ImageView) itemView.findViewById(R.id.fragment_choiceSecondTodayRecommendCorrelationRecommendVideoIv);
            fragmentChoiceSecondTodayRecommendCorrelationRecommendTimeTv = (TextView) itemView.findViewById(R.id.fragment_choiceSecondTodayRecommendCorrelationRecommendVideoTimesTv);
            fragmentChoiceSecondTodayRecommendCorrelationRecommendVideoTitleTv = (TextView) itemView.findViewById(R.id.fragment_choiceSecondTodayRecommendCorrelationRecommendVideoTitleTv);
            fragmentChoiceSecondTodayRecommendCorrelationRecommendVideoNumberOfPeopleTv = (TextView) itemView.findViewById(R.id.fragment_choiceSecondTodayRecommendCorrelationRecommendVideoNumberOfPeopleTv);
        }
    }
}
