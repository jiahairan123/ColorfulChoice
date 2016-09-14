package com.example.dllo.colorfulchoice.video.recommend;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.activity.MainActivity;
import com.example.dllo.colorfulchoice.base.BaseFragment;
import com.example.dllo.colorfulchoice.base.CommonAdapter;
import com.example.dllo.colorfulchoice.base.CommonViewHolder;
import com.example.dllo.colorfulchoice.nettool.NetTool;
import com.example.dllo.colorfulchoice.nettool.URLValue;
import com.example.dllo.colorfulchoice.video.VideoBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${马庭凯} on 16/9/13.
 */

public class RecommendFragment extends BaseFragment {
    private ListView listView;
    private RecommendAdapter recommendAdapter;
    private List<Integer> integerList;

    @Override
    protected int setLayout() {
        return R.layout.fragment_video_child;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.fragment_video_child_list_view);
        integerList = new ArrayList<>();
    }

    @Override
    protected void initData() {
        try {
            netTool.post(URLValue.VIDEO_RECOMMEND_URL, URLValue.VIDEO_COOKIE, VideoBean.class, new NetTool.NetListener<VideoBean>() {
                @Override
                public void onSuccess(VideoBean videoBean) {
                    if(videoBean != null){
                        for (int i = 0; i < videoBean.getResult().size(); i++) {
                            if (videoBean.getResult().get(i).getTitle() != null){
                                integerList.add(i);
                            }
                        }
                    }
                    recommendAdapter = new RecommendAdapter(videoBean.getResult(), getContext(), R.layout.fragment_video_child_listview,integerList);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        listView.setAdapter(recommendAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    public class RecommendAdapter extends CommonAdapter<VideoBean.ResultBean> {
        public RecommendAdapter(List<VideoBean.ResultBean> beanList, Context context, int id,List<Integer> integerList) {
            super(beanList, context, id,integerList);
        }

        @Override
        public void setData(VideoBean.ResultBean resultBean, CommonViewHolder viewHolder) {
            Glide.with(getContext()).load(resultBean.getImage()).into((ImageView) viewHolder.getView(R.id.video_picture));
            viewHolder.setText(R.id.video_title, resultBean.getTitle());
            viewHolder.getView(R.id.video_title).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            viewHolder.getView(R.id.video_play).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            viewHolder.getView(R.id.video_collect).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            viewHolder.getView(R.id.video_comment).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
