package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.bean.BriefBean;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.bean.FeaturedBean;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net.ImageManagersFactory;

import java.util.List;

/**
 * Created by Zzy on 2017/2/27-21:05.
 * ✎﹏﹏﹏.₯㎕*﹏﹏﹏
 * 　　忍把浮名，
 * 　　　　换了浅斟低唱。
 * 　　　　 ﹏﹏﹏♥♥赵子源✍♥♥﹏﹏
 * 今日推荐模块的适配器
 */

public class TodayRecommendAdapter extends BaseAdapter {
    private Context context;
    // 今日推荐集合
//    private List<BriefBean> todayRecommendBeanList;
    //所有数据集合
    private FeaturedBean featuredBean;

    public TodayRecommendAdapter(Context context) {
        super();
        this.context = context;
    }

    public TodayRecommendAdapter setFeaturedBean(FeaturedBean featuredBean) {
        this.featuredBean = featuredBean;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public int getCount() {
        return featuredBean != null ? 6 : 0;
    }

    @Override
    public Object getItem(int position) {
        return featuredBean != null ? featuredBean.getData().getTodayRecommend().get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = null;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fragment_choice_today_recommend, parent, false);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        myViewHolder.fragmentChoiceTodayRecommendTimeTv.setText(featuredBean.getData().getTodayRecommend().get(position).getDuration());
        myViewHolder.fragmentChoiceTodayRecommendTitleTv.setText(featuredBean.getData().getTodayRecommend().get(position).getTitle());
        ImageManagersFactory.getImageManager(ImageManagersFactory.GLIDE).loadImageView(context, featuredBean.getData().getTodayRecommend().get(position).getCover(), myViewHolder.fragmentChoiceTodayRecommendPictureIv);
        return convertView;
    }

    class MyViewHolder {
        ImageView fragmentChoiceTodayRecommendPictureIv;
        TextView fragmentChoiceTodayRecommendTimeTv;
        TextView fragmentChoiceTodayRecommendTitleTv;

        public MyViewHolder(View view) {
            super();
            fragmentChoiceTodayRecommendPictureIv = (ImageView) view.findViewById(R.id.fragment_choiceTodayRecommendPictureIv);
            fragmentChoiceTodayRecommendTimeTv = (TextView) view.findViewById(R.id.fragment_choiceTodayRecommendTimeTv);
            fragmentChoiceTodayRecommendTitleTv = (TextView) view.findViewById(R.id.fragment_choiceTodayRecommendTitleTv);
        }
    }
}
