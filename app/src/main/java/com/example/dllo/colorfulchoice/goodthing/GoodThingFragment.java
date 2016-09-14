package com.example.dllo.colorfulchoice.goodthing;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseFragment;

/**
 * Coder: JiaHaiRan
 * created on 16/9/13 11:30
 */

public class GoodThingFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    //有物
    @Override
    protected int setLayout() {
        return R.layout.fragment_goodthing;
    }

    @Override
    protected void initView() {
        tabLayout = bindView(R.id.goodthing_tablayout);
        viewPager = bindView(R.id.goodthing_viewpager);
        ThingsAdapter adapter = new ThingsAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        // 滑动条颜色
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        // 标题字体大小
        tabLayout.setTabTextColors(0xffd6d6d6,Color.WHITE);
        // 滑动条宽度
        tabLayout.setSelectedTabIndicatorHeight(2);
    }

    @Override
    protected void initData() {


    }
}
