package com.example.dllo.colorfulchoice.video.sports;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseFragment;
import com.example.dllo.colorfulchoice.nettool.URLValue;
import com.example.dllo.colorfulchoice.video.codereuse.CodeReuse;
import com.example.dllo.colorfulchoice.video.xlistview.XListView;

/**
 * Created by ${马庭凯} on 16/9/13.
 */

public class SportsFragment extends BaseFragment implements XListView.IXListViewListener{
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
        codeReuse = new CodeReuse(mContext,xListView);
    }

    @Override
    protected void initData() {
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(this);
        String url = URLValue.VIDEO_FIRST_URL + informationQuantity + URLValue.VIDEO_SECOND_URL + (informationQuantity += 20) + URLValue.VIDEO_THIRD_URL + URLValue.VIDEO_SPORTS + URLValue.VIDEO_FOURTH_URL;
        codeReuse.setAddNum(20);
        codeReuse.getBean(url,URLValue.VIDEO_COOKIE);
    }

    @Override
    public void onRefresh() {
        informationQuantity = 0;
        String url = URLValue.VIDEO_FIRST_URL + informationQuantity + URLValue.VIDEO_SECOND_URL + (informationQuantity += 20) + URLValue.VIDEO_THIRD_URL + URLValue.VIDEO_SPORTS + URLValue.VIDEO_FOURTH_URL;
        codeReuse.onRefresh(url,20);
    }

    @Override
    public void onLoadMore() {
        String url = URLValue.VIDEO_FIRST_URL + informationQuantity + URLValue.VIDEO_SECOND_URL + (informationQuantity += 5) + URLValue.VIDEO_THIRD_URL + URLValue.VIDEO_SPORTS + URLValue.VIDEO_FOURTH_URL;
        codeReuse.onLoadMore(url,5);
    }
}
