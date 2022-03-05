package com.akuya.mylitepal;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import org.litepal.LitePal;

/**
 * Created by HaiyuKing
 * Used
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        /*=================litepal数据库=====================*/
        LitePal.initialize(this);
        //获取到SQLiteDatabase的实例，创建数据库表
        SQLiteDatabase db = LitePal.getDatabase();
    }
}

