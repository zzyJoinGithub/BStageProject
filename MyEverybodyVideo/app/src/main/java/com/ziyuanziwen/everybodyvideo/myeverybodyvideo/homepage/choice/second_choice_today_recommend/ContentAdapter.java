package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.second_choice_today_recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 17/3/1.
 */

public class ContentAdapter extends RecyclerView.Adapter {

    private Context context;
    private static final int title = 0;
    private static final int subscribe = 1;
    private static final int recommend = 2;


    public ContentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return title;
            case 1:
                return subscribe;
            default:
                return recommend;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case title:
                View titleView = LayoutInflater.from(context).inflate(R.layout.item_second_fragment_choice_today_recommend_title, parent, false);
                viewHolder = new TodayRecommendSecondTitleViewHolder(titleView);
                break;
            case subscribe:
                View subscribeView = LayoutInflater.from(context).inflate(R.layout.item_second_fragment_choice_today_recommend_subscribe, parent, false);
                viewHolder = new TodayRecommendSecondSubscribeViewHolder(subscribeView);
                break;
            case recommend:
                View recommendView = LayoutInflater.from(context).inflate(R.layout.item_second_fragment_choice_today_recommend_correlation_recommend, parent, false);
                viewHolder = new TodayRecommendSecondCorrelationRecommendViewHolder(recommendView);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class TodayRecommendSecondTitleViewHolder extends RecyclerView.ViewHolder {
        TextView fragmentChoiceTodayRecommendSecondTitleTv;
        TextView fragmentChoiceTodayRecommendSecondPlayTimeTv;
        TextView fragmentChoiceTodayRecommendSecondContentTv;
        RecyclerView fragmentChoiceTodayRecommendSecondTagRv;

        public TodayRecommendSecondTitleViewHolder(View itemView) {
            super(itemView);
            fragmentChoiceTodayRecommendSecondTitleTv = (TextView) itemView.findViewById(R.id.fragment_choiceTodayRecommendSecondTitleTv);
            fragmentChoiceTodayRecommendSecondPlayTimeTv = (TextView) itemView.findViewById(R.id.fragment_choiceTodayRecommendSecondPlayTimeTv);
            fragmentChoiceTodayRecommendSecondContentTv = (TextView) itemView.findViewById(R.id.fragment_choiceTodayRecommendSecondContentTv);
            fragmentChoiceTodayRecommendSecondTagRv = (RecyclerView) itemView.findViewById(R.id.fragment_choiceTodayRecommendSecondTagRv);
        }
    }


    class TodayRecommendSecondSubscribeViewHolder extends RecyclerView.ViewHolder {
        CircleImageView fragmentChoiceTodayRecommendSecondSubscribeCircleIv;
        TextView fragmentChoiceTodayRecommendSecondSubscribeTitleTv;
        TextView fragmentChoiceTodayRecommendSecondSubscribeNumberOfPeopleTv;
        ImageView fragmentChoiceTodayRecommendSecondSubscribeIv;


        public TodayRecommendSecondSubscribeViewHolder(View itemView) {
            super(itemView);
            fragmentChoiceTodayRecommendSecondSubscribeCircleIv = (CircleImageView) itemView.findViewById(R.id.fragment_choiceTodayRecommendSecondSubscribeCircleIv);
            fragmentChoiceTodayRecommendSecondSubscribeTitleTv = (TextView) itemView.findViewById(R.id.fragment_choiceTodayRecommendSecondSubscribeTitleTv);
            fragmentChoiceTodayRecommendSecondSubscribeNumberOfPeopleTv = (TextView) itemView.findViewById(R.id.fragment_choiceTodayRecommendSecondSubscribeNumberTv);
            fragmentChoiceTodayRecommendSecondSubscribeIv = (ImageView) itemView.findViewById(R.id.fragment_choiceTodayRecommendSecondSubscribeIv);

        }
    }


    class TodayRecommendSecondCorrelationRecommendViewHolder extends RecyclerView.ViewHolder {
        ListView fragmentChoiceTodayRecommendSecondCorrelationRecommendLv;

        public TodayRecommendSecondCorrelationRecommendViewHolder(View itemView) {
            super(itemView);
            fragmentChoiceTodayRecommendSecondCorrelationRecommendLv = (ListView) itemView.findViewById(R.id.fragment_choiceTodayRecommendSecondCorrelationRecommendLv);
        }
    }
}
