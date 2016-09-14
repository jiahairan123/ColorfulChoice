package com.example.dllo.colorfulchoice.video;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseFragment;
import com.example.dllo.colorfulchoice.video.animalworld.AnimalWorldFragment;
import com.example.dllo.colorfulchoice.video.recommend.RecommendFragment;
import com.example.dllo.colorfulchoice.video.cutepet.CutePetFragment;
import com.example.dllo.colorfulchoice.video.euphonious.EuphoniousFragment;
import com.example.dllo.colorfulchoice.video.huggies.HuggiesFragment;
import com.example.dllo.colorfulchoice.video.lifevideo.LifeVideoFragment;
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

public class VideoFragment extends BaseFragment {
    // 视频 fragment
    private TabLayout tbVideo;
    private ViewPager vpVideo;
    private List<Fragment> fragmentList;
    private List<String> stringList;

    @Override
    protected int setLayout() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView() {
        vpVideo = bindView(R.id.vp_video);
        tbVideo = bindView(R.id.tb_video);
        fragmentList = new ArrayList<>();
        stringList = new ArrayList<>();
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
