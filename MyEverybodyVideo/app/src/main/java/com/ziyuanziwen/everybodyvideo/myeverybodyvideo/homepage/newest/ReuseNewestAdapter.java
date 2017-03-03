package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.newest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.login.LoginActivity;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net.ImageManagersFactory;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.util.LogTool;

import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
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
    private Boolean isLogin = false;
    private Boolean isLike = false;


    //    构造方法(初始化适配器)
    public ReuseNewestAdapter(Context context) {
        this.context = context;
        ShareSDK.initSDK(context);//这句话一定要在调用方法之前写出来

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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
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
                showShare(resultsBeanList.get(i).getTitle(), "", resultsBeanList.get(i).getCover(), "");
            }
        });

/**
 *   内部类的东西不能定义成全局变量, 如果用在定义一个同类型的变量,将内部类的东西赋值在使用;
 */
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.likeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogTool.logI("Adapter", "点击");
               if (isLogin == true){
                   if (isLike == true){
                       finalViewHolder.likeIv.setImageResource(R.mipmap.ic_like_h);
                       LogTool.logI("Adapter", "红色");
                       isLike = false;
                   }else{
                       finalViewHolder.likeIv.setImageResource(R.mipmap.ic_like_);
                       LogTool.logI("Adapter", "无色");
                       isLike = true;
                   }
               }else {
                   LogTool.logI("Adapter", "跳转,boolean类型值都变为true");
                   Intent intent = new Intent(context, LoginActivity.class);
                   context.startActivity(intent);
                   isLike = true;
                   isLogin = true;
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

    private void showShare(String title, String content, String imageUrl, String shareUrl) {
//        ShareSDK.initSDK(context);//这句话一定要在调用方法之前写出来

        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle(title);
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(shareUrl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(content);
//        if (musicTransferBean.getTransferBeens().get(musicPosition).getMusicImage().equals("没有专辑图片")) {
//            //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
//            oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
//        } else {
//            oks.setImageUrl(musicTransferBean.getTransferBeens().get(musicPosition).getMusicLoad());
//        }
        oks.setImageUrl(imageUrl);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(shareUrl);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(shareUrl);
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(shareUrl);

// 启动分享GUI
        oks.show(context);
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
