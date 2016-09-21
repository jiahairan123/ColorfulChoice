package com.example.dllo.colorfulchoice.designer;

import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Coder: JiaHaiRan
 * created on 16/9/21 21:05
 */

public class TurnPagerAdapter extends PagerAdapter {


    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


}
