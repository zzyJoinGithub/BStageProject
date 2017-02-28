package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.bean.DataBean;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.bean.FeaturedBean;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net.ImageManagersFactory;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Zzy on 2017/2/27-19:47.
 * ✎﹏﹏﹏.₯㎕*﹏﹏﹏
 * 　　忍把浮名，
 * 　　　　换了浅斟低唱。
 * 　　　　 ﹏﹏﹏♥♥赵子源✍♥♥﹏﹏
 * 推荐主播模块的适配器
 */

public class RecommendUpAdapter extends RecyclerView.Adapter<RecommendUpAdapter.RecommendUpViewHolder>{
    private Context context;
    //所有数据集合
    private FeaturedBean featuredBean;

    public RecommendUpAdapter(Context context) {
        super();
        this.context = context;
    }

    public RecommendUpAdapter setFeaturedBean(FeaturedBean featuredBean) {
        this.featuredBean = featuredBean;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public RecommendUpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fragment_choice_up_main_recommend,parent,false);
        RecommendUpViewHolder recommendUpViewHolder = new RecommendUpViewHolder(view);
        return recommendUpViewHolder;
    }

    @Override
    public void onBindViewHolder(RecommendUpViewHolder holder, int position) {
        holder.fragmentChoiceUpMainRecommendTitleTv.setText(featuredBean.getData().getRecommendUp().get(position).getName());
        holder.fragmentChoiceUpMainRecommendContentTv.setText(featuredBean.getData().getRecommendUp().get(position).getIntro());
        ImageManagersFactory.getImageManager(ImageManagersFactory.GLIDE).loadImageView(context,featuredBean.getData().getRecommendUp().get(position).getHeadImg(),holder.fragmentChoiceUpMainRecommendCircleIv);
    }

    @Override
    public int getItemCount() {
        return featuredBean != null ? featuredBean.getData().getRecommendUp().size() : 0;
    }

    public class RecommendUpViewHolder extends RecyclerView.ViewHolder {
        CircleImageView fragmentChoiceUpMainRecommendCircleIv;
        TextView fragmentChoiceUpMainRecommendTitleTv;
        TextView fragmentChoiceUpMainRecommendContentTv;
        ImageView fragmentChoiceUpMainRecommendSubscribeIv;
        public RecommendUpViewHolder(View itemView) {
            super(itemView);
            fragmentChoiceUpMainRecommendCircleIv = (CircleImageView) itemView.findViewById(R.id.fragment_choiceUpMainRecommendCircleIv);
            fragmentChoiceUpMainRecommendTitleTv = (TextView) itemView.findViewById(R.id.fragment_choiceUpMainRecommendTitleTv);
            fragmentChoiceUpMainRecommendContentTv = (TextView) itemView.findViewById(R.id.fragment_choiceUpMainRecommendContentTv);
            fragmentChoiceUpMainRecommendSubscribeIv = (ImageView) itemView.findViewById(R.id.fragment_choiceUpMainRecommendSubscribeIv);
        }
    }
}
