package com.example.dllo.colorfulchoice.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.dllo.colorfulchoice.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by ${马庭凯} on 16/9/26.
 */

public class FireWorks extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder sfh;
    private Paint paint;
    private Thread th;
    private boolean flag;
    private Canvas canvas;
    private int screenW;
    private int screenH;
    private HashMap<String,Integer> map;
    private List<HashMap<String,Integer>> mapList;
    private Bitmap bitmap;
    private float bmwid;
    private float bmhei;

    public FireWorks(Context context) {
        super(context);
        sfh = this.getHolder();
        sfh.addCallback(this);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(30);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        setFocusable(true);
        mapList = new ArrayList<>();
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.welcome);
        bmwid = bitmap.getWidth();
        bmhei = bitmap.getHeight();
    }

    public FireWorks(Context context, AttributeSet attrs) {
        super(context, attrs);
        sfh = this.getHolder();
        sfh.addCallback(this);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(30);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        setFocusable(true);
        mapList = new ArrayList<>();
        bitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.welcome);
        bmwid = bitmap.getWidth();
        bmhei = bitmap.getHeight();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        screenW = this.getWidth();
        screenH = this.getHeight();
        flag = true;
        th = new Thread(this);
        th.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        flag = false;
    }

    private void myDraw(int getwid,int gethei) {
        if(getwid != 0 && gethei != 0){
            for (int i = 0; i < mapList.size(); i++) {
                if(mapList.get(i).get("cylinder") == 8){
                    mapList.remove(i);
                }
                if(mapList.size() != 0) {
                    HashMap<String, Integer> linshiMap = mapList.get(i);
                    linshiMap.put("cylinder", linshiMap.get("cylinder") + 1);
                    mapList.set(i, linshiMap);
                }
            }
            map = new HashMap<>();
            map.put("cylinder",0);
            map.put("getwid",getwid);
            map.put("gethei",gethei);
            mapList.add(map);
        }else {
            for (int i = 0; i < mapList.size(); i++) {
                if(mapList.get(i).get("cylinder") == 8){
                    mapList.remove(i);
                }
                if(mapList.size() != 0) {
                    HashMap<String, Integer> linshiMap = mapList.get(i);
                    linshiMap.put("cylinder", linshiMap.get("cylinder") + 1);
                    mapList.set(i, linshiMap);
                }
            }
        }

        try {
            canvas = sfh.lockCanvas();
            if (canvas != null) {
                paint.setColor(Color.WHITE);
                canvas.scale((screenW / bmwid),(screenH / bmhei));
                canvas.drawBitmap(bitmap,0,0,paint);
                for (int i = 0; i < mapList.size(); i++) {
                    fireWorks(mapList.get(i));
                }
            }
        } catch (Exception e) {

        } finally {
            if (canvas != null) {
                sfh.unlockCanvasAndPost(canvas);
            }
        }
    }

    private void fireWorks(HashMap<String,Integer> integerMap){
        for (int j = 0; j < integerMap.get("cylinder"); j++) {
            for (int i = 0; i < (j + 1) * 4; i++) {
                canvas.save();
                canvas.rotate(360 / ((j + 1) * 4) * i, integerMap.get("getwid"), integerMap.get("gethei"));
                getColor(new Random().nextInt(5));
                canvas.drawCircle(integerMap.get("getwid") + (j + 1) * 25 + new Random().nextInt(10), integerMap.get("gethei"), 8, paint);
                canvas.restore();
            }
        }
    }

    private void logic() {

    }

    @Override
    public void run() {
        int getwid;
        int gethei;
        while (flag) {
            int getNum = new Random().nextInt(5);
            if(getNum == 3){
                getwid = new Random().nextInt(screenW) + 1;
                gethei = new Random().nextInt(screenH) + 1;
            }else {
                getwid = 0;
                gethei = 0;
            }
            long start = System.currentTimeMillis();
            myDraw(getwid,gethei);
            logic();
            long end = System.currentTimeMillis();
            try {
                if (end - start < 50) {
                    Thread.sleep(50 - (end - start));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void getColor(int numColor) {
        Log.d("FireWorks", "随机生成的颜色是" + numColor);
        switch (numColor) {
            case 0:
                //黄色
                paint.setColor(Color.rgb(255,255,0));
                break;
            case 1:
                //红色
                paint.setColor(Color.rgb(255,0,0));
                break;
            case 2:
                //紫色
                paint.setColor(Color.rgb(255,0,255));
                break;
            case 3:
                //绿色
                paint.setColor(Color.rgb(127,255,0));
                break;
            case 4:
                //蓝色
                paint.setColor(Color.rgb(0,255,255));
                break;
        }
    }
}
