package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

//import com.youth.banner.Banner;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;

import java.util.List;

/**
 * Created by dllo on 17/2/27.
 */

public class ChoiceAdapter extends RecyclerView.Adapter {

    private static final int BANNER = 0;
    private static final int TODAYRECOMMEND = 1;
    private static final int SAME = 2;
    private static final int UPMAINRECOMMEND = 3;
    private static final int EXCHANGE = 4;

    //    up主推荐集合
    private List<ChoiceEntity.DataBean.RecommendUpBean> upBeanList;
    //    轮播图集合
    private List<ChoiceEntity.DataBean.BannerTopBean> bannerBeanList;
    //    奥斯卡集合
    private List<ChoiceEntity.DataBean.OfficalAlbumBean> resultListBeanList;
    //    今日推荐集合
    private List<ChoiceEntity.DataBean.TodayRecommendBean> todayRecommendBeanList;
    //    分类集合
    private List<ChoiceEntity.DataBean.CategoryListBean> categoryListBeanList;
    private Context context;


    public ChoiceAdapter(Context context) {
        this.context = context;
    }

    public void setUpBeanList(List<ChoiceEntity.DataBean.RecommendUpBean> upBeanList) {
        this.upBeanList = upBeanList;
    }

    public void setBannerBeanList(List<ChoiceEntity.DataBean.BannerTopBean> bannerBeanList) {
        this.bannerBeanList = bannerBeanList;
    }

    public void setResultListBeanList(List<ChoiceEntity.DataBean.OfficalAlbumBean> resultListBeanList) {
        this.resultListBeanList = resultListBeanList;
    }

    public void setTodayRecommendBeanList(List<ChoiceEntity.DataBean.TodayRecommendBean> todayRecommendBeanList) {
        this.todayRecommendBeanList = todayRecommendBeanList;
    }

    public void setCategoryListBeanList(List<ChoiceEntity.DataBean.CategoryListBean> categoryListBeanList) {
        this.categoryListBeanList = categoryListBeanList;
    }


    @Override
    public int getItemViewType(int position) {

        switch (position) {
            case 0:
                return BANNER;
            case 1:
                return TODAYRECOMMEND;
            case 2:
            case 8:
            case 12:
                return EXCHANGE;
            case 3:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
                return SAME;
            case 4:
                return UPMAINRECOMMEND;
            default:
                return BANNER;

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case BANNER:
                View bannerView = LayoutInflater.from(context).inflate(R.layout.item_fragment_choice_bannner, parent, false);
                viewHolder = new BannerViewHolder(bannerView);
                break;
            case TODAYRECOMMEND:
                View todayRecommendView = LayoutInflater.from(context).inflate(R.layout.item_fragment_choice_today_recommend_title_gridview, parent, false);
                viewHolder = new TodayRecommendViewHolder(todayRecommendView);
                break;
            case EXCHANGE:
                View exchangeView = LayoutInflater.from(context).inflate(R.layout.item_fragment_choice_exchange, parent, false);
                viewHolder = new ExchangeViewHolder(exchangeView);
                break;
            case SAME:
                View sameView = LayoutInflater.from(context).inflate(R.layout.item_fragment_choice_today_recommend_same, parent, false);
                viewHolder = new SameViewHolder(sameView);
                break;
            case UPMAINRECOMMEND:
                View mainRecommendView = LayoutInflater.from(context).inflate(R.layout.item_fragment_choice_up_main_recommend_title_recyclerview, parent, false);
                viewHolder = new MainRecommendViewHolder(mainRecommendView);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int viewType = getItemViewType(position);
        switch (viewType) {
            case SAME:

        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    private class BannerViewHolder extends RecyclerView.ViewHolder {
//        Banner banner;

        public BannerViewHolder(View bannerView) {
            super(bannerView);
//            banner = (Banner) bannerView.findViewById(R.id.fragment_choiceBanner);
        }
    }

    private class TodayRecommendViewHolder extends RecyclerView.ViewHolder {
        TextView billboardTv;
        GridView gridView;

        public TodayRecommendViewHolder(View todayRecommendView) {
            super(todayRecommendView);
            billboardTv = (TextView) todayRecommendView.findViewById(R.id.fragment_choiceBillboardTv);
            gridView = (GridView) todayRecommendView.findViewById(R.id.fragment_choiceGridView);
        }
    }

    private class ExchangeViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;

        public ExchangeViewHolder(View exchangeView) {
            super(exchangeView);
            relativeLayout = (RelativeLayout) exchangeView.findViewById(R.id.fragment_choiceExchangeRv);
        }
    }

    private class SameViewHolder extends RecyclerView.ViewHolder {
        TextView sameMoreTv;
        TextView firstTitleTv;
        TextView firstTimeTv;
        TextView secondLeftContentTv;
        TextView secondLeftTimeTv;
        TextView secondRightContentTv;
        TextView secondRightTimeTv;
        ImageView firstIv;
        ImageView secondLeftIv;
        ImageView secondRightIv;

        public SameViewHolder(View sameView) {
            super(sameView);

            sameMoreTv = (TextView) sameView.findViewById(R.id.fragment_choiceDailyMoreTv);
            firstIv = (ImageView) sameView.findViewById(R.id.fragment_choiceDailyBigPictureIv);
            firstTitleTv = (TextView) sameView.findViewById(R.id.fragment_choiceDailyBigPictureTitleTv);
            firstTimeTv = (TextView) sameView.findViewById(R.id.fragment_choiceDailyBigPictureTimeTv);
            secondLeftIv = (ImageView) sameView.findViewById(R.id.fragment_choiceDailySecondPictureFirstIv);
            secondLeftTimeTv = (TextView) sameView.findViewById(R.id.fragment_choiceDailySecondPictureFirstTimeTv);
            secondLeftContentTv = (TextView) sameView.findViewById(R.id.fragment_choiceDailySecondPictureFirstTitleTv);
            secondRightIv = (ImageView) sameView.findViewById(R.id.fragment_choiceDailySecondPictureSecondIv);
            secondRightTimeTv = (TextView) sameView.findViewById(R.id.fragment_choiceDailySecondPictureSecondTimeTv);
            secondRightContentTv = (TextView) sameView.findViewById(R.id.fragment_choiceDailySecondPictureSecondTitleTv);
        }
    }

    private class MainRecommendViewHolder extends RecyclerView.ViewHolder {
        TextView mainRecommendMoreTv;
        RecyclerView recyclerView;

        public MainRecommendViewHolder(View mainRecommendView) {
            super(mainRecommendView);
            mainRecommendMoreTv = (TextView) mainRecommendView.findViewById(R.id.fragment_choiceUpMainRecommendMoreTv);
            recyclerView = (RecyclerView) mainRecommendView.findViewById(R.id.fragment_choiceUpMainRecommendRv);
        }
    }
}
