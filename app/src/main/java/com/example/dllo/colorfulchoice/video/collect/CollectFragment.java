package com.example.dllo.colorfulchoice.video.collect;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseFragment;
import com.example.dllo.colorfulchoice.base.CommonAdapter;
import com.example.dllo.colorfulchoice.base.CommonViewHolder;
import com.example.dllo.colorfulchoice.database.DBTools;
import com.example.dllo.colorfulchoice.database.DBVideoView;
import com.example.dllo.colorfulchoice.video.xlistview.XListView;

import java.util.List;

import cn.bmob.v3.BmobUser;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by ${马庭凯} on 16/9/29.
 */

public class CollectFragment extends BaseFragment{
    private XListView xListView;
    private List<DBVideoView> dbVideoViewList;

    @Override
    protected int setLayout() {
        return R.layout.fragment_video_child;
    }

    @Override
    protected void initView() {
        xListView = bindView(R.id.fragment_video_child_list_view);
    }

    @Override
    protected void initData() {
        if(BmobUser.getCurrentUser() != null) {
            dbVideoViewList = DBTools.getInstance().queryVideoView(BmobUser.getCurrentUser().getUsername());
        }
        xListView.setAdapter(new CollectAdapter(dbVideoViewList,mContext,R.layout.fragment_video_child_listview));
    }

    private class CollectAdapter extends CommonAdapter<DBVideoView>{
        private int playPosition = -1;
        private CommonViewHolder videoViewHolder = null;
        private Context context;
        public CollectAdapter(List<DBVideoView> beanList, Context context, int id) {
            super(beanList, context, id);
            this.context = context;
        }

        @Override
        public void setData(final DBVideoView dbVideoView, final CommonViewHolder viewHolder) {
            viewHolder.setText(R.id.video_title, dbVideoView.getTitle());
            viewHolder.setImage(R.id.video_picture, dbVideoView.getImage());
            viewHolder.setBackground(R.id.video_collect, R.mipmap.star);
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
                    Log.d("RecommendAdapter", dbVideoView.getVideoUrl());
                    videoView.setVideoPath(dbVideoView.getVideoUrl());
                    videoView.setMediaController(new MediaController(context));
                    videoView.requestFocus();
                }
            });

            viewHolder.getView(R.id.video_collect).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DBTools.getInstance().deleteVideoView(dbVideoView);
                    dbVideoViewList.remove(dbVideoView);
                    notifyDataSetChanged();
                }
            });
        }
    }

}
