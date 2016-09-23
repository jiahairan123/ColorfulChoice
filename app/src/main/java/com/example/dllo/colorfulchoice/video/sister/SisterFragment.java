package com.example.dllo.colorfulchoice.video.sister;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseFragment;
import com.example.dllo.colorfulchoice.nettool.URLValue;
import com.example.dllo.colorfulchoice.video.codereuse.CodeReuse;
import com.example.dllo.colorfulchoice.video.xlistview.XListView;

/**
 * Created by ${马庭凯} on 16/9/13.
 */

public class SisterFragment extends BaseFragment implements XListView.IXListViewListener{
    private XListView xListView;
    private int informationQuantity = 0;
    private CodeReuse codeReuse;
    @Override
    protected int setLayout() {
        return R.layout.fragment_video_child;
    }

    @Override
    protected void initView() {
        xListView = bindView(R.id.fragment_video_child_list_view);
        codeReuse = new CodeReuse(mContext,xListView,5);
    }

    @Override
    protected void initData() {
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(this);
        String url = URLValue.VIDEO_VARIETY_FIRST_URL + informationQuantity + URLValue.VIDEO_VARIETY_SECOND_URL + (informationQuantity += 10) + URLValue.VIDEO_VARIETY_THIRD_URL;
        codeReuse.getBean(url,URLValue.VIDEO_COOKIE);
    }

    @Override
    public void onRefresh() {
        informationQuantity = 0;
        String url = URLValue.VIDEO_VARIETY_FIRST_URL + informationQuantity + URLValue.VIDEO_VARIETY_SECOND_URL + (informationQuantity += 10) + URLValue.VIDEO_VARIETY_THIRD_URL;
        codeReuse.onRefresh(url);
    }

    @Override
    public void onLoadMore() {
        String url = URLValue.VIDEO_VARIETY_FIRST_URL + informationQuantity + URLValue.VIDEO_VARIETY_SECOND_URL + (informationQuantity += 10) + URLValue.VIDEO_VARIETY_THIRD_URL;
        codeReuse.onLoadMore(url);
    }
}
