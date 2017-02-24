package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.util;

import android.util.Log;

/**
 * Created by Zzy on 2017/2/23-11:07.
 * ✎﹏﹏﹏.₯㎕*﹏﹏﹏
 * 　　忍把浮名，
 * 　　　　换了浅斟低唱。
 * 　　　　 ﹏﹏﹏♥♥赵子源✍♥♥﹏﹏
 * log工具类
 */

public final class LogTool {
    /*final修饰的类不能被继承
        私有化构造方法:不能被外部new对象
        对这个类采取这种方法的目的:
        这个类就是一个工具类,不允许继承修改和创建垃圾对象
     */
        private LogTool(){}
    private static final boolean IS_DEBUG_LOG = true;//可以在上市的时候去掉所有log

    public static void logI(String logKey,String info){
        if (IS_DEBUG_LOG) {
            Log.i(logKey, info);
        }
    };
}
