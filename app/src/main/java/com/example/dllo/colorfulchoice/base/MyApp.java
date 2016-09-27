package com.example.dllo.colorfulchoice.base;

import android.app.Application;
import android.content.Context;

import com.example.dllo.colorfulchoice.database.DaoMaster;
import com.example.dllo.colorfulchoice.database.DaoSession;

/**
 * Coder: JiaHaiRan
 * created on 16/9/12 14:35
 */

//需要在清单文件中注册
public class MyApp extends Application {
    private static Context context;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private static MyApp mMyApp;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        if (mMyApp == null){
            mMyApp = this;
        }
    }
    public static Context getContext(){
        return context;
    }

    /**
     * 取得DaoMaster
     * @return         DaoMaster
     */
    public static DaoMaster getDaoMaster(){
        if (daoMaster == null){
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, "MyDataBase.db", null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     * @return          DaoSession
     */
    public static DaoSession getDaoSession(){
        if (daoSession == null){
            if (daoMaster == null){
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
