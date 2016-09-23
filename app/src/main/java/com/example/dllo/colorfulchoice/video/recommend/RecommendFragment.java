package com.example.dllo.colorfulchoice.video.recommend;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseFragment;
import com.example.dllo.colorfulchoice.base.CommonAdapter;
import com.example.dllo.colorfulchoice.base.CommonViewHolder;
import com.example.dllo.colorfulchoice.nettool.NetTool;
import com.example.dllo.colorfulchoice.nettool.URLValue;
import com.example.dllo.colorfulchoice.video.collect.CollectBean;
import com.example.dllo.colorfulchoice.video.collect.JudgeCollectChange;
import com.example.dllo.colorfulchoice.video.VideoBean;
import com.example.dllo.colorfulchoice.video.xlistview.XListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by ${马庭凯} on 16/9/13.
 */

public class RecommendFragment extends BaseFragment implements XListView.IXListViewListener {
    private RecommendAdapter recommendAdapter;
    private XListView xListView;
    private List<VideoBean.ResultBean> resultBeanList;
    private int informationQuantity = 20;

    @Override
    protected int setLayout() {
        return R.layout.fragment_video_child;
    }

    @Override
    protected void initView() {
        xListView = bindView(R.id.fragment_video_child_list_view);
        resultBeanList = new ArrayList<>();
    }

    @Override
    protected void initData() {
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(this);
        getBean(URLValue.VIDEO_RECOMMEND_URL, URLValue.VIDEO_COOKIE);
        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    private void getBean(String url, String cookie) {
        try {
            netTool.post(url, cookie, VideoBean.class, new NetTool.NetListener<VideoBean>() {
                @Override
                public void onSuccess(VideoBean videoBean) {
                    Log.d("RecommendFragment", "success");
                    if (videoBean != null) {
                        for (int i = videoBean.getResult().size() - 1; i >= 0; i--) {
                            Log.d("Exercise", "videoBean.getResult().size():" + videoBean.getResult().size());
                            if (videoBean.getResult().get(i).getTitle() != null) {
                                resultBeanList.add(videoBean.getResult().get(i));
                            }
                        }
                        onLoad();
                    }
                    recommendAdapter = new RecommendAdapter(resultBeanList, getContext(), R.layout.fragment_video_child_listview);
                    Log.d("Exercise", "resultBeanList.size():" + resultBeanList.size());
                    xListView.setAdapter(recommendAdapter);
                    recommendAdapter.notifyDataSetChanged();
                    xListView.setSelection(resultBeanList.size() - 30);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onLoad() {
        xListView.stopRefresh();
        xListView.stopLoadMore();
    }

    @Override
    public void onRefresh() {
        resultBeanList.clear();
        getBean(URLValue.VIDEO_RECOMMEND_URL, URLValue.VIDEO_COOKIE);
    }

    @Override
    public void onLoadMore() {
        String url = URLValue.VIDEO_RECOMMEND_TOP_FIRST_URL + informationQuantity + URLValue.VIDEO_RECOMMEND_TOP_SECOND_URL + (informationQuantity += 30) + URLValue.VIDEO_RECOMMEND_TOP_THIRD_URL;
        getBean(url, URLValue.VIDEO_COOKIE);
    }

    public class RecommendAdapter extends CommonAdapter<VideoBean.ResultBean> {
        private Handler mHandler;
        private Handler handler;
        private Context context;
        private CommonViewHolder videoViewHolder = null;

        public RecommendAdapter(List<VideoBean.ResultBean> beanList, Context context, int id) {
            super(beanList, context, id);
            this.context = context;
        }

        @Override
        public void setData(final VideoBean.ResultBean resultBean, final CommonViewHolder viewHolder) {
            viewHolder.setImage(R.id.video_picture, resultBean.getImage());
            viewHolder.setText(R.id.video_title, resultBean.getTitle());
            if (videoViewHolder != null) {
                if (videoViewHolder.getPosition() < xListView.getFirstVisiblePosition() || videoViewHolder.getPosition() > xListView.getLastVisiblePosition()) {
                    videoViewHolder.getView(R.id.video_item_video_view).setVisibility(View.GONE);
                    videoViewHolder.getView(R.id.rl_top).setVisibility(View.VISIBLE);
                    if (((VideoView) videoViewHolder.getView(R.id.video_item_video_view)).isPlaying()) {
                        ((VideoView) videoViewHolder.getView(R.id.video_item_video_view)).pause();
                        ((VideoView) videoViewHolder.getView(R.id.video_item_video_view)).stopPlayback();
                    }
                }
            }
            viewHolder.getView(R.id.video_play).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (videoViewHolder != null) {
                        videoViewHolder.getView(R.id.video_item_video_view).setVisibility(View.GONE);
                        videoViewHolder.getView(R.id.rl_top).setVisibility(View.VISIBLE);
                        if (((VideoView) videoViewHolder.getView(R.id.video_item_video_view)).isPlaying()) {
                            ((VideoView) videoViewHolder.getView(R.id.video_item_video_view)).pause();
                            ((VideoView) videoViewHolder.getView(R.id.video_item_video_view)).stopPlayback();
                        }
                    }
                    videoViewHolder = viewHolder;
                    VideoView videoView = videoViewHolder.getView(R.id.video_item_video_view);
                    videoViewHolder.getView(R.id.rl_top).setVisibility(View.GONE);
                    videoView.setVisibility(View.VISIBLE);
                    Log.d("RecommendAdapter", resultBean.getVideo_url());
                    videoView.setVideoPath(resultBean.getVideo_url());
                    videoView.setMediaController(new MediaController(context));
                    videoView.requestFocus();
                }
            });
            mHandler = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message message) {
                    if (message.what == 1) {
                        viewHolder.setBackground(R.id.video_collect, R.mipmap.star);
                    } else if (message.what == 0) {
                        viewHolder.setBackground(R.id.video_collect, R.mipmap.star_gray);
                    }
                    return false;
                }
            });
            JudgeCollectChange.getInstance().judgeCollectExit(resultBean.getItemid(), mHandler);
            viewHolder.getView(R.id.video_collect).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handler = new Handler(new Handler.Callback() {
                        @Override
                        public boolean handleMessage(Message message) {
                            switch (message.what) {
                                case 0:
                                    CollectBean.getInstance().setResultBeanList(CollectBean.getInstance().new ResultBean(resultBean.getItemid(), resultBean.getTitle(), resultBean.getDate(), resultBean.getImage(), resultBean.getVideo_url()));
                                    viewHolder.setBackground(R.id.video_collect, R.mipmap.star);
                                    Log.d("RecommendAdapter", "CollectBean.getInstance().getResultBeanList().size():" + CollectBean.getInstance().getResultBeanList().size());
                                    break;
                                case 1:
                                    Log.d("RecommendAdapter", "对收藏信息进行了一次判断");
                                    CollectBean.getInstance().getResultBeanList().remove((int) message.obj);
                                    Log.d("RecommendAdapter", "CollectBean.getInstance().getResultBeanList().size():" + CollectBean.getInstance().getResultBeanList().size());
                                    viewHolder.setBackground(R.id.video_collect, R.mipmap.star_gray);
                                    break;
                            }
                            return false;
                        }
                    });
                    Log.d("RecommendAdapter", "Thread.currentThread().getId():" + Thread.currentThread().getId());
                    JudgeCollectChange.getInstance().judgeCollectExit(resultBean.getItemid(), handler);
                }
            });
            viewHolder.getView(R.id.video_comment).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    //跳转到评论页面
                }
            });
        }
    }
}
