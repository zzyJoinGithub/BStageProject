package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.customize.OnRecyclerViewClickListener;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.bean.BannerBean;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.bean.FeaturedBean;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net.ImageManagersFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzy on 17/2/27.
 * 精选页适配器  zzy
 */

public class ChoiceAdapter extends RecyclerView.Adapter {

    private static final int BANNER = 0;                            //轮播图
    private static final int TODAYRECOMMEND = 1;        //今日推荐
    private static final int SAME = 2;                              //相同的界面
    private static final int UPMAINRECOMMEND = 3;   //主推荐
    private static final int EXCHANGE = 4;                      //换一批

    private Context context;
    private OnRecyclerViewClickListener onRecyclerViewClickListener;

    public ChoiceAdapter setOnRecyclerViewClickListener(OnRecyclerViewClickListener onRecyclerViewClickListener) {
        this.onRecyclerViewClickListener = onRecyclerViewClickListener;
        notifyDataSetChanged();
        return this;
    }

    public ChoiceAdapter(Context context) {
        super();
        this.context = context;
    }

    //所有数据集合
    private FeaturedBean featuredBean;

    public ChoiceAdapter setFeaturedBean(FeaturedBean featuredBean) {
        this.featuredBean = featuredBean;
        notifyDataSetChanged();
        return this;
    }

