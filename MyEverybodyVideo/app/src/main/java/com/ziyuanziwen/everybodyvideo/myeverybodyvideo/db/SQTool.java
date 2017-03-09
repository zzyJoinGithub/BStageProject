package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.base.BaseApplication;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.mine.MineEntity;
import com.ziyuanziwen.everybodyvideo.myeverybodyvideo.util.LogTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/3/8.
 * <p>
 * 数据库具体的操作方法
 */

public class SQTool {

    private static SQTool instance;
    private SQLiteDatabase database;
    private SQHelper helper;


    private SQTool() {
//        通过SQHelper类创建数据库
        helper = new SQHelper(BaseApplication.getContext(), SQValues.DB_NAME, null, SQValues.DB_VERSION);
//        通过SQHelper类获取一个可写的数据库
        database = helper.getWritableDatabase();
    }

    //    全局只有一个SQTool类对象
    public static SQTool getInstance() {
        if (null == instance) {
//            双重校验锁
            synchronized (SQTool.class) {
                if (null == instance) {
                    instance = new SQTool();
                }
            }
        }
        return instance;
    }


//    查(数据库中是否存在此数据 : 根据名字)
//
//    public boolean hasThisData(String title){
//        boolean hasThisDatas = false;
//        Cursor cursor = database.rawQuery("select * from " + SQValues.TABLE_NAME +
//        "where" + SQValues.TITLE_COLUMN + " = ?", new String[]{title});
//        if (cursor.getCount() > 0){
//            hasThisDatas = true;
//        }
//        cursor.close();
//        return hasThisDatas;
//    }


    //    增
    public void addData(MineEntity mineEntity) {
//        if (true == hasThisData(mineEntity.getTitle())){
//            return;
//        }
        ContentValues values = new ContentValues();
        values.put(SQValues.PICTURE_COLUMN, mineEntity.getImage());
        values.put(SQValues.TITLE_COLUMN, mineEntity.getTitle());
        database.insert(SQValues.TABLE_NAME, null, values);
        LogTool.logI("SQTool", mineEntity.getImage() + "\n" + mineEntity.getTitle());
    }

    //    删
    public void deleteData(String title) {
        database.delete(SQValues.TABLE_NAME, SQValues.TITLE_COLUMN + " = ?", new String[]{title});
        LogTool.logI("SQTool", title);

    }

    //    查
    public List<MineEntity> queryAllData() {
        List<MineEntity> data = new ArrayList<>();
        Cursor cursor = database.query(SQValues.TABLE_NAME, null, null, null, null, null, null);
        if (null != cursor) {
            while (cursor.moveToNext()) {
                String titles = cursor.getString(cursor.getColumnIndex(SQValues.TITLE_COLUMN));
                String image = cursor.getString(cursor.getColumnIndex(SQValues.PICTURE_COLUMN));
                MineEntity bean = new MineEntity();
                bean.setTitle(titles);
                bean.setImage(image);
                data.add(bean);
            }
        }
        return data;
    }
}
