package com.example.dllo.colorfulchoice.designer.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dllo.colorfulchoice.designer.dummy.DesignerDummyFragment;
import com.example.dllo.colorfulchoice.nettool.URLValue;

/**
 * Coder: JiaHaiRan
 * created on 16/9/18 10:41
 */

public class DesignerAdapter extends FragmentPagerAdapter {

    String[] designerTab = new String[]{"推荐", "最受欢迎", "独立设计师", "大牌设计师"};
    final String[] urls = new String[]{ URLValue.DESIGNER_SUGGEST_URL, URLValue.DESIGNER_MOSTPOP_URL, URLValue.DESIGNER_SELFDESIGN_URL, URLValue.DESIGNER_BREADDESIGN_URL};
    Fragment[] fragments;

    public DesignerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new Fragment[designerTab.length];
    }

    /**
     *
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */

    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        fragments[position] =  DesignerDummyFragment.getInstance(urls[position]);
        return fragments[position];
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return designerTab.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return designerTab[position];
    }
}
