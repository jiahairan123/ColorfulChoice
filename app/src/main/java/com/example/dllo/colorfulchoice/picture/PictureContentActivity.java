package com.example.dllo.colorfulchoice.picture;

import android.view.View;
import android.widget.LinearLayout;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseAty;

/**
 * Coder: JiaHaiRan
 * created on 16/9/19 09:29
 */

// http://design.zuimeia.com/api/v1/article/  69  /?device_id=860076039322200&platform=android&lang=zh&appVersion=1.1.9&appVersionCode=10190&systemVersion=23&countryCode=CN&user_id=54289&token=4ff-c8707efb1ab1d1555ba5&package_name=com.zuiapps.zuiworld

public class PictureContentActivity extends BaseAty{

    static String startUrl = "http://design.zuimeia.com/api/v1/article/";
    static String endUrl = "/?device_id=860076039322200&platform=android&lang=zh&appVersion=1.1.9&appVersionCode=10190&systemVersion=23&countryCode=CN&user_id=54289&token=4ff-c8707efb1ab1d1555ba5&package_name=com.zuiapps.zuiworld";
    int num = 30;

    String myUrl = startUrl + num + endUrl;
    @Override
    protected int setLayout() {
        return R.layout.activity_picture_content;
    }

    @Override
    protected void initView() {
        LinearLayout linearLayout = bindView(R.id.picture_content_ll);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
