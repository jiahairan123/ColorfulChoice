package com.example.dllo.colorfulchoice.video.codereuse;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.CommonAdapter;
import com.example.dllo.colorfulchoice.base.CommonViewHolder;
import com.example.dllo.colorfulchoice.database.DBVideoView;
import com.example.dllo.colorfulchoice.nettool.NetTool;
import com.example.dllo.colorfulchoice.nettool.URLValue;
import com.example.dllo.colorfulchoice.video.VideoBean;
import com.example.dllo.colorfulchoice.video.collect.CollectBean;
import com.example.dllo.colorfulchoice.video.collect.JudgeCollectChange;
import com.example.dllo.colorfulchoice.video.xlistview.XListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
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
    private int addNum = 0;

    public CodeReuse(Context mContext, XListView xListView) {
        this.mContext = mContext;
        this.xListView = xListView;
        resultBeanList = new ArrayList<>();
        netTool = new NetTool();
    }

    public void setAddNum(int addNum) {
        this.addNum = addNum;
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

    public void onRefresh(String url, int addNum) {
        this.addNum = addNum;
        resultBeanList.clear();
        getBean(url, URLValue.VIDEO_COOKIE);
    }

    public void onLoadMore(String url, int addNum) {
        this.addNum = addNum;
        getBean(url, URLValue.VIDEO_COOKIE);
    }

    public class CodeReuseAdapter extends CommonAdapter<VideoBean.ResultBean> {
        private Handler mHandler;
        private Handler handler;
        private Context context;
        private int playPosition = -1;
        private CommonViewHolder videoViewHolder = null;
        private List<CommonViewHolder> viewHolderList;

        public CodeReuseAdapter(List<VideoBean.ResultBean> beanList, Context context, int id) {
            super(beanList, context, id);
            this.context = context;
            viewHolderList = new ArrayList<>();
        }

        @Override
        public void setData(final VideoBean.ResultBean resultBean, final CommonViewHolder viewHolder) {
            viewHolder.setImage(R.id.video_picture, resultBean.getImage());
            Log.d("CodeReuseAdapter", resultBean.getImage());
            viewHolder.setText(R.id.video_title, resultBean.getTitle());
//
//            if(viewHolderList.size() == 0){
//                viewHolderList.add(viewHolder);
//            }else {
//                int i = 0;
//                for (; i < viewHolderList.size(); i++) {
//                    if(viewHolderList.get(i) == viewHolder){
//                        break;
//                    }
//                }
//                if(i == viewHolderList.size()){
//                    viewHolderList.add(viewHolder);
//                }
//            }
//            Log.d("CodeReuseAdapter", "当前界面的最后item的位置信息是" + xListView.getLastVisiblePosition());
//            Log.d("CodeReuseAdapter", "当前界面的最初item的位置信息是" + xListView.getFirstVisiblePosition());
//            Log.d("CodeReuseAdapter", "该listView中共new了" + viewHolderList.size() + "个item");

//            Log.d("CodeReuseAdapter", "父类布局的id是" + viewHolder.getView(R.id.rl_up));

            if (videoViewHolder != null) {
//                Log.d("CodeReuseAdapter", "滑动时的位置信息为" + videoViewHolder.getPosition());
                if (playPosition < xListView.getFirstVisiblePosition() || playPosition > (xListView.getLastVisiblePosition())) {
                    Log.d("CodeReuseAdapter", "每一次的刷新都会运行到这里吗");
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
                    playPosition = videoViewHolder.getPosition();
                    Log.d("CodeReuseAdapter", "正在播放的视频的位置信息" + videoViewHolder.getPosition());
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
                    if(BmobUser.getCurrentUser() != null){
                        Toast.makeText(mContext, "您还没有登录,请先登录然后在收藏", Toast.LENGTH_SHORT).show();
                    }else {
                        handler = new Handler(new Handler.Callback() {
                            @Override
                            public boolean handleMessage(Message message) {
                                switch (message.what) {
                                    case 0:
                                        DBVideoView dbVideoView = new DBVideoView();
                                        dbVideoView.setUserName(BmobUser.getCurrentUser().getUsername());
                                        dbVideoView.setItemId(resultBean.getItemid());
                                        dbVideoView.setTitle(resultBean.getTitle());
                                        dbVideoView.setDate(resultBean.getDate());
                                        dbVideoView.setImage(resultBean.getImage());
                                        dbVideoView.setVideoUrl(resultBean.getVideo_url());
                                        CollectBean.getInstance().setResultBeanList(dbVideoView);
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
                }
            });
        }
    }
}
