package com.example.dllo.colorfulchoice.nettool;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.dllo.colorfulchoice.base.MyApp;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Coder: JiaHaiRan
 * created on 16/9/13 09:39
 * 网络请求的工具类 在进行网络请求时 都用这个工具类
 */

public class NetTool {

    private Gson gson;
    //网络数据
    private Handler handler;

    public NetTool() {
        gson = new Gson();
        handler = new Handler(Looper.getMainLooper());
    }

    public <T> void getNetData(String url, final Class<T> tClass, final NetListener<T> netListener) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        OKHttp.getInstance().newCall(request).enqueue(new Callback() {


            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handler.post(new HandlerRunnable<T>(gson.fromJson(response.body().string(), tClass), netListener));
        }
        });


    }

    class HandlerRunnable<T> implements Runnable {
        T t;
        NetListener<T> netListener;

        public HandlerRunnable(T t, NetListener<T> netListener) {
            this.t = t;
            this.netListener = netListener;
        }

        @Override
        public void run() {
            netListener.onSuccess(t);

        }
    }

    public interface NetListener<T> {

        void onSuccess(T t); //请求成功

        void onError(String errorMsg); //请求失败
    }

    //post请求
    public <T> void post(String url, String cookie, final Class<T> tClass, final NetListener<T> getVideo) throws IOException {
        final Request request = new Request.Builder().url(url)
                .addHeader("Cookie", cookie)
                .build();
        OkHttpClient client = OKHttp.getInstance();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (e != null) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {

                    String gsonString = response.body().string();

                    handler.post(new HandlerRunnable<>(gson.fromJson(gsonString, tClass), getVideo));
                } else {
                    Log.d("GetVideoByOkHttpPost", "请检测你的网络是否连接");
                }
            }
        });
    }

    public void getImage(String source, GetBitMap getBitMap) throws ExecutionException, InterruptedException {
        Log.d("NetTool", source);
        Log.d("NetTool", "开始获取数据");
        //// TODO: 16/9/18  一个asyncTask 获取图片
        MyAsyncTask myAsyncTask = new MyAsyncTask(getBitMap);
        myAsyncTask.execute(source);

    }


    public interface GetBitMap {
        void getBitMap(Bitmap bitmap);
    }


    private class MyAsyncTask extends AsyncTask<String, Integer, Bitmap> {
        GetBitMap getBitMap;

        public MyAsyncTask(GetBitMap getBitMap) {
            this.getBitMap = getBitMap;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String source = params[0];
            int wStart = source.indexOf("_");
            int wEnd = source.indexOf("x");
            int w = Integer.parseInt(source.substring(wStart + 1, wEnd));

            int hEnd = source.lastIndexOf(".");

            int h = Integer.parseInt(source.substring(wEnd + 1, hEnd));

            Bitmap bitmap = null;
            try {
                bitmap = Glide.with(MyApp.getContext()).load(source).asBitmap().centerCrop().into(w, h).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            return bitmap;
        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            getBitMap.getBitMap(bitmap);
        }
    }
}