package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base.ImageManagers;

/**
 * Created by Zzy on 2017/2/23-20:50.
 * ✎﹏﹏﹏.₯㎕*﹏﹏﹏
 * 　　忍把浮名，
 * 　　　　换了浅斟低唱。
 * 　　　　 ﹏﹏﹏♥♥赵子源✍♥♥﹏﹏
 */

public class ImageManagersFactory {
    public static final String GLIDE = "glide";
    public static final String PICASSO = "picasso";
    public static ImageManagers getImageManager(String type){
        if (type.equals(GLIDE)) {
            return GlideManager.getInstance();
        } else if (type.equals(PICASSO)){
            return PicassoManager.getInstance();
        }
        return null;
    }
}
