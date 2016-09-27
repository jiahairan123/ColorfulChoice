package com.example.dllo.colorfulchoice.goodthing.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseFragment;
import com.example.dllo.colorfulchoice.base.CommonAdapter;
import com.example.dllo.colorfulchoice.base.CommonViewHolder;
import com.example.dllo.colorfulchoice.custom.GrapeGridView;
import com.example.dllo.colorfulchoice.database.DBTools;
import com.example.dllo.colorfulchoice.database.GoodThings;
import com.example.dllo.colorfulchoice.goodthing.activity.NormalTwoActivity;
import com.example.dllo.colorfulchoice.goodthing.bean.PopTwoBean;
import com.example.dllo.colorfulchoice.goodthing.eventbus.EventBusPosition;
import com.example.dllo.colorfulchoice.goodthing.bean.NormalBean;
import com.example.dllo.colorfulchoice.goodthing.bean.PopBean;
import com.example.dllo.colorfulchoice.nettool.NetTool;
import com.example.dllo.colorfulchoice.nettool.URLValue;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;

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
    private RelativeLayout relativeLayout;
    private CommonAdapter<NormalBean.DataBean.ProductsBean> adapter;
    private int[] ids = {-1, 3, 1, 2, 65, 4, 54};
    private String url;
    private List<NormalBean.DataBean.ProductsBean> been;
    private BmobUser bmobUser;


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

        relativeLayout = bindView(R.id.item_normal_rl);

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

        bmobUser = BmobUser.getCurrentUser();
    }

    private void showPopUpWindow(View view) {

        final GrapeGridView gridView = (GrapeGridView) contentView.findViewById(R.id.goodthing_pop_gv);
        final PopBean.DataBean.CategoriesBean bean = NotmalTab.getInstance().getBeanFormPos(ids[position]);
        if (bean != null) {
            gridView.setAdapter(new CommonAdapter<PopBean.DataBean.CategoriesBean.SubCategoriesBean>(bean.getSub_categories(), mContext, R.layout.item_pop) {

                @Override
                public void setData(PopBean.DataBean.CategoriesBean.SubCategoriesBean subCategoriesBean, CommonViewHolder viewHolder) {
                    viewHolder.setText(R.id.goodthing_pop_gridview_tv, subCategoriesBean.getName());
                }
            });
        }

        // 里面的点击事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "稍后", Toast.LENGTH_SHORT).show();
                String oneUrl = URLValue.PopTwo_ONE;
                int id1 = bean.getSub_categories().get(position).getId();
                String twoUrl = URLValue.PopTwo_two;
                String url = oneUrl + id1 + twoUrl;
                netTool.getNetData(url, PopTwoBean.class, new NetTool.NetListener<PopTwoBean>() {
                    @Override
                    public void onSuccess(PopTwoBean popTwoBean) {
                        mPullRefreshGridView.setAdapter(new CommonAdapter<PopTwoBean.DataBean.ProductsBean>(popTwoBean
                                .getData().getProducts(), getContext(), R.layout.item_normal) {

                            @Override
                            public void setData(PopTwoBean.DataBean.ProductsBean productsBean, CommonViewHolder viewHolder) {
                                viewHolder.setText(R.id.item_normal_name_describe, productsBean.getName());
                                viewHolder.setText(R.id.item_normal_name, productsBean.getDesigner().getName());
                                viewHolder.setText(R.id.item_normal_lable, productsBean.getDesigner().getLabel());
                                viewHolder.setImage(R.id.item_normal_avatar_url, productsBean.getDesigner().getAvatar_url());
                                viewHolder.setImage(R.id.item_normal_cover_images, productsBean.getCover_images().get(0));
                            }
                        });
                    }

                    @Override
                    public void onError(String errorMsg) {

                    }
                });
                myPopUpWindow.dismiss();
            }
        });
        myPopUpWindow.setFocusable(true);
        myPopUpWindow.setOutsideTouchable(true);
        myPopUpWindow.setBackgroundDrawable(getResources().getDrawable(R.mipmap.se));
        myPopUpWindow.showAsDropDown(view);
    }


    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        // viewPager 的网址
        url = bundle.getString("url");

        // 初始加载的数据
        netTool.getNetData(url, NormalBean.class, new NetTool.NetListener<NormalBean>() {
            @Override
            public void onSuccess(final NormalBean normalBean) {

                mPullRefreshGridView.setAdapter(new CommonAdapter<NormalBean.DataBean.ProductsBean>(normalBean.getData().getProducts(),
                        getContext(), R.layout.item_normal) {
                    @Override
                    public void setData(final NormalBean.DataBean.ProductsBean productsBean, CommonViewHolder viewHolder) {

                        viewHolder.setText(R.id.item_normal_name_describe, productsBean.getName());
                        viewHolder.setText(R.id.item_normal_name, productsBean.getDesigner().getName());
                        viewHolder.setText(R.id.item_normal_lable, productsBean.getDesigner().getLabel());
                        viewHolder.setImage(R.id.item_normal_avatar_url, productsBean.getDesigner().getAvatar_url());
                        viewHolder.setImage(R.id.item_normal_cover_images, productsBean.getCover_images().get(0));
                        been = normalBean.getData().getProducts();

                        mPullRefreshGridView.onRefreshComplete();

                        ImageView imageCry = viewHolder.getView(R.id.item_normal_cry_iv);
                        imageCry.bringToFront();
                        final ImageView imageSmile = viewHolder.getView(R.id.item_normal_smail_iv);
                        imageSmile.bringToFront();
                        imageCry.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getContext(), "动画" + position, Toast.LENGTH_SHORT).show();
                            }
                        });

                        imageSmile.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (bmobUser == null) {
                                    Log.d("TAGGG", "onClick: bmobUser is null");
                                }
                                // 点击之后, 先查一遍数据库
                                ArrayList<GoodThings> goodThingses = new ArrayList<GoodThings>();
                                DBTools.getInstance(getContext()).queryUser(productsBean.getCover_images().get(0), bmobUser.getUsername(),goodThingses);
                                if (goodThingses.size() == 0){
                                    imageSmile.setBackgroundResource(R.mipmap.ic_launcher);

                                    // 存数据库
                                    GoodThings goodThings = new GoodThings();
                                    goodThings.setImgUrl(normalBean.getData().getProducts().get(position).getCover_images().get(0));

                                    DBTools.getInstance(getContext()).insertGoodThing(goodThings);
                                    Log.d("NormalFragment", "goodThings:" + goodThings);
                                } else {
                                    // 取消收藏
                                    imageSmile.setBackgroundResource(R.mipmap.smile);
                                    DBTools.getInstance(getContext()).deleteGood(productsBean.getCover_images().get(0), bmobUser.getUsername());
                                    Toast.makeText(mContext, "取消收藏", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                });

                // item 的点击事件
                mPullRefreshGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        int id1 = been.get(position).getId();
                        Intent intent = new Intent(getActivity(), NormalTwoActivity.class);
                        intent.putExtra("id", id1);
                        getActivity().startActivity(intent);

                        // 进来先查一圈数据库, 已经收藏了再次点击就取消收藏, 没收藏过点击就收藏

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
                                i += 1;
                                String newUrl = url.replace("p1", newPage);
                                netTool.getNetData(newUrl, NormalBean.class, new NetTool.NetListener<NormalBean>() {
                                    @Override
                                    public void onSuccess(NormalBean normalBean) {
                                        adapter = new CommonAdapter<NormalBean.DataBean.ProductsBean>(normalBean.getData().getProducts(),
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
                                        };

                                        mPullRefreshGridView.setAdapter(adapter);
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
