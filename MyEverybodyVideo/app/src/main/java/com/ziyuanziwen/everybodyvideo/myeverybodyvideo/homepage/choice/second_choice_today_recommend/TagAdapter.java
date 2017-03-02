package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.second_choice_today_recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;

/**
 * Created by dllo on 17/3/1.
 */

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TodayRecommendSecondTagViewHolder> {

    private Context context;
    private ContentEntity contentEntity;

    public TagAdapter(Context context) {
        this.context = context;
    }

    public void setContentEntity(ContentEntity contentEntity) {
        this.contentEntity = contentEntity;
        notifyDataSetChanged();
    }

    @Override
    public TodayRecommendSecondTagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_second_fragment_choice_today_recommend_tag, parent, false);
        TodayRecommendSecondTagViewHolder tagViewHolder = new TodayRecommendSecondTagViewHolder(view);
        return tagViewHolder;
    }

    @Override
    public void onBindViewHolder(TodayRecommendSecondTagViewHolder holder, int position) {
        holder.fragmentChoiceTodayRecommendSecondTagTv.setText(contentEntity.
                getData().getVideoDetailView().getTagList().get(position).getName());
    }

    @Override
    public int getItemCount() {
        return contentEntity != null ? contentEntity.getData().getVideoDetailView().getTagList().size() : 0;
    }

    class TodayRecommendSecondTagViewHolder extends RecyclerView.ViewHolder {
        TextView fragmentChoiceTodayRecommendSecondTagTv;

        public TodayRecommendSecondTagViewHolder(View itemView) {
            super(itemView);
            fragmentChoiceTodayRecommendSecondTagTv = (TextView) itemView.findViewById(R.id.fragment_choiceTodayRecommendSecondTagTv);
        }

    }
}
