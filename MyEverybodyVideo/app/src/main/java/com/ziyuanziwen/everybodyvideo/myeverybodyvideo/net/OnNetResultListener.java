package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.net;

/**
 * Created by dllo on 2017/2/24-上午10:07.
 * ✎﹏﹏﹏.₯㎕*﹏﹏﹏
 * 　　忍把浮名，
 * 　　　　换了浅斟低唱。
 * 　　　　 ﹏﹏﹏♥♥赵子源✍♥♥﹏﹏
 * 网络请求数据接口
 */

public interface OnNetResultListener {
    //成功
    void onSuccessful(String resultStr);
    //失败
    void onFailure(String errMsg);
}
