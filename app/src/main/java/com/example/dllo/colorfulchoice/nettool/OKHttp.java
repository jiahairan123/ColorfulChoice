package com.example.dllo.colorfulchoice.nettool;

import okhttp3.OkHttpClient;

/**
 * Coder: JiaHaiRan
 * created on 16/9/13 10:20
 */


public class OKHttp {

    private static OkHttpClient client;

    public static OkHttpClient getInstance(){
        if (client == null){
            synchronized (OKHttp.class){
                if (client == null){
                    client = new OkHttpClient();
                }
            }
        }
        return client;


    }


}
