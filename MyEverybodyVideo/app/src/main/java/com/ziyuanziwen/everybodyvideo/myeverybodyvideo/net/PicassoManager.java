package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.R;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base.ImageManagers;

/**
 * Created by Zzy on 2017/2/23-19:26.
 * ✎﹏﹏﹏.₯㎕*﹏﹏﹏
 * 　　忍把浮名，
 * 　　　　换了浅斟低唱。
 * 　　　　 ﹏﹏﹏♥♥赵子源✍♥♥﹏﹏
 */

public class PicassoManager extends ImageManagers {
    private static PicassoManager instance;
    private PicassoManager() {}

    public static PicassoManager getInstance() {
        if (null == instance) {
            synchronized (PicassoManager.class){
                if (null == instance) {
                    instance = new PicassoManager();
                }
            }
        }
        return instance;
    }

    @Override
    public void loadImageView(Context context, String url, ImageView imageView) {
//        Picasso.with(context).load(url)
//                .resize(100,100)                                        //重设图片大小
//                .rotate(360,0.5f,0.5f)                              //获取的图片旋转
//                .placeholder(R.mipmap.ic_launcher)          //占位（我感觉就是加载过程中图片）
//                .into(imageView);
        Picasso.with(context).load(url).into(imageView);
    }
}
