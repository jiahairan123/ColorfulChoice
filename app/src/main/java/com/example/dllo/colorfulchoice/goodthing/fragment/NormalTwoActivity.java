package com.example.dllo.colorfulchoice.goodthing.fragment;

import android.content.Intent;
import android.os.Message;
import android.support.design.widget.TabLayout;
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

    private ViewPager viewPager;
    private TextView digest;
    private TextView name;
    private TextView desc;
    private ImageView images;

    private ImageView avatar;
    private TextView nameTwo;
    private TextView lable;
    private TextView description;

    private LinearLayout dotcontant;
    ArrayList<View> dots;
    int currentIndex = 0;
    int olderIndex = 0;

    android.os.Handler handler = new android.os.Handler() {
        public void handleMessage(Message msg) {
            viewPager.setCurrentItem(currentIndex);  // 设置此次要显示的pager
            // 切换选中的原点
            dots.get(olderIndex).setBackgroundResource(R.drawable.dot_nomal); // 设置上次选中的原点为不选中
            WheelAdapter adapter = new WheelAdapter(getApplicationContext());
            count = adapter.getCount();
            // 设置当前选中的原点为选中
            dots.get(currentIndex % count).setBackgroundResource(R.drawable.dot_focus);
            olderIndex = currentIndex % count;
        }
    };
    private int count;


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

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int normalId = intent.getIntExtra("id", 1268);
        Log.d("NormalTwoActivity", "normalId:" + normalId);
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

                // 获得原点
                getDotList();
                // 设置第一个原点为选中状态
//                dots.get(0).setBackgroundResource(R.drawable.dot_focus);

                // 轮播图
                WheelAdapter adapter = new WheelAdapter(getApplicationContext());
                adapter.setBean(twoNormalBean);
                viewPager.setAdapter(adapter);

//                int currentItem = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % count;
//                viewPager.setCurrentItem(currentItem);

                // 通过定时器, 制作自动滑动的效果
//                Timer timer = new Timer();
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        currentIndex = viewPager.getCurrentItem() + 1; // 下一页
//                        handler.sendEmptyMessage(0x123);  // 在此线程中, 不能操作ui主线程
//                    }
//                }, 3000, 2000);
            }

            @Override
            public void onError(String errorMsg) {

            }
        });

    }

    private void getDotList() {
        dots = new ArrayList<View>();
        for (int i = 0; i < count; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.dot_layout, null);
            View dot = view.findViewById(R.id.dotView);  // 得到布局中的dot组件
            // 收集dot
            dots.add(dot);
            // 把布局添加到线性布局
            dotcontant.addView(view);
        }
    }


    @Override
    public void onClick(View v) {

    }
}
