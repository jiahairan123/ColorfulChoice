package com.example.dllo.colorfulchoice.goodthing;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseFragment;
import com.example.dllo.colorfulchoice.base.CommonAdapter;
import com.example.dllo.colorfulchoice.base.CommonViewHolder;
import com.example.dllo.colorfulchoice.nettool.NetTool;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

/**
 * Created by mayinling on 16/9/13.
 * 复用的fragment
 */
public class NormalFragment extends BaseFragment {

    private PullToRefreshGridView mPullRefreshGridView;
    private GridView mGridView;
    int i = 1;
    private LinearLayout llPop;



    public static NormalFragment getInstance(String tab){
        Bundle bundle = new Bundle();
        bundle.putString("url", tab);
        NormalFragment fragment = new NormalFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_normal;
    }

    @Override
    protected void initView() {
        mPullRefreshGridView = bindView(R.id.pull_refresh_grid);
        mGridView = mPullRefreshGridView.getRefreshableView();
        // popupWindow;
        llPop = bindView(R.id.normal_fragment_pop_ll);
        llPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUpWindow();
            }
        });

        // TODO 收藏点击事件
        mPullRefreshGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                ImageView imageView = (ImageView) view.findViewById(R.id.item_normal_cry_iv);
                Log.d("NormalFragment", "i:" + i);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("NormalFragment", "i:" + i);
                    }
                });
            }
        });
    }

    private void showPopUpWindow() {


    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        final String url = bundle.getString("url");
        netTool.getNetData(url, NormalBean.class, new NetTool.NetListener<NormalBean>() {
            @Override
            public void onSuccess(NormalBean normalBean) {
               mPullRefreshGridView.setAdapter(new CommonAdapter<NormalBean.DataBean.ProductsBean>(normalBean.getData().getProducts(),
                       getContext(), R.layout.item_normal) {
                   @Override
                   public void setData(NormalBean.DataBean.ProductsBean productsBean, CommonViewHolder viewHolder) {
                       mPullRefreshGridView.onRefreshComplete();

                       mPullRefreshGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
                           @Override
                           public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                               // 下拉刷新
                               Log.d("NormalFragment", "刷新");
                               // 获取上次刷新的时间
                               String lable = DateUtils.formatDateTime(getContext(),
                                       System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE
                                               | DateUtils.FORMAT_ABBREV_ALL);
                               refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(lable);

                               netTool.getNetData(url, NormalBean.class, new NetTool.NetListener<NormalBean>() {
                                   @Override
                                   public void onSuccess(NormalBean normalBean) {
                                       mPullRefreshGridView.setAdapter(new CommonAdapter<NormalBean.DataBean.ProductsBean>(normalBean.getData().getProducts(),
                                               getContext(), R.layout.item_normal) {
                                           @Override
                                           public void setData(NormalBean.DataBean.ProductsBean productsBean, CommonViewHolder viewHolder) {
                                               viewHolder.setText(R.id.item_normal_name_describe, productsBean.getName());
                                               viewHolder.setText(R.id.item_normal_name, productsBean.getDesigner().getName());
                                               viewHolder.setText(R.id.item_normal_lable, productsBean.getDesigner().getLabel());
                                               viewHolder.setImage(R.id.item_normal_avatar_url, productsBean.getDesigner().getAvatar_url());
                                               viewHolder.setImage(R.id.item_normal_cover_images, productsBean.getCover_images().get(0));
                                               mPullRefreshGridView.onRefreshComplete();
                                           }
                                       });
                                   }

                                   @Override
                                   public void onError(String errorMsg) {
                                       Toast.makeText(mContext, "网络连接错误", Toast.LENGTH_SHORT).show();
                                   }
                               });


                           }


                           @Override
                           public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                               // 上拉加载
                               String newPage = "p" + i;
                               String newUrl = url.replace("p1", newPage);
                               i += 1;
                               netTool.getNetData(newUrl, NormalBean.class, new NetTool.NetListener<NormalBean>() {
                                   @Override
                                   public void onSuccess(NormalBean normalBean) {
                                       mPullRefreshGridView.setAdapter(new CommonAdapter<NormalBean.DataBean.ProductsBean>(normalBean.getData().getProducts(),
                                               getContext(), R.layout.item_normal) {
                                           @Override
                                           public void setData(NormalBean.DataBean.ProductsBean productsBean, CommonViewHolder viewHolder) {
                                               viewHolder.setText(R.id.item_normal_name_describe, productsBean.getName());
                                               viewHolder.setText(R.id.item_normal_name, productsBean.getDesigner().getName());
                                               viewHolder.setText(R.id.item_normal_lable, productsBean.getDesigner().getLabel());
                                               viewHolder.setImage(R.id.item_normal_avatar_url, productsBean.getDesigner().getAvatar_url());
                                               viewHolder.setImage(R.id.item_normal_cover_images, productsBean.getCover_images().get(0));
                                               mPullRefreshGridView.onRefreshComplete();
                                           }
                                       });
                                   }

                                   @Override
                                   public void onError(String errorMsg) {
                                       Toast.makeText(mContext, "网络连接错误", Toast.LENGTH_SHORT).show();
                                   }
                               });

                           }
                       });

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
