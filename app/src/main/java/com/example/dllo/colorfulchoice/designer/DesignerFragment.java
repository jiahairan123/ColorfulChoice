package com.example.dllo.colorfulchoice.designer;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseFragment;
import com.example.dllo.colorfulchoice.goodthing.DailyBean;

/**
 * Coder: JiaHaiRan
 * created on 16/9/13 11:34
 */

public class DesignerFragment extends BaseFragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected int setLayout() {
        return R.layout.fragment_designer;
    }

    @Override
    protected void initView() {
        tabLayout = bindView(R.id.designer_tab_layout);
        viewPager = bindView(R.id.designer_vp);
        tabLayout.setupWithViewPager(viewPager);
        DesignerAdapter adapter = new DesignerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setTabTextColors(0xffd6d6d6, Color.WHITE);
    }

    @Override
    protected void initData() {

    }
}
