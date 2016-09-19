package com.example.dllo.colorfulchoice.goodthing.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseFragment;
import com.example.dllo.colorfulchoice.base.CommonAdapter;
import com.example.dllo.colorfulchoice.base.CommonViewHolder;
import com.example.dllo.colorfulchoice.custom.GrapeGridView;
import com.example.dllo.colorfulchoice.goodthing.EventBusPosition;
import com.example.dllo.colorfulchoice.goodthing.bean.NormalBean;
import com.example.dllo.colorfulchoice.goodthing.bean.PopBean;
import com.example.dllo.colorfulchoice.nettool.NetTool;
import com.example.dllo.colorfulchoice.nettool.URLValue;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by mayinling on 16/9/13.
 * 复用的fragment
 */
public class NormalFragment extends BaseFragment {

    private PullToRefreshGridView mPullRefreshGridView;
    private GridView mGridView;
    int i = 1;
    private LinearLayout llPop;
    private PopupWindow myPopUpWindow;
    private int position;
    private View contentView;
    private PopBean.DataBean.CategoriesBean categoriesBean;
    private String name;
    private NormalBean normalBean;
    private int[] ids = {-1,3,1,2,65,4,54};

    public static NormalFragment getInstance(String tab) {
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 接EventBus
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void getPosition(EventBusPosition eventBusPosition) {
        position = eventBusPosition.getPosition();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initView() {

        // PopUpWindow的布局
        contentView = LayoutInflater.from(getContext()).inflate(R.layout.goodthing_popupwindow, null);
        myPopUpWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);

        mPullRefreshGridView = bindView(R.id.pull_refresh_grid);
        mGridView = mPullRefreshGridView.getRefreshableView();

        // popupWindow;
        llPop = bindView(R.id.normal_fragment_pop_ll);
        llPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUpWindow(view);
            }
        });

        // TODO 收藏点击事件
        mPullRefreshGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                ImageView imageView = (ImageView) view.findViewById(R.id.item_normal_cry_iv);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
            }
        });
    }


    private void showPopUpWindow(View view) {

        GrapeGridView gridView = (GrapeGridView) contentView.findViewById(R.id.goodthing_pop_gv);

        PopBean.DataBean.CategoriesBean bean = NotmalTab.getInstance().getBeanFormPos(ids[position]);
        if(bean!=null) {
            gridView.setAdapter(new CommonAdapter<PopBean.DataBean.CategoriesBean.SubCategoriesBean>(bean.getSub_categories(),mContext,R.layout.item_pop) {

                @Override
                public void setData(PopBean.DataBean.CategoriesBean.SubCategoriesBean subCategoriesBean, CommonViewHolder viewHolder) {
                    viewHolder.setText(R.id.goodthing_pop_gridview_tv,subCategoriesBean.getName());
                }
            });
        }
        myPopUpWindow.setFocusable(true);
        myPopUpWindow.setOutsideTouchable(true);
        myPopUpWindow.setBackgroundDrawable(getResources().getDrawable(R.mipmap.se));
        myPopUpWindow.showAsDropDown(view);


    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        final String url = bundle.getString("url");

        // 下拉刷新之前的数据
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

        // 刷新的数据
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
