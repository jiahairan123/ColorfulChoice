package com.example.dllo.colorfulchoice.goodthing;

import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseFragment;
import com.example.dllo.colorfulchoice.base.CommonAdapter;
import com.example.dllo.colorfulchoice.base.CommonViewHolder;
import com.example.dllo.colorfulchoice.nettool.NetTool;
import com.example.dllo.colorfulchoice.nettool.URLValue;

import java.util.ArrayList;

/**
 * Created by mayinling on 16/9/13.
 * 第一页Daily的fragment
 */
public class DailyFragment extends BaseFragment {

    private ListView listView;

    @Override
    protected int setLayout() {
        return R.layout.fragment_daily;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.daily_fragment_lv);

    }

    @Override
    protected void initData() {

        // 网络请求
        NetTool netTool = new NetTool();
        netTool.getNetData(URLValue.DAILYA_url, DailyBean.class, new NetTool.NetListener<DailyBean>() {
            @Override
            public void onSuccess(DailyBean dailyBean) {
                listView.setAdapter(new CommonAdapter<DailyBean.DataBean.ProductsBean>(dailyBean.getData().getProducts(), mContext, R.layout.item_daily) {
                    @Override
                    public void setData(DailyBean.DataBean.ProductsBean bean, CommonViewHolder viewHolder) {
                        viewHolder.setText(R.id.item_daily_name, bean.getDesigner().getName());
                        viewHolder.setText(R.id.item_daily_lable, bean.getDesigner().getLabel());
                        viewHolder.setText(R.id.item_daily_name_describe, bean.getName());

                        viewHolder.setImage(R.id.item_daily_avatar_url, bean.getDesigner().getAvatar_url());
                        viewHolder.setImage(R.id.item_daily_cover_images, bean.getCover_images().get(0));


                    }
                });
            }

            @Override
            public void onError(String errorMsg) {
                Toast.makeText(mContext, "网络连接错误", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
