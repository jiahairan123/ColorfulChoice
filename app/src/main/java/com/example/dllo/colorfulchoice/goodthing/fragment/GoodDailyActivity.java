package com.example.dllo.colorfulchoice.goodthing.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseAty;
import com.example.dllo.colorfulchoice.base.CommonAdapter;
import com.example.dllo.colorfulchoice.base.CommonViewHolder;
import com.example.dllo.colorfulchoice.base.MyApp;
import com.example.dllo.colorfulchoice.goodthing.bean.TwoDailyBean;
import com.example.dllo.colorfulchoice.nettool.NetTool;

import java.util.List;


/**
 * Created by mayinling on 16/9/19.
 */
public class GoodDailyActivity extends BaseAty{

    private ImageView coverImages;
    private TextView digest;
    private TextView name;
    private TextView desc;
    private ImageView images;
    private int aa;
    private ImageView avatar;
    private TextView nameTwo;
    private TextView lable;
    private TextView description;
    private GridView gridView;



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

        avatar = bindView(R.id.activity_goodthing_avatar_url);
        nameTwo = bindView(R.id.activity_goodthing_name_two);
        lable = bindView(R.id.activity_goodthing_lable);
        description = bindView(R.id.activity_goodthing_description);

        gridView = bindView(R.id.activity_goodthing_lianjie);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        aa = intent.getIntExtra("dailyId", 1246);
        String upUrl = "http://design.zuimeia.com/api/v1/product/";
        String downUrl = "/?device_id=860076039322200&platform=android&lang=zh&appVersion=1.1.9&appVersionCode=10190&systemVersion=23&countryCode=CN&user_id=54289&token=4ff-c8707efb1ab1d1555ba5&package_name=com.zuiapps.zuiworld";
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

                // 获取网络图片
                Glide.with(getApplicationContext()).load(twoDailyBean.getData().getCover_images().get(0)).into(coverImages);
                List<String> imgUrls =twoDailyBean.getData().getImages();
                Glide.with(getApplicationContext()).load(imgUrls.get(0)).into(images);
                Glide.with(getApplicationContext()).load(twoDailyBean.getData().getDesigner().getAvatar_url()).into(avatar);

                // 下面的相关链接
                gridView.setAdapter(new CommonAdapter<TwoDailyBean.DataBean.ReferProductsBean>(twoDailyBean
                .getData().getRefer_products(), getApplicationContext(), R.layout.item_daily_lianjie) {
                    @Override
                    public void setData(TwoDailyBean.DataBean.ReferProductsBean referProductsBean, CommonViewHolder viewHolder) {
                        for (int i = 0; i < twoDailyBean.getData().getRefer_products().size(); i++) {
                            List<String> images = twoDailyBean.getData().getRefer_products().get(i).getImages();
                            for (int j = 0; j < images.size(); j++) {
                                viewHolder.setImage(R.id.lianjie_iv, images.get(j));
                                Log.d("GoodDailyActivity", images.get(j));

                            }
                        }
                    }
                });
            }

            @Override
            public void onError(String errorMsg) {
                Toast.makeText(GoodDailyActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
