package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dllo on 17/3/8.
 * 数据库的helper类
 */

public class SQHelper extends SQLiteOpenHelper {

//    建表(定义成静态全局的)
    public static final String DB_NAME = "create table " + SQValues.TABLE_NAME +
            "(id Integer primary Key autoincrement," + SQValues.PICTURE_COLUMN + " text, "
            + SQValues.TITLE_COLUMN + " text)";

    public SQHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DB_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
