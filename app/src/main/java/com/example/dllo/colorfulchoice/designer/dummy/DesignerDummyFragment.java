package com.example.dllo.colorfulchoice.designer.dummy;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseFragment;
import com.example.dllo.colorfulchoice.designer.bean.DesignerBean;
import com.example.dllo.colorfulchoice.designer.detail.DesignerDetailsActivity;
import com.example.dllo.colorfulchoice.nettool.NetTool;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

/**
 * Coder: JiaHaiRan
 * created on 16/9/18 11:24
 * <p>
 * 设计fragment的 -- 复用
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
        //gridView
        gridView = bindView(R.id.designer_pull_to_refresh);
        //转圈的小方块
        mGridView = gridView.getRefreshableView();

    }

    @Override
    protected void initData() {

        Bundle bundle = getArguments();
        final String url = bundle.getString(KEY, "default");

        //网络请求
        netTool.getNetData(url, DesignerBean.class, new NetTool.NetListener<DesignerBean>() {
            @Override
            public void onSuccess(final DesignerBean designerBean) {

                adapter = new DummyAdapter();
                adapter.setDesignerBean(designerBean);
                gridView.setAdapter(adapter);

                // Item 监听
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(getActivity(), DesignerDetailsActivity.class);
                        final int id1 = designerBean.getData().getDesigners().get(position).getId();
                        Log.d("DesignerDummyFragment", "id1:" + id1);
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

        // 刷新
        gridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                //获取上次刷新的时间
                String lastRefreshTime = DateUtils.formatDateTime(getContext(),
                        System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                gridView.getLoadingLayoutProxy().setLastUpdatedLabel(lastRefreshTime);

                //下拉
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

                //上拉
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
