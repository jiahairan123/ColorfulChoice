package com.example.dllo.colorfulchoice.designer;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.GridView;
import android.widget.Toast;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseFragment;
import com.example.dllo.colorfulchoice.nettool.NetTool;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

/**
 * Coder: JiaHaiRan
 * created on 16/9/18 11:24
 */

public class DesignerDummyFragment extends BaseFragment {

    private static final String KEY = "url";
    private PullToRefreshGridView gridView;
    private GridView mGridView;
    int sum = 30;
    private DummyAdapter adapter;

    /**
     * 在这里面写个获取实例的方法
     * 得到对应的url
     */

    public static DesignerDummyFragment getInstance(String url) {

        Bundle bundle = new Bundle();
        bundle.putString(KEY, url);

        DesignerDummyFragment fragment = new DesignerDummyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_designer_dummy;
    }

    @Override
    protected void initView() {
        gridView = bindView(R.id.designer_pull_to_refresh);
        mGridView = gridView.getRefreshableView();

    }

    @Override
    protected void initData() {

        Bundle bundle = getArguments();
        final String url = bundle.getString(KEY, "default");
        //获取网络数据
        netTool.getNetData(url, DesignerBean.class, new NetTool.NetListener<DesignerBean>() {
            @Override
            public void onSuccess(final DesignerBean designerBean) {
                //加网络数据的时候  在这里获取当前用户
                adapter = new DummyAdapter();
                gridView.setAdapter(adapter);
                adapter.setDesignerBean(designerBean);

            }

            @Override
            public void onError(String errorMsg) {
                Toast.makeText(mContext, "网络连接错误", Toast.LENGTH_SHORT).show();
            }
        });

        //GridView 加刷新
        gridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                //获取上次刷新的时间
                String lastRefreshTime = DateUtils.formatDateTime(getContext(),
                        System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                gridView.getLoadingLayoutProxy().setLastUpdatedLabel(lastRefreshTime);

                //下拉刷新
                netTool.getNetData(url, DesignerBean.class, new NetTool.NetListener<DesignerBean>() {
                    @Override
                    public void onSuccess(DesignerBean designerBean) {
                        adapter.setDesignerBean(designerBean);
                        gridView.onRefreshComplete();
                    }

                    @Override
                    public void onError(String errorMsg) {
                        Toast.makeText(mContext, "网络连接错误", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {

                //上拉加载
                        sum += 10;
                        String part = "size=" + sum;
                        String newUrl = url.replace("size=30", part);

                netTool.getNetData(newUrl, DesignerBean.class, new NetTool.NetListener<DesignerBean>() {
                    @Override
                    public void onSuccess(DesignerBean designerBean) {
                        adapter.setDesignerBean(designerBean);
                        gridView.onRefreshComplete();
                    }

                    @Override
                    public void onError(String errorMsg) {
                        Toast.makeText(mContext, "网络连接错误", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }

}
