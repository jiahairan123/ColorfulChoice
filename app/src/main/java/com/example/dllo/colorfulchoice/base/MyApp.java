package com.example.dllo.colorfulchoice.base;

import android.app.Application;
import android.content.Context;

/**
 * Coder: JiaHaiRan
 * created on 16/9/12 14:35
 */

//需要在清单文件中注册
public class MyApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
    public static Context getContext(){
        return context;
    }
}
