package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.newest;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.login.LoginActivity;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net.ImageManagersFactory;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 17/2/24.
 */

/**
 * 复用碎片的适配器
 * 作者:赵子文
 */

public class ReuseNewestAdapter extends BaseAdapter {

    //    定义实体类内容的集合,传入的对象
    private List<ReuseNewestEntity.DataBean.ResultsBean> resultsBeanList;
    private Context context;
    private Boolean isLogin = true;
    private Boolean isLike = false;
    private ViewHolder viewHolder;

    //    构造方法(初始化适配器)
    public ReuseNewestAdapter(Context context) {
        this.context = context;
    }

    //    set方法将获取的网络数据传入适配器中并刷新界面
    public void setResultsBeanList(List<ReuseNewestEntity.DataBean.ResultsBean> resultsBeanList) {
        this.resultsBeanList = resultsBeanList;
        notifyDataSetChanged();
    }

    //    获取集合的数量
    @Override
    public int getCount() {
        return resultsBeanList != null && resultsBeanList.size() > 0 ? resultsBeanList.size() : 0;
    }

    //    获取某一行的item
    @Override
    public Object getItem(int i) {
        return resultsBeanList != null && resultsBeanList.size() > 0 ? resultsBeanList.get(i) : null;
    }

    //    获取某一行的item的id
    @Override
    public long getItemId(int i) {
        return i;
    }

    //    获取视图
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewHolder = null;
        if (null == view) {
            view = LayoutInflater.from(context).inflate(R.layout.item_fragment_newest_reuse, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

//    设置数据给视图
        viewHolder.shareIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        viewHolder.likeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLogin) {
                    Log.d("ReuseNewestAdapter", "登录");
                    if (!isLike) {
                        viewHolder.likeIv.setImageResource(R.mipmap.ic_like_h);
                        Log.d("ReuseNewestAdapter", "喜欢");
                    } else {
                        viewHolder.likeIv.setImageResource(R.mipmap.ic_me_like);
                        Log.d("ReuseNewestAdapter", "不喜欢");
                    }

                } else {
                    Log.d("ReuseNewestAdapter", "跳转");
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                }
            }
        });
        viewHolder.authorTv.setText(resultsBeanList.get(i).getAuthor().getNickName());
        viewHolder.timeTv.setText(resultsBeanList.get(i).getDuration());
        viewHolder.titleTv.setText(resultsBeanList.get(i).getTitle());
        ImageManagersFactory.getImageManager(ImageManagersFactory.GLIDE).loadImageView(
                context, resultsBeanList.get(i).getCover(), viewHolder.mainIv);
        ImageManagersFactory.getImageManager(ImageManagersFactory.GLIDE).loadImageView(
                context, resultsBeanList.get(i).getAuthor().getHeadImgUrl(), viewHolder.authorCIv);
        return view;
    }


    //    内部类(复用机制),绑定各个组件
    private class ViewHolder {
        ImageView mainIv;
        CircleImageView authorCIv;
        TextView titleTv;
        TextView timeTv;
        TextView authorTv;
        ImageView shareIv;
        ImageView likeIv;

        public ViewHolder(View view) {
            mainIv = (ImageView) view.findViewById(R.id.fragment_newestItemPictureIv);
            authorCIv = (CircleImageView) view.findViewById(R.id.fragment_newestItemCircleIv);
            titleTv = (TextView) view.findViewById(R.id.fragment_newestItemTitleTv);
            timeTv = (TextView) view.findViewById(R.id.fragment_newestItemTimeTv);
            authorTv = (TextView) view.findViewById(R.id.fragment_newestItemAuthorTv);
            shareIv = (ImageView) view.findViewById(R.id.fragment_newestItemShareIv);
            likeIv = (ImageView) view.findViewById(R.id.fragment_newestItemLikeIv);
        }
    }
}
