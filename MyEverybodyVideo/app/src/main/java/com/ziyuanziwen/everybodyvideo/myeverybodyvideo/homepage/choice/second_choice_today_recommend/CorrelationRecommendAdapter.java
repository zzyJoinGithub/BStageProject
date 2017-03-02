package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice.second_choice_today_recommend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net.ImageManagersFactory;

/**
 * Created by dllo on 17/3/1.
 */

public class CorrelationRecommendAdapter extends BaseAdapter {

    private Context context;
    private ContentEntity contentEntity;

    public CorrelationRecommendAdapter(Context context) {
        this.context = context;
    }

    public void setContentEntity(ContentEntity contentEntity) {
        this.contentEntity = contentEntity;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return contentEntity != null ? 6 : 0;
    }

    @Override
    public Object getItem(int i) {
        return contentEntity != null ? contentEntity.getData().getRecommendVideoList().get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (null == view){
            view = LayoutInflater.from(context).inflate(R.layout.item_second_fragment_choice_today_recommend_correlation_recommend_content, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        ImageManagersFactory.getImageManager(ImageManagersFactory.GLIDE).loadImageView(
                context, contentEntity.getData().getRecommendVideoList().get(i).
                        getCover(), viewHolder.fragmentChoiceSecondTodayRecommendCorrelationRecommendVideoIv);
        viewHolder.fragmentChoiceSecondTodayRecommendCorrelationRecommendTimeTv.setText(contentEntity.getData().getRecommendVideoList().get(i).getDuration());
        viewHolder.fragmentChoiceSecondTodayRecommendCorrelationRecommendVideoTitleTv.setText(contentEntity.getData().getRecommendVideoList().get(i).getTitle());
        viewHolder.fragmentChoiceSecondTodayRecommendCorrelationRecommendVideoNumberOfPeopleTv.setText(contentEntity.getData().getRecommendVideoList().get(i).getViewCount() + "次播放");

        return view;
    }

    class ViewHolder {
        ImageView fragmentChoiceSecondTodayRecommendCorrelationRecommendVideoIv;
        TextView fragmentChoiceSecondTodayRecommendCorrelationRecommendTimeTv;
        TextView fragmentChoiceSecondTodayRecommendCorrelationRecommendVideoTitleTv;
        TextView fragmentChoiceSecondTodayRecommendCorrelationRecommendVideoNumberOfPeopleTv;

        public ViewHolder(View view) {

            fragmentChoiceSecondTodayRecommendCorrelationRecommendVideoIv = (ImageView) view.findViewById(R.id.fragment_choiceSecondTodayRecommendCorrelationRecommendVideoIv);
            fragmentChoiceSecondTodayRecommendCorrelationRecommendTimeTv = (TextView) view.findViewById(R.id.fragment_choiceSecondTodayRecommendCorrelationRecommendVideoTimesTv);
            fragmentChoiceSecondTodayRecommendCorrelationRecommendVideoTitleTv = (TextView) view.findViewById(R.id.fragment_choiceSecondTodayRecommendCorrelationRecommendVideoTitleTv);
            fragmentChoiceSecondTodayRecommendCorrelationRecommendVideoNumberOfPeopleTv = (TextView) view.findViewById(R.id.fragment_choiceSecondTodayRecommendCorrelationRecommendVideoNumberOfPeopleTv);
        }



    }
}
