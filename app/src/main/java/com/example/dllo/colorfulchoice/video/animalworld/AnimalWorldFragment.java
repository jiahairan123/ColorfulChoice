package com.example.dllo.colorfulchoice.video.animalworld;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ListView;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseFragment;
import com.example.dllo.colorfulchoice.base.CommonAdapter;
import com.example.dllo.colorfulchoice.base.CommonViewHolder;
import com.example.dllo.colorfulchoice.video.VideoBean;

import java.util.List;

/**
 * Created by ${马庭凯} on 16/9/13.
 */

public class AnimalWorldFragment extends BaseFragment{
    private ListView listView;
    @Override
    protected int setLayout() {
        return R.layout.fragment_video_child;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.fragment_video_child_list_view);
    }

    @Override
    protected void initData() {

    }

//    public class AnimalWorldAdapter extends CommonAdapter<VideoBean> {
//        public AnimalWorldAdapter(List<VideoBean> beanList, Context context, int id,) {
//            super(beanList, context, id);
//        }
//
//        @Override
//        public void setData(VideoBean videoBean, CommonViewHolder viewHolder) {
//
//        }
//    }

}
