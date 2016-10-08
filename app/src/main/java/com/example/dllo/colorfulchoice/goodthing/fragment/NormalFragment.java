package com.example.dllo.colorfulchoice.goodthing.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.example.dllo.colorfulchoice.goodthing.bean.NormalBean;
import com.example.dllo.colorfulchoice.goodthing.bean.PopBean;
import com.example.dllo.colorfulchoice.goodthing.bean.PopTwoBean;
import com.example.dllo.colorfulchoice.goodthing.eventbus.EventBusPosition;
import com.example.dllo.colorfulchoice.nettool.NetTool;
import com.example.dllo.colorfulchoice.nettool.URLValue;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.bmob.v3.BmobUser;
import rx.functions.Action1;

/**
 * Created by mayinling on 16/9/13.
 * 复用的fragment
 */
public class NormalFragment extends BaseFragment{

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
    private int count;
    private RadioButton imageCry;
    private RadioButton imageSmile;
    private RadioGroup radioGroup;


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
                        mContext, R.layout.item_normal) {
                    @Override
                    public void setData(final NormalBean.DataBean.ProductsBean productsBean, final CommonViewHolder viewHolder) {
                        viewHolder.setText(R.id.item_normal_name_describe, productsBean.getName());
                        viewHolder.setText(R.id.item_normal_name, productsBean.getDesigner().getName());
                        viewHolder.setText(R.id.item_normal_lable, productsBean.getDesigner().getLabel());
                        viewHolder.setImage(R.id.item_normal_avatar_url, productsBean.getDesigner().getAvatar_url());
                        viewHolder.setImage(R.id.item_normal_cover_images, productsBean.getCover_images().get(0));
                        been = normalBean.getData().getProducts();
                        mPullRefreshGridView.onRefreshComplete();
                        bmobUser = BmobUser.getCurrentUser();
                        if (bmobUser != null) {
                            // 查询数据库
                            DBTools.getInstance().queryUser(productsBean.getCover_images().get(0), bmobUser.getUsername(), new Action1<List<GoodThings>>() {
                                @Override
                                public void call(List<GoodThings> goodThingses) {
                                    count = goodThingses.size();
                                }
                            });
                        } else {
//                            Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                        }

                        radioGroup = viewHolder.getView(R.id.item_radio_group);
                        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                switch (checkedId) {
                                    case R.id.item_normal_smail_iv:
                                        // 存数据库
                                        if (count == 0) {
                                            GoodThings goodThings = new GoodThings();
                                            goodThings.setImgUrl(normalBean.getData().getProducts().get(position).getCover_images().get(0));
//                                            goodThings.setUsername(bmobUser.getUsername());
                                            DBTools.getInstance().insertGoodThing(goodThings);
                                            Toast.makeText(mContext, "喜欢", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(mContext, "已经添加成功", Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                                    case R.id.item_normal_cry_iv:
                                        if (count > 0) {
                                            DBTools.getInstance().deleteGood(productsBean.getCover_images().get(0), bmobUser.getUsername());
                                            Toast.makeText(getContext(), "不喜欢", Toast.LENGTH_SHORT).show();
                                        }
                                        break;
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

//                                                imageCry.setOnClickListener(new View.OnClickListener() {
//                                                    @Override
//                                                    public void onClick(View v) {
//                                                        Toast.makeText(getContext(), "不喜欢", Toast.LENGTH_SHORT).show();
//                                                    }
//                                                });
//
//                                                imageSmile.setOnClickListener(new View.OnClickListener() {
//                                                    @Override
//                                                    public void onClick(View v) {
//                                                        if (count > 0) {
//                                                            Log.d("Sysout", "coun---------t:" + count);
//                                                            imageSmile.setBackgroundResource(R.mipmap.cry);
//                                                            DBTools.getInstance().deleteGood(productsBean.getCover_images().get(0), bmobUser.getUsername());
//                                                            Toast.makeText(getContext(), "取消收藏", Toast.LENGTH_SHORT).show();
//                                                        } else {
//                                                            Log.d("NormalFragment", "count********:" + count);
//                                                            count  ++;
//
//                                                            ScaleAnimation sa = new ScaleAnimation(0, 10, 0, 10);
//                                                            sa.setDuration(1000);
//                                                            sa.setRepeatCount(0);
//                                                            imageSmile.startAnimation(sa);
//                                                            // 存数据库
//                                                            GoodThings goodThings = new GoodThings();
//                                                            goodThings.setImgUrl(normalBean.getData().getProducts().get(position).getCover_images().get(0));
//                                                            DBTools.getInstance().insertGoodThing(goodThings);
//                                                            Toast.makeText(mContext, "收藏", Toast.LENGTH_SHORT).show();
//                                                        }
//                                                    }
//
//                                                });


                                                // 查询数据库
//                                                DBTools.getInstance().queryUser(productsBean.getCover_images().get(0), bmobUser.getUsername(), new Action1<List<GoodThings>>() {
//                                                    @Override
//                                                    public void call(List<GoodThings> goodThingses) {
//                                                        count = goodThingses.size();
//                                                    }
//                                                });

                                                radioGroup = viewHolder.getView(R.id.item_radio_group);
                                                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                    @Override
                                                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                        switch (checkedId){
                                                            case R.id.item_normal_smail_iv:
                                                                // 存数据库
                                                                if (count == 0){
                                                                    GoodThings goodThings = new GoodThings();
                                                                    goodThings.setImgUrl(normalBean.getData().getProducts().get(position).getCover_images().get(0));
//                                                                    goodThings.setUsername(bmobUser.getUsername());
                                                                    DBTools.getInstance().insertGoodThing(goodThings);
                                                                    Toast.makeText(mContext, "喜欢", Toast.LENGTH_SHORT).show();
                                                                } else {
                                                                    Toast.makeText(mContext, "已经添加成功", Toast.LENGTH_SHORT).show();
                                                                }
                                                                break;
                                                            case R.id.item_normal_cry_iv:
                                                                    DBTools.getInstance().deleteGood(productsBean.getCover_images().get(0), bmobUser.getUsername());
                                                                    Toast.makeText(getContext(), "不喜欢", Toast.LENGTH_SHORT).show();
                                                                break;
                                                        }
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

