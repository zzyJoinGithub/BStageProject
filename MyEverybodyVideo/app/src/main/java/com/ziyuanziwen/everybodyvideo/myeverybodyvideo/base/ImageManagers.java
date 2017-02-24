package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Zzy on 2017/2/23-19:18.
 * ✎﹏﹏﹏.₯㎕*﹏﹏﹏
 * 　　忍把浮名，
 * 　　　　换了浅斟低唱。
 * 　　　　 ﹏﹏﹏♥♥赵子源✍♥♥﹏﹏
 * 抽象方法 规定所有的图片管理对象都有从网络上加载图片并设置到ImageView对象的方法
 */

public abstract class ImageManagers {

    public abstract void loadImageView(Context context, String url, ImageView imageView);

    /**
     * 重载方法
     * @param context                           context对象
     * @param url                                   图片网址
     * @param placeHolderImage          加载时图片
     * @param errorImage                    加载失败显示图片
     * @param imageView                     要显示到的Image组件
     */
    protected void loadImageView(Context context,String url,int placeHolderImage,int errorImage,ImageView imageView){

    }
}
