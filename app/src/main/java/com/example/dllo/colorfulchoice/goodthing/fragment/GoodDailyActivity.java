package com.example.dllo.colorfulchoice.goodthing.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseAty;
import com.example.dllo.colorfulchoice.goodthing.bean.TwoDailyBean;
import com.example.dllo.colorfulchoice.nettool.NetTool;


/**
 * Created by mayinling on 16/9/19.
 */
public class GoodDailyActivity extends BaseAty{

    private ImageView coverImages;
    private TextView digest;
    private TextView name;
    private TextView desc;
    private ImageView images;
    private TwoDailyBean twoDailyBean;
    private int aa;


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
        images = bindView(R.id.activity_goodthing_daily_images);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        aa = intent.getIntExtra("dailyId", 1246);
        String upUrl = "http://design.zuimeia.com/api/v1/product/";
        String downUrl = "/?device_id=860076039322200&platform=android&lang=zh&appVersion=1.1.9&appVersionCode=10190&systemVersion=23&countryCode=CN&user_id=54289&token=4ff-c8707efb1ab1d1555ba5&package_name=com.zuiapps.zuiworld";
        String finalUrl = upUrl + aa + downUrl;
        netTool.getNetData(finalUrl, TwoDailyBean.class, new NetTool.NetListener<TwoDailyBean>() {
            @Override
            public void onSuccess(TwoDailyBean twoDailyBean) {
                digest.setText(twoDailyBean.getData().getDigest());
                name.setText(twoDailyBean.getData().getName());
                desc.setText(twoDailyBean.getData().getDesc());

            }

            @Override
            public void onError(String errorMsg) {

            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}