    //    //    up主推荐集合
//    private List<DataBean.RecommendUpBean> upBeanList;
//    //    轮播图集合
//    private List<BannerBean> bannerBeanList;
//    //    奥斯卡集合
//    private List<DataBean.OfficalAlbumBean> resultListBeanList;
//    //    今日推荐集合
//    private List<BriefBean> todayRecommendBeanList;
//    //    分类集合
//    private List<DataBean.CategoryBean> categoryListBeanList;

//    public ChoiceAdapter setUpBeanList(List<DataBean.RecommendUpBean> upBeanList) {
//        this.upBeanList = upBeanList;
//        notifyDataSetChanged();
//        return this;
//    }
//
//    public ChoiceAdapter setBannerBeanList(List<BannerBean> bannerBeanList) {
//        this.bannerBeanList = bannerBeanList;
//        notifyDataSetChanged();
//        return this;
//    }
//
//    public ChoiceAdapter setResultListBeanList(List<DataBean.OfficalAlbumBean> resultListBeanList) {
//        this.resultListBeanList = resultListBeanList;
//        notifyDataSetChanged();
//        return this;
//    }
//
//    public ChoiceAdapter setTodayRecommendBeanList(List<BriefBean> todayRecommendBeanList) {
//        this.todayRecommendBeanList = todayRecommendBeanList;
//        notifyDataSetChanged();
//        return this;
//    }
//
//    public ChoiceAdapter setCategoryListBeanList(List<DataBean.CategoryBean> categoryListBeanList) {
//        this.categoryListBeanList = categoryListBeanList;
//        notifyDataSetChanged();
//        return this;
//    }

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
            case BANNER:
                List<String> banners = new ArrayList<>();
                for (BannerBean bannerBean : featuredBean.getData().getBannerTop()) {
                    banners.add(bannerBean.getImgUrl());
                }
                BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
                bannerViewHolder.banner.setImageLoader(new ChoiceBannerTop());
                bannerViewHolder.banner.setImages(banners);
                bannerViewHolder.banner.isAutoPlay(true);
                bannerViewHolder.banner.setDelayTime(2000);
                bannerViewHolder.banner.start();
                break;
            case TODAYRECOMMEND:
                TodayRecommendViewHolder todayRecommendViewHolder = (TodayRecommendViewHolder) holder;
                TodayRecommendAdapter todayRecommendAdapter = new TodayRecommendAdapter(context);
                todayRecommendViewHolder.gridView.setAdapter(todayRecommendAdapter);
                todayRecommendAdapter.setFeaturedBean(featuredBean);
                todayRecommendViewHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("TODAYRECOMMEND",position);
                        onRecyclerViewClickListener.onRecyclerViewClickListener(position,bundle);
                    }
                });
                break;
            case EXCHANGE:
                ExchangeViewHolder exchangeViewHolder = (ExchangeViewHolder) holder;
                break;
            case SAME:
                SameViewHolder sameViewHolder = (SameViewHolder) holder;
                if (3 == position) {
                    sameViewHolder.fragmentChoiceDailyTitleTv.setText(featuredBean.getData().getOfficalAlbum().get(0).getName());
                    ImageManagersFactory.getImageManager(ImageManagersFactory.GLIDE).loadImageView(context, featuredBean.getData().getOfficalAlbum().get(0).getResultList().get(0).getCover(), sameViewHolder.firstIv);
                    sameViewHolder.firstTitleTv.setText(featuredBean.getData().getOfficalAlbum().get(0).getResultList().get(0).getTitle());
                    sameViewHolder.firstTimeTv.setText(featuredBean.getData().getOfficalAlbum().get(0).getResultList().get(0).getDuration());
                    ImageManagersFactory.getImageManager(ImageManagersFactory.GLIDE).loadImageView(context, featuredBean.getData().getOfficalAlbum().get(0).getResultList().get(1).getCover(), sameViewHolder.secondLeftIv);
                    sameViewHolder.secondLeftTimeTv.setText(featuredBean.getData().getOfficalAlbum().get(0).getResultList().get(1).getDuration());
                    sameViewHolder.secondLeftContentTv.setText(featuredBean.getData().getOfficalAlbum().get(0).getResultList().get(1).getBrief());
                    ImageManagersFactory.getImageManager(ImageManagersFactory.GLIDE).loadImageView(context, featuredBean.getData().getOfficalAlbum().get(0).getResultList().get(2).getCover(), sameViewHolder.secondRightIv);
                    sameViewHolder.secondRightTimeTv.setText(featuredBean.getData().getOfficalAlbum().get(0).getResultList().get(2).getDuration());
                    sameViewHolder.secondRightContentTv.setText(featuredBean.getData().getOfficalAlbum().get(0).getResultList().get(2).getBrief());
                } else {
                    int i = 0;
                    if (position > 4 && position < 8) {
                         i = position - 5;
                    } else if (position > 8 && position < 12) {
                        i = position - 6;
                    } else if (position == 13 || position == 14){
                        i = position - 7;
                    }
                    sameViewHolder.fragmentChoiceDailyTitleTv.setText(featuredBean.getData().getCategoryList().get(i).getCategoryName());
                    ImageManagersFactory.getImageManager(ImageManagersFactory.GLIDE).loadImageView(context, featuredBean.getData().getCategoryList().get(i).getVideoList().get(0).getCover(), sameViewHolder.firstIv);
                    sameViewHolder.firstTitleTv.setText(featuredBean.getData().getCategoryList().get(i).getVideoList().get(0).getTitle());
                    sameViewHolder.firstTimeTv.setText(featuredBean.getData().getCategoryList().get(i).getVideoList().get(0).getDuration());
                    ImageManagersFactory.getImageManager(ImageManagersFactory.GLIDE).loadImageView(context, featuredBean.getData().getCategoryList().get(i).getVideoList().get(1).getCover(), sameViewHolder.secondLeftIv);
                    sameViewHolder.secondLeftTimeTv.setText(featuredBean.getData().getCategoryList().get(i).getVideoList().get(1).getDuration());
                    sameViewHolder.secondLeftContentTv.setText(featuredBean.getData().getCategoryList().get(i).getVideoList().get(1).getBrief());
                    ImageManagersFactory.getImageManager(ImageManagersFactory.GLIDE).loadImageView(context, featuredBean.getData().getCategoryList().get(i).getVideoList().get(2).getCover(), sameViewHolder.secondRightIv);
                    sameViewHolder.secondRightTimeTv.setText(featuredBean.getData().getCategoryList().get(i).getVideoList().get(2).getDuration());
                    sameViewHolder.secondRightContentTv.setText(featuredBean.getData().getCategoryList().get(i).getVideoList().get(2).getBrief());
                }
                break;
            case UPMAINRECOMMEND:
                MainRecommendViewHolder mainRecommendViewHolder = (MainRecommendViewHolder) holder;
                RecommendUpAdapter recommendUpAdapter = new RecommendUpAdapter(context);
                mainRecommendViewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                mainRecommendViewHolder.recyclerView.setAdapter(recommendUpAdapter);
                recommendUpAdapter.setFeaturedBean(featuredBean);
                break;
        }
    }

    @Override
    public int getItemCount() {
//        return upBeanList != null && bannerBeanList != null && resultListBeanList != null && todayRecommendBeanList != null && categoryListBeanList != null ? 15 : 0;
        return featuredBean != null ? 15 : 0;
    }


    private class BannerViewHolder extends RecyclerView.ViewHolder {
        Banner banner;

        public BannerViewHolder(View bannerView) {
            super(bannerView);
            banner = (Banner) bannerView.findViewById(R.id.fragment_choiceBanner);
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
        TextView fragmentChoiceDailyTitleTv;

        public SameViewHolder(View sameView) {
            super(sameView);

            fragmentChoiceDailyTitleTv = (TextView) sameView.findViewById(R.id.fragment_choiceDailyTitleTv);
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
        TextView fragmentChoiceUpMainRecommendTitleTv;
        RecyclerView recyclerView;

        public MainRecommendViewHolder(View mainRecommendView) {
            super(mainRecommendView);
            fragmentChoiceUpMainRecommendTitleTv = (TextView) mainRecommendView.findViewById(R.id.fragment_choiceUpMainRecommendTitleTv);
            mainRecommendMoreTv = (TextView) mainRecommendView.findViewById(R.id.fragment_choiceUpMainRecommendMoreTv);
            recyclerView = (RecyclerView) mainRecommendView.findViewById(R.id.fragment_choiceUpMainRecommendRv);
        }
    }
}
