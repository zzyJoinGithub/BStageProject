package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Zzy on 2017/2/23-20:36.
 * ✎﹏﹏﹏.₯㎕*﹏﹏﹏
 * 　　忍把浮名，
 * 　　　　换了浅斟低唱。
 * 　　　　 ﹏﹏﹏♥♥赵子源✍♥♥﹏﹏
 */

public class GlideManager extends ImageManagers {
    private static GlideManager instance;
    private GlideManager() {}

    public static GlideManager getInstance() {
        if (null == instance) {
            synchronized (GlideManager.class){
                if (null == instance) {
                    instance = new GlideManager();
                }
            }
        }
        return instance;
    }

    @Override
    public void loadImageView(Context context, String url, ImageView imageView) {
//        Glide.with(context).load(url)                           //图片网址
//                .placeholder(R.mipmap.ic_launcher)        //加载过程中显示的图片
//                .error(R.mipmap.ic_launcher)                  //加载失败时候显示的图片
//                .into(imageView);                                   //要显示到的组件
        Glide.with(context).load(url).into(imageView);
    }
}
