package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.homepage.choice;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net.ImageManagersFactory;

/**
 * Created by Zzy on 2017/2/27-21:23.
 * ✎﹏﹏﹏.₯㎕*﹏﹏﹏
 * 　　忍把浮名，
 * 　　　　换了浅斟低唱。
 * 　　　　 ﹏﹏﹏♥♥赵子源✍♥♥﹏﹏
 * banner的缓存机制
 */

public class ChoiceBannerTop extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }
}
