package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.subscribe;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net.ImageManagersFactory;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 17/2/24.
 */

public class SubscribeAdapter extends BaseAdapter {

    private List<SubscribeEntity.DataBean.UperListBean> subscribeEntityList;
    private Context context;

    public SubscribeAdapter(Context context) {
        this.context = context;
    }

    public void setSubscribeEntityList(List<SubscribeEntity.DataBean.UperListBean> subscribeEntityList) {
        this.subscribeEntityList = subscribeEntityList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return subscribeEntityList != null && subscribeEntityList.size() > 0 ? subscribeEntityList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return subscribeEntityList != null && subscribeEntityList.size() > 0 ? subscribeEntityList.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (null == view) {
            view = LayoutInflater.from(context).inflate(R.layout.item_fragment_subscribe, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Log.d("SubscribeAdapter", "subscribeEntityList.size():" + subscribeEntityList.size());
        viewHolder.titleTv.setText(subscribeEntityList.get(i).getName());
        viewHolder.subscribeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        String fansCount = subscribeEntityList.get(i).getFansCount() + "";
        if (fansCount.length() < 5) {
            viewHolder.numberOfPeopleTv.setText(fansCount + "人订阅");

        } else {
            String newFansCount = fansCount.substring(0, fansCount.length() - 3);
            viewHolder.numberOfPeopleTv.setText(newFansCount.substring(0, newFansCount.length() - 1)
                    + "." + newFansCount.substring(newFansCount.length() - 1, newFansCount.length()) + "万人订阅");

        }
        ImageManagersFactory.getImageManager(ImageManagersFactory.GLIDE).loadImageView(
                context, subscribeEntityList.get(i).getHeadImg(), viewHolder.circleImageView);

        return view;
    }


    class ViewHolder {

        CircleImageView circleImageView;
        TextView titleTv;
        TextView numberOfPeopleTv;
        ImageView subscribeIv;

        public ViewHolder(View view) {
            circleImageView = (CircleImageView) view.findViewById(R.id.fragment_subscribeItemCircleIv);
            titleTv = (TextView) view.findViewById(R.id.fragment_subscribeItemTitle);
            numberOfPeopleTv = (TextView) view.findViewById(R.id.fragment_subscribeItemNumOfPeople);
            subscribeIv = (ImageView) view.findViewById(R.id.fragment_subscribeItem);
        }
    }
}
