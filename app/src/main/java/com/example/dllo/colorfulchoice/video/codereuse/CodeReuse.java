package com.example.dllo.colorfulchoice.video.codereuse;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.CommonAdapter;
import com.example.dllo.colorfulchoice.base.CommonViewHolder;
import com.example.dllo.colorfulchoice.nettool.NetTool;
import com.example.dllo.colorfulchoice.nettool.URLValue;
import com.example.dllo.colorfulchoice.video.VideoBean;
import com.example.dllo.colorfulchoice.video.collect.CollectBean;
import com.example.dllo.colorfulchoice.video.collect.JudgeCollectChange;
import com.example.dllo.colorfulchoice.video.xlistview.XListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by ${马庭凯} on 16/9/23.
 */

public class CodeReuse {
    private CodeReuseAdapter codeReuseAdapter;
    private List<VideoBean.ResultBean> resultBeanList;
    private NetTool netTool;
    private Context mContext;
    private XListView xListView;
    private int addNum;

    public CodeReuse (Context mContext,XListView xListView,int addNum){
        this.mContext = mContext;
        this.xListView = xListView;
        this.addNum = addNum;
        resultBeanList = new ArrayList<>();
        netTool = new NetTool();
    }

    public void getBean(String url, String cookie) {
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
                    codeReuseAdapter = new CodeReuseAdapter(resultBeanList, mContext, R.layout.fragment_video_child_listview);
                    Log.d("Exercise", "resultBeanList.size():" + resultBeanList.size());
                    xListView.setAdapter(codeReuseAdapter);
                    codeReuseAdapter.notifyDataSetChanged();
                    xListView.setSelection(resultBeanList.size() - addNum);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onLoad() {
        xListView.stopRefresh();
        xListView.stopLoadMore();
    }

    public void onRefresh(String url){
        resultBeanList.clear();
        getBean(url, URLValue.VIDEO_COOKIE);
    }

    public void onLoadMore(String url){
        getBean(url,URLValue.VIDEO_COOKIE);
    }

    public class CodeReuseAdapter extends CommonAdapter<VideoBean.ResultBean> {
        private Handler mHandler;
        private Handler handler;
        private Context context;
        private CommonViewHolder videoViewHolder = null;

        public CodeReuseAdapter(List<VideoBean.ResultBean> beanList, Context context, int id) {
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
