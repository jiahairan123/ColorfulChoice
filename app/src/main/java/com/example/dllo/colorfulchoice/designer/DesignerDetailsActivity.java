package com.example.dllo.colorfulchoice.designer;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseAty;
import com.example.dllo.colorfulchoice.nettool.NetTool;
import com.example.dllo.colorfulchoice.nettool.URLValue;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Coder: JiaHaiRan
 * created on 16/9/21 09:31
 */

public class DesignerDetailsActivity extends BaseAty {

    //图片的网址
    //http://design.zuimeia.com/api/v1/products/designer/120/?page=1&page_size=30&user_id=0&device_id=860076039322200&platform=android&lang=zh&appVersion=1.1.9&appVersionCode=10190&systemVersion=23&countryCode=CN&user_id=56867&token=4fi-d3ee768bd49d1239de90&package_name=com.zuiapps.zuiworld

    //轮播图的网址
    //http://design.zuimeia.com/api/v1/designer/120/?device_id=860076039322200&platform=android&lang=zh&appVersion=1.1.9&appVersionCode=10190&systemVersion=23&countryCode=CN&user_id=56867&token=4fi-d3ee768bd49d1239de90&package_name=com.zuiapps.zuiworld

    //线上购买的
    //design.zuimeia.com/api/v1/shops/designer/120/?device_id=860076039322200&platform=android&lang=zh&appVersion=1.1.9&appVersionCode=10190&systemVersion=23&countryCode=CN&user_id=56867&token=4fi-d3ee768bd49d1239de90&package_name=com.zuiapps.zuiworld

    private ViewPager viewPager;
    private CircleImageView designerPic;
    private TextView designerName, designerPosition, designerWords, designerHistory;
    private Button signBtn;
    private TurnPagerAdapter adapter;
    private Handler handler = new Handler(Looper.myLooper());


    @Override
    protected int setLayout() {
        return R.layout.activity_designer_details;
    }

    @Override
    protected void initView() {

        viewPager = bindView(R.id.designer_detail_view_pager);
        designerPic = bindView(R.id.designer_detail_designer_pic);
        designerName = bindView(R.id.designer_detail_designer_name);
        designerPosition = bindView(R.id.designer_detail_designer_position);
        designerWords = bindView(R.id.designer_detail_designer_words);
        designerHistory = bindView(R.id.designer_detail_designer_history);
        signBtn = bindView(R.id.designer_detail_sign_btn);
        signBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String url = "http://design.zuimeia.com/api/v1/designer/120/?device_id=860076039322200&platform=android&lang=zh&appVersion=1.1.9&appVersionCode=10190&systemVersion=23&countryCode=CN&user_id=56867&token=4fi-d3ee768bd49d1239de90&package_name=com.zuiapps.zuiworld";

        adapter = new TurnPagerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(2000, true);

        netTool.getNetData(url, TurnPagerBean.class, new NetTool.NetListener<TurnPagerBean>() {
            @Override
            public void onSuccess(TurnPagerBean turnPagerBean) {







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
