package com.example.dllo.colorfulchoice.goodthing.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseFragment;
import com.example.dllo.colorfulchoice.base.CommonAdapter;
import com.example.dllo.colorfulchoice.base.CommonViewHolder;
import com.example.dllo.colorfulchoice.goodthing.bean.DailyBean;
import com.example.dllo.colorfulchoice.nettool.NetTool;
import com.example.dllo.colorfulchoice.nettool.URLValue;

/**
 * Created by mayinling on 16/9/13.
 * 第一页Daily的fragment
 */
public class DailyFragment extends BaseFragment {

    private ListView listView;
    private DailyBean dailyBean;
    private int id1;
    private CommonAdapter<DailyBean.DataBean.ProductsBean> adapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_daily;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.daily_fragment_lv);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), GoodDailyActivity.class);
//                dailyBean = new DailyBean();
                id1 = adapter.getItem(position).getId();
                intent.putExtra("dailyId",id1);
                Log.d("DailyFragment", "id1:" + id1);
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

        // 网络请求
        NetTool netTool = new NetTool();
        netTool.getNetData(URLValue.DAILY_URL, DailyBean.class, new NetTool.NetListener<DailyBean>() {
            @Override
            public void onSuccess(DailyBean dailyBean) {
                adapter = new CommonAdapter<DailyBean.DataBean.ProductsBean>(dailyBean.getData().getProducts(), getContext(), R.layout.item_daily) {
                    @Override
                    public void setData(DailyBean.DataBean.ProductsBean bean, CommonViewHolder viewHolder) {
                        viewHolder.setText(R.id.item_daily_name, bean.getDesigner().getName());
                        viewHolder.setText(R.id.item_daily_lable, bean.getDesigner().getLabel());
                        viewHolder.setText(R.id.item_daily_name_describe, bean.getName());

                        viewHolder.setImage(R.id.item_daily_avatar_url, bean.getDesigner().getAvatar_url());
                        viewHolder.setImage(R.id.item_daily_cover_images, bean.getCover_images().get(0));


                    }
                };
                listView.setAdapter(adapter);
            }

            @Override
            public void onError(String errorMsg) {
                Toast.makeText(mContext, "网络连接错误", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
