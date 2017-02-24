package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Zzy on 2017/2/23-11:49.
 * ✎﹏﹏﹏.₯㎕*﹏﹏﹏
 * 　　忍把浮名，
 * 　　　　换了浅斟低唱。
 * 　　　　 ﹏﹏﹏♥♥赵子源✍♥♥﹏﹏
 * toast工具类
 */

public final class ToastTool {
    //私有化构造方法
    private ToastTool(){}

    private static final boolean IS_DEBUG_TOAST = true;//可以在上市的时候去掉所有测试toast

    /**
     *
     * @param context       需要显示页面的 context对象
     * @param info              要显示的内容
     */
    public static void showDebugToast(Context context, String info){
        if (IS_DEBUG_TOAST) {
            Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
        }
    }

}
