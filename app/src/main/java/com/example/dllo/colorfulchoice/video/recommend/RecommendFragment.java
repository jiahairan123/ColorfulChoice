package com.example.dllo.colorfulchoice.video.recommend;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
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
import com.example.dllo.colorfulchoice.video.CollectBean;
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
    private Handler mHandler;

    @Override
    protected int setLayout() {
        return R.layout.fragment_video_child;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.fragment_video_child_list_view);
        integerList = new ArrayList<>();
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    protected void initData() {
        try {
            netTool.post(URLValue.VIDEO_RECOMMEND_URL, URLValue.VIDEO_COOKIE, VideoBean.class, new NetTool.NetListener<VideoBean>() {
                @Override
                public void onSuccess(VideoBean videoBean) {
                    if (videoBean != null) {
                        for (int i = 0; i < videoBean.getResult().size(); i++) {
                            if (videoBean.getResult().get(i).getTitle() != null) {
                                integerList.add(i);
                            }
                        }
                    }
                    recommendAdapter = new RecommendAdapter(videoBean.getResult(), getContext(), R.layout.fragment_video_child_listview, integerList);
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
                Log.d("RecommendFragment", "i:" + i);
            }
        });
    }

    public class RecommendAdapter extends CommonAdapter<VideoBean.ResultBean> {
        private boolean collectChange = false;

        public RecommendAdapter(List<VideoBean.ResultBean> beanList, Context context, int id, List<Integer> integerList) {
            super(beanList, context, id, integerList);
            notifyDataSetChanged();
        }

        @Override
        public void setData(final VideoBean.ResultBean resultBean, final CommonViewHolder viewHolder) {
            Glide.with(getContext()).load(resultBean.getImage()).into((ImageView) viewHolder.getView(R.id.video_picture));
            viewHolder.setText(R.id.video_title, resultBean.getTitle());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (CollectBean.getInstance().getResultBeanList().size() > 0) {
                        int i = 0;
                        for (; i < CollectBean.getInstance().getResultBeanList().size(); i++) {
                            if (CollectBean.getInstance().getResultBeanList().get(i).getItemId().equals(resultBean.getItemid())) {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        viewHolder.setBackground(R.id.video_collect, R.mipmap.star);
                                    }
                                });
                            }
                        }
                        if (i == CollectBean.getInstance().getResultBeanList().size()) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    viewHolder.setBackground(R.id.video_collect, R.mipmap.star_gray);
                                }
                            });
                        }
                    }
                }
            }).start();
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
                    if (!collectChange) {
                        CollectBean.getInstance().setResultBeanList(CollectBean.getInstance().new ResultBean(resultBean.getItemid(), resultBean.getTitle(), resultBean.getDate(), resultBean.getImage(), resultBean.getVideo_url()));
                        viewHolder.setBackground(R.id.video_collect, R.mipmap.star);
                        collectChange = true;
                    } else {
                        CollectBean.getInstance().getResultBeanList().remove(resultBean);
                        viewHolder.setBackground(R.id.video_collect,R.mipmap.star_gray);
                        collectChange = false;
                    }
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
