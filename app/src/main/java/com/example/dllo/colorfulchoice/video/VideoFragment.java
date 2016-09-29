package com.example.dllo.colorfulchoice.video;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseFragment;
import com.example.dllo.colorfulchoice.database.DBTools;
import com.example.dllo.colorfulchoice.database.DBVideoView;
import com.example.dllo.colorfulchoice.video.animalworld.AnimalWorldFragment;
import com.example.dllo.colorfulchoice.video.collect.CollectBean;
import com.example.dllo.colorfulchoice.video.cutepet.CutePetFragment;
import com.example.dllo.colorfulchoice.video.euphonious.EuphoniousFragment;
import com.example.dllo.colorfulchoice.video.huggies.HuggiesFragment;
import com.example.dllo.colorfulchoice.video.lifevideo.LifeVideoFragment;
import com.example.dllo.colorfulchoice.video.recommend.RecommendFragment;
import com.example.dllo.colorfulchoice.video.rofl.RoflFragment;
import com.example.dllo.colorfulchoice.video.scene.SceneFragment;
import com.example.dllo.colorfulchoice.video.sister.SisterFragment;
import com.example.dllo.colorfulchoice.video.smallmovie.SmallMovieFragment;
import com.example.dllo.colorfulchoice.video.sports.SportsFragment;
import com.example.dllo.colorfulchoice.video.variety.VarietyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Coder: JiaHaiRan
 * created on 16/9/13 11:32
 */

public class VideoFragment extends BaseFragment implements View.OnClickListener{
    // 视频 fragment
    private TabLayout tbVideo;
    private ViewPager vpVideo;
    private ImageView btnPoppup;
    private List<Fragment> fragmentList;
    private List<String> stringList;
    private PopupWindow popupWindow;
    private static int judgePosition = 0;

    @Override
    protected int setLayout() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView() {
        vpVideo = bindView(R.id.vp_video);
        tbVideo = bindView(R.id.tb_video);
        btnPoppup = bindView(R.id.btn_poppup_windows);
        btnPoppup.setOnClickListener(this);
        fragmentList = new ArrayList<>();
        stringList = new ArrayList<>();
        popupWindow = new PopupWindow(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void initData() {
        fragmentList.add(new RecommendFragment());
        fragmentList.add(new RoflFragment());
        fragmentList.add(new VarietyFragment());
        fragmentList.add(new SmallMovieFragment());
        fragmentList.add(new SisterFragment());
        fragmentList.add(new SceneFragment());
        fragmentList.add(new HuggiesFragment());
        fragmentList.add(new AnimalWorldFragment());
        fragmentList.add(new LifeVideoFragment());
        fragmentList.add(new EuphoniousFragment());
        fragmentList.add(new SportsFragment());
        fragmentList.add(new CutePetFragment());
        stringList.add("推荐");
        stringList.add("爆笑");
        stringList.add("综艺范");
        stringList.add("微电影");
        stringList.add("妹纸");
        stringList.add("现场");
        stringList.add("猎奇");
        stringList.add("动物世界");
        stringList.add("生活视频");
        stringList.add("悦耳");
        stringList.add("运动");
        stringList.add("萌宠萌娃");
        MyViewPager myViewPager = new MyViewPager(getChildFragmentManager());
        vpVideo.setAdapter(myViewPager);
        tbVideo.setupWithViewPager(vpVideo);
        tbVideo.setSelectedTabIndicatorColor(0x00);
        tbVideo.setTabTextColors(Color.BLACK,0xFFFF4500);
        tabPopupWin();
        if(popupWindow != null && popupWindow.isShowing()){
            popupWindow.dismiss();
            return;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        List<DBVideoView> dbVideoViewList = DBTools.getInstance().queryVideoView();
        CollectBean.getInstance().setResultBeanList(dbVideoViewList);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_poppup_windows:
                if(popupWindow != null && popupWindow.isShowing()){
                    popupWindow.dismiss();
                    view.setBackgroundResource(R.mipmap.poppup_start);
                    return;
                }else {
                    tabPopupWin();
                    popupWindow.showAsDropDown(view,0,5);
                    view.setBackgroundResource(R.mipmap.poppup_end);
                    Log.d("VideoFragment", "点击了一次popupwindows");
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(popupWindow != null && popupWindow.isShowing()){
            popupWindow.dismiss();
            btnPoppup.setBackgroundResource(R.mipmap.poppup_start);
        }
        DBTools.getInstance().insertVideoView(CollectBean.getInstance().getResultBeanList());
    }

    private void tabPopupWin() {
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.video_popup_tab,null,false);
        RecyclerView recycler = (RecyclerView) tabView.findViewById(R.id.popup_recycle_view);
        final MyRecyclerAdapter recyclerAdapter = new MyRecyclerAdapter();
        recycler.setAdapter(recyclerAdapter);
        GridLayoutManager manager = new GridLayoutManager(mContext,3);
        recycler.setLayoutManager(manager);
        popupWindow.setContentView(tabView);
//        popupWindow.setOutsideTouchable(true);
        vpVideo.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                judgePosition = position;
                recyclerAdapter.notifyDataSetChanged();
                if(popupWindow != null && popupWindow.isShowing()){
                    popupWindow.dismiss();
                    btnPoppup.setBackgroundResource(R.mipmap.poppup_start);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.PopViewHolder>{
        @Override
        public MyRecyclerAdapter.PopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View popView = LayoutInflater.from(mContext).inflate(R.layout.video_popup_tab_btn,parent,false);
            return new PopViewHolder(popView);
        }

        @Override
        public void onBindViewHolder(MyRecyclerAdapter.PopViewHolder holder, final int position) {
            if(judgePosition == position){
                holder.button.setTextColor(0xFFFF4500);
            }else {
                holder.button.setTextColor(Color.BLACK);
            }
            holder.button.setText(stringList.get(position));
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vpVideo.setCurrentItem(position);
                    judgePosition = position;
                    notifyDataSetChanged();
                    popupWindow.dismiss();
                    btnPoppup.setBackgroundResource(R.mipmap.poppup_start);
                }
            });
        }

        @Override
        public int getItemCount() {
            Log.d("MyRecyclerAdapter", "stringList.size():" + stringList.size());
            return stringList.size();
        }

        public class PopViewHolder extends RecyclerView.ViewHolder {
            private Button button;
            public PopViewHolder(View itemView) {
                super(itemView);
                button = (Button) itemView.findViewById(R.id.popup_recycle_view_btn);
            }
        }
    }

    public class MyViewPager extends FragmentPagerAdapter {
        public MyViewPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return stringList.get(position);
        }
    }

}
