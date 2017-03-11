package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.db;

/**
 * Created by dllo on 17/3/8.
 * <p>
 * 数据库常用类
 */

public class SQValues {

    //    数据库名
    public static final String DB_NAME = "subscribe.db";

    //    表名
    public static final String TABLE_NAME = "subscribe";

    //    列名(图片 与 订阅名, 登录状态, 用户名)
    public static final String TITLE_COLUMN = "subscribeName";
    public static final String PICTURE_COLUMN = "subscribePicture";
    public static final String LOGIN_STATE_COLUMN = "subscribeLoginState";
    public static final String USER_NAME_COLUMN = "subscribeUserName";

    //    数据库版本
    public static final int DB_VERSION = 1;
}
