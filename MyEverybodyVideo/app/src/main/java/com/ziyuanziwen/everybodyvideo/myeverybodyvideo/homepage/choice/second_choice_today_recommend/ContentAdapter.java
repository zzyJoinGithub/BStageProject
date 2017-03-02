package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.second_choice_today_recommend;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net.ImageManagersFactory;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.util.LogTool;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 17/3/1.
 */

public class ContentAdapter extends RecyclerView.Adapter {

    private Context context;
    private static final int title = 0;
    private static final int subscribe = 1;
    private static final int recommend = 2;
    private ContentEntity contentEntity;

    public ContentAdapter(Context context) {
        this.context = context;
    }

    //    所有信息
    public void setContentEntity(ContentEntity contentEntity) {
        this.contentEntity = contentEntity;
        LogTool.logI("ContentAdapter", this.contentEntity.getData().getRecommendVideoList().size() + "");
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        LogTool.logI("ContentAdapter", "创建item类型");
        switch (position) {
            case 0:
                return title;
            case 1:
                return subscribe;
            case 2:
                return recommend;
            default:
                return title;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LogTool.logI("ContentAdapter", "创建复用机制");
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
        LogTool.logI("ContentAdapter", "铺设");
        int viewType = getItemViewType(position);
        switch (viewType) {
            case title:
                TagAdapter adapter = new TagAdapter(context);
                TodayRecommendSecondTitleViewHolder titleViewHolder = (TodayRecommendSecondTitleViewHolder) holder;
                titleViewHolder.fragmentChoiceTodayRecommendSecondTitleTv.setText(contentEntity.getData().getVideoDetailView().getTitle());
                LogTool.logI("ContentAdapter", "sssss" + contentEntity.getData().getVideoDetailView().getTitle());
                titleViewHolder.fragmentChoiceTodayRecommendSecondContentTv.setText(contentEntity.getData().getVideoDetailView().getBrief());
                String viewCount = contentEntity.getData().getVideoDetailView().getViewCount() + "";
                String reviseFirstViewCount = viewCount.substring(0, viewCount.length() - 3);
                if (viewCount.length() < 5) {
                    titleViewHolder.fragmentChoiceTodayRecommendSecondPlayTimeTv.setText(viewCount + "次播放");
                } else {

                    titleViewHolder.fragmentChoiceTodayRecommendSecondPlayTimeTv.setText(reviseFirstViewCount.substring
                            (0, reviseFirstViewCount.length() - 1) + "." + reviseFirstViewCount.substring(reviseFirstViewCount.length() - 1, reviseFirstViewCount.length()) + "万次播放");
                }
                titleViewHolder.fragmentChoiceTodayRecommendSecondTagRv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                adapter.setContentEntity(contentEntity);
                titleViewHolder.fragmentChoiceTodayRecommendSecondTagRv.setAdapter(adapter);
                break;
            case subscribe:
                TodayRecommendSecondSubscribeViewHolder subscribeViewHolder = (TodayRecommendSecondSubscribeViewHolder) holder;
                subscribeViewHolder.fragmentChoiceTodayRecommendSecondSubscribeTitleTv.setText(contentEntity.getData().getVideoDetailView().getAuthor().getNickName());
                subscribeViewHolder.fragmentChoiceTodayRecommendSecondSubscribeNumberOfPeopleTv.setText(contentEntity.getData().getVideoDetailView().getAuthor().getFansCount() + "人订阅");
                ImageManagersFactory.getImageManager(ImageManagersFactory.GLIDE).loadImageView(
                        context, contentEntity.getData().getVideoDetailView().getAuthor().getHeadImgUrl(),
                        subscribeViewHolder.fragmentChoiceTodayRecommendSecondSubscribeCircleIv);
                subscribeViewHolder.fragmentChoiceTodayRecommendSecondSubscribeIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                break;
            case recommend:
                TodayRecommendSecondCorrelationRecommendViewHolder recommendViewHolder = (TodayRecommendSecondCorrelationRecommendViewHolder) holder;
                CorrelationRecommendAdapter recommendAdapter = new CorrelationRecommendAdapter(context);
                recommendAdapter.setContentEntity(contentEntity);
                recommendViewHolder.fragmentChoiceTodayRecommendSecondCorrelationRecommendLv.setAdapter(recommendAdapter);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return contentEntity != null ? 3 : 0;
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
