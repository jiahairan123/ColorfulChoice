package com.example.dllo.colorfulchoice.goodthing.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseAty;
import com.example.dllo.colorfulchoice.base.MyApp;
import com.example.dllo.colorfulchoice.goodthing.bean.TwoNormalBean;
import com.example.dllo.colorfulchoice.goodthing.adapter.WheelAdapter;
import com.example.dllo.colorfulchoice.nettool.NetTool;
import com.example.dllo.colorfulchoice.nettool.URLValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by mayinling on 16/9/21.
 */
public class NormalTwoActivity extends BaseAty {

    private static final int TIME = 5000;
    private Handler handler;
    private Runnable rotateRunnable;
    private ViewPager viewPager;
    private TextView digest;
    private TextView name;
    private TextView desc;
    private ImageView images;

    private ImageView avatar;
    private TextView nameTwo;
    private TextView lable;
    private TextView description;

    private boolean isRotate = false;

    private LinearLayout dotcontant;
    ArrayList<View> dots;
    private int size;

    @Override
    protected int setLayout() {
        return R.layout.activity_gooddaily;
    }

    @Override
    protected void initView() {

        viewPager = bindView(R.id.activity_goodthing_daily_cover_images);
        digest = bindView(R.id.activity_goodthing_daily_digest);
        name = bindView(R.id.activity_goodthing_daily_name);
        desc = bindView(R.id.activity_goodthing_daily_desc);
        images = bindView(R.id.activity_goodthing_daily_images);
        avatar = bindView(R.id.activity_goodthing_avatar_url);
        nameTwo = bindView(R.id.activity_goodthing_name_two);
        lable = bindView(R.id.activity_goodthing_lable);
        description = bindView(R.id.activity_goodthing_description);

        // 圆点
        dotcontant = bindView(R.id.dotcantaint);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int normalId = intent.getIntExtra("id", 1268);
        String urlone = URLValue.NROMAL_ONE;
        String urltwo = URLValue.NORMAL_TWO;
        String fina = urlone + normalId + urltwo;
        netTool.getNetData(fina, TwoNormalBean.class, new NetTool.NetListener<TwoNormalBean>() {
            @Override
            public void onSuccess(TwoNormalBean twoNormalBean) {
                digest.setText(twoNormalBean.getData().getDigest());
                name.setText(twoNormalBean.getData().getName());
                desc.setText(twoNormalBean.getData().getDesc());
                nameTwo.setText(twoNormalBean.getData().getDesigner().getName());
                lable.setText(twoNormalBean.getData().getDesigner().getLabel());
                description.setText(twoNormalBean.getData().getDesigner().getDescription());

                // 图片
                Glide.with(getApplicationContext()).load(twoNormalBean.getData().getDesigner().getAvatar_url()).into(avatar);
                List<String> image = twoNormalBean.getData().getImages();
                Glide.with(getApplicationContext()).load(image.get(0)).into(images);

                // 获得轮播图图片的个数
                size = twoNormalBean.getData().getCover_images().size();

                // 轮播图
                WheelAdapter adapter = new WheelAdapter(getApplicationContext());
                adapter.setBean(twoNormalBean);
                viewPager.setAdapter(adapter);
                viewPager.setCurrentItem(size * 100);

                handler = new Handler();
                // 开始轮播
                startRotate();
                // 添加轮播小点
                addPoints();



                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                       if (isRotate){
                           for (int i = 0; i < size; i++) {
                               ImageView imageView = (ImageView) dotcontant.getChildAt(i);
                               imageView.setBackgroundResource(R.drawable.dot_nomal);
                           }
                           ImageView iv = (ImageView) dotcontant.getChildAt(position % size);
                           iv.setBackgroundResource(R.drawable.dot_focus);
                       }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
            }

            @Override
            public void onError(String errorMsg) {

            }
        });

    }

    private void addPoints() {
        for (int i = 0; i < size; i++) {
            ImageView pointIv = new ImageView(this);
            pointIv.setPadding(5,5,5,5);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            pointIv.setLayoutParams(params);

            if (i == 0){
                pointIv.setBackgroundResource(R.drawable.dot_focus);
            } else {
                pointIv.setBackgroundResource(R.drawable.dot_nomal);
            }
            dotcontant.addView(pointIv);
        }
    }

    // 开始轮播
    private void startRotate() {
        rotateRunnable = new Runnable() {
            @Override
            public void run() {
                int nowIndex = viewPager.getCurrentItem();
                viewPager.setCurrentItem(++nowIndex);
                if (isRotate){
                    handler.postDelayed(rotateRunnable, TIME);
                }
            }
        };
        handler.postDelayed(rotateRunnable, TIME);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRotate = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isRotate = false;
    }

    @Override
    public void onClick(View v) {

    }
}
