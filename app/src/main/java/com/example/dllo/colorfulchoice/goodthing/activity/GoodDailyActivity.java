package com.example.dllo.colorfulchoice.goodthing.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseAty;
import com.example.dllo.colorfulchoice.base.CommonAdapter;
import com.example.dllo.colorfulchoice.base.CommonViewHolder;
import com.example.dllo.colorfulchoice.goodthing.adapter.WheelAdapter;
import com.example.dllo.colorfulchoice.goodthing.bean.TwoDailyBean;
import com.example.dllo.colorfulchoice.nettool.NetTool;
import com.example.dllo.colorfulchoice.nettool.URLValue;

import java.util.List;


/**
 * Created by mayinling on 16/9/19.
 */
public class GoodDailyActivity extends BaseAty {

    private static final int TIME = 3000;

    private ViewPager coverImages;
    private TextView digest;
    private TextView name;
    private TextView desc;
    private ImageView image;

    private ImageView avatar;
    private TextView nameTwo;
    private TextView lable;
    private TextView description;
    private GridView gridView;
    private int aa;
    private LinearLayout pointLl;
    private Handler handler;
    private Runnable rotateRunnable;
    private boolean isRotate = false;
    private int size;
    private List<String> images;

    @Override
    protected int setLayout() {
        return R.layout.activity_gooddaily;
    }

    @Override
    protected void initView() {
        coverImages = bindView(R.id.activity_goodthing_daily_cover_images);
        digest = bindView(R.id.activity_goodthing_daily_digest);
        name = bindView(R.id.activity_goodthing_daily_name);
        desc = bindView(R.id.activity_goodthing_daily_desc);
        image = bindView(R.id.activity_goodthing_daily_images);

        avatar = bindView(R.id.activity_goodthing_avatar_url);
        nameTwo = bindView(R.id.activity_goodthing_name_two);
        lable = bindView(R.id.activity_goodthing_lable);
        description = bindView(R.id.activity_goodthing_description);

        gridView = bindView(R.id.activity_goodthing_lianjie);
        pointLl = bindView(R.id.dotcantaint);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        aa = intent.getIntExtra("dailyId", 1246);
        String upUrl = URLValue.TWO_GOODTHING_UP;
        String downUrl = URLValue.TWO_GOODTHING_DOWN;
        final String finalUrl = upUrl + aa + downUrl;
        netTool.getNetData(finalUrl, TwoDailyBean.class, new NetTool.NetListener<TwoDailyBean>() {


            @Override
            public void onSuccess(final TwoDailyBean twoDailyBean) {
                digest.setText(twoDailyBean.getData().getDigest());
                name.setText(twoDailyBean.getData().getName());
                desc.setText(twoDailyBean.getData().getDesc());

                nameTwo.setText(twoDailyBean.getData().getDesigner().getName());
                lable.setText(twoDailyBean.getData().getDesigner().getLabel());
                description.setText(twoDailyBean.getData().getDesigner().getDescription());

                // 获取网络图片 头像
                List<String> imgUrls = twoDailyBean.getData().getImages();
                Glide.with(getApplicationContext()).load(imgUrls.get(0)).into(image);
                Glide.with(getApplicationContext()).load(twoDailyBean.getData().getDesigner().getAvatar_url()).into(avatar);

                // 上面的轮播图

                coverImages.setAdapter(new DailyWheelAdapter(twoDailyBean, getApplicationContext()));
                // ViewPager 的页数
                size = twoDailyBean.getData().getCover_images().size();
                coverImages.setCurrentItem(size * 100);

                // 开始轮播
                handler = new Handler();
                startRotate();
                // 添加轮播小点
                addPoints();

                // 随着轮播改变小点
                coverImages.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        if (isRotate){
                            // 把所有小点设置为白色
                            for (int i = 0; i < size; i++) {
                                ImageView imageView = (ImageView) pointLl.getChildAt(i);
                                imageView.setBackgroundResource(R.drawable.dot_nomal);
                            }

                            // 设置当前位置小点为选中
                            ImageView iv = (ImageView) pointLl.getChildAt(position % size);
                            iv.setBackgroundResource(R.drawable.dot_focus);
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });


                // 下面的相关链接
                gridView.setAdapter(new CommonAdapter<TwoDailyBean.DataBean.ReferProductsBean>(twoDailyBean
                        .getData().getRefer_products(), getApplicationContext(), R.layout.item_daily_lianjie) {
                    @Override
                    public void setData(TwoDailyBean.DataBean.ReferProductsBean referProductsBean, CommonViewHolder viewHolder) {

                        String s = referProductsBean.getImages().get(0);
                        viewHolder.setImage(R.id.lianjie_iv,s);
                    }
                });
            }

            @Override
            public void onError(String errorMsg) {
                Toast.makeText(GoodDailyActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 添加轮播切换小点
    private void addPoints() {
        for (int i = 0; i < size; i++) {
            ImageView imageView = new ImageView(this);
            pointLl.setPadding(10,10,10,10);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            imageView.setLayoutParams(params);

            // 设置第0页小点为选中
            if (i == 0){
                imageView.setBackgroundResource(R.drawable.dot_focus);
            } else {
                imageView.setBackgroundResource(R.drawable.dot_nomal);
            }
            pointLl.addView(imageView);
        }
    }

    // 开始轮播
    private void startRotate() {
        rotateRunnable = new Runnable() {
            @Override
            public void run() {
                int nowIndex = coverImages.getCurrentItem();
                coverImages.setCurrentItem(++nowIndex);
                if (isRotate){
                    handler.postDelayed(rotateRunnable,TIME);
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

    private class DailyWheelAdapter extends PagerAdapter {

        private Context context;
        private TwoDailyBean twoDailyBean;

        public DailyWheelAdapter(TwoDailyBean twoDailyBean, Context context) {
            this.twoDailyBean = twoDailyBean;
            this.context = context;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(context).inflate(R.layout.wheel, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.wheel_iv);
            int size = twoDailyBean.getData().getCover_images().size();
            String s = twoDailyBean.getData().getCover_images().get(position % size);
            Glide.with(getApplicationContext()).load(s).into(imageView);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
