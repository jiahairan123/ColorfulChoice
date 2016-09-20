package com.example.dllo.colorfulchoice.goodthing.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseFragment;
import com.example.dllo.colorfulchoice.goodthing.eventbus.EventBusPosition;
import com.example.dllo.colorfulchoice.goodthing.adapter.ThingsAdapter;

import org.greenrobot.eventbus.EventBus;

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

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 可以获取每个tab的位置 -- tab.getPosition()
                // 使用eventbus发布一个事件
                int position = tab.getPosition();
                EventBusPosition eventBusPosition = new EventBusPosition();
                eventBusPosition.setPosition(position);
                EventBus.getDefault().post(eventBusPosition);
                Log.d("GoodThingFragment", "position:" + position);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
