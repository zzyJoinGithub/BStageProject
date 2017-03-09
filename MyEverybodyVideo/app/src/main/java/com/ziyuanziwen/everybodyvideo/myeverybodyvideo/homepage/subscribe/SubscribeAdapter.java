package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.subscribe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.db.SQTool;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.login.LoginActivity;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.mine.MineEntity;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net.ImageManagersFactory;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.util.LogTool;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.util.ToastTool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 17/2/24.
 */

/**
 * 订阅的适配器
 * 作者:赵子文
 */

public class SubscribeAdapter extends BaseAdapter {

    //    定义实体类集合,承接对象
    private List<SubscribeEntity.DataBean.UperListBean> subscribeEntityList;
    private Context context;
    private boolean isLogin = false;
    private Map<Integer, Boolean> isClicked;


    //     构造方法
    public SubscribeAdapter(Context context) {
        this.context = context;
    }

    //    set方法将集合传入适配器中
    public void setSubscribeEntityList(List<SubscribeEntity.DataBean.UperListBean> subscribeEntityList) {
        this.subscribeEntityList = subscribeEntityList;
        isClicked = new HashMap<>();
        for (int i = 0; i < subscribeEntityList.size(); i++) {
            isClicked.put(i, false);
        }
        notifyDataSetChanged();
    }


    //    获取集合中的数量
    @Override
    public int getCount() {
        return subscribeEntityList != null && subscribeEntityList.size() > 0 ? subscribeEntityList.size() : 0;
    }

    //    获取每一行item
    @Override
    public Object getItem(int i) {
        return subscribeEntityList != null && subscribeEntityList.size() > 0 ? subscribeEntityList.get(i) : null;
    }

    //    获取item的id
    @Override
    public long getItemId(int i) {
        return i;
    }

    //    获取视图
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
//        复用机制为空
        ViewHolder viewHolder = null;
        if (null == view) {
//            注入视图
            view = LayoutInflater.from(context).inflate(R.layout.item_fragment_subscribe, viewGroup, false);
            viewHolder = new ViewHolder(view);
//            将视图设置标签(当事如没有构建出来时)
            view.setTag(viewHolder);
        } else {
//            再次获取标签(从viewholder复用机制中获取)
            viewHolder = (ViewHolder) view.getTag();
        }
//      给组件设置内容
//        Log.d("SubscribeAdapter", "subscribeEntityList.size():" + subscribeEntityList.size());
        viewHolder.titleTv.setText(subscribeEntityList.get(i).getName());
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.subscribeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MineEntity entity = new MineEntity();
                if (isLogin) {
                    if (isClicked.get(i)) {
                        isClicked.put(i, false);
                        finalViewHolder.subscribeIv.setImageResource(R.mipmap.ic_home_take_dingyue);
//                        entity.setTitle(subscribeEntityList.get(i).getName());
                        SQTool.getInstance().deleteData(subscribeEntityList.get(i).getName());

                        notifyDataSetChanged();
                        LogTool.logI("SubscribeAdapter", subscribeEntityList.get(i).getName());

                    } else {
                        isClicked.put(i, true);
                        finalViewHolder.subscribeIv.setImageResource(R.mipmap.ic_home_take_yidingyue);
                        entity.setTitle(subscribeEntityList.get(i).getName());
                        entity.setImage(subscribeEntityList.get(i).getHeadImg());
                        entity.setClicked(true);
                        SQTool.getInstance().addData(entity);
                        notifyDataSetChanged();
                        LogTool.logI("SubscribeAdapter", entity.getTitle());
                    }

                } else {
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                    isLogin = true;
                    ToastTool.showDebugToast(context, "请先登录");
                }
            }
        });
//        先获取内容,进行判断
        String fansCount = subscribeEntityList.get(i).getFansCount() + "";
        if (fansCount.length() < 5) {
            viewHolder.numberOfPeopleTv.setText(fansCount + "人订阅");

        } else {
//            获取新的内容(将原内容字符串的长度截取,去0到字符串长度 - 3的内容)
            String newFansCount = fansCount.substring(0, fansCount.length() - 3);
//            分别截取剩余字符串长度的内容 + "."
            viewHolder.numberOfPeopleTv.setText(newFansCount.substring(0, newFansCount.length() - 1)
                    + "." + newFansCount.substring(newFansCount.length() - 1, newFansCount.length()) + "万人订阅");

        }
//        通过试图工厂获取解析图片的对象进行解析
        ImageManagersFactory.getImageManager(ImageManagersFactory.GLIDE).loadImageView(
                context, subscribeEntityList.get(i).getHeadImg(), viewHolder.circleImageView);

        return view;
    }

    //定义内部类, 定义组件,绑定组件
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
