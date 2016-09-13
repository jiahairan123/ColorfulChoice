package com.example.dllo.colorfulchoice.goodthing;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dllo.colorfulchoice.nettool.URLValue;

/**
 * Created by mayinling on 16/9/13.
 */
public class ThingsAdapter extends FragmentPagerAdapter{

    String[] strings = new String[]{"Daily", "首饰", "包袋", "鞋履", "Men", "配饰", "其他"};
    final String url[] = {URLValue.DAILYA_url, URLValue.JEWELLERY_URL, URLValue.BAGS_URL,
    URLValue.SHOES_URL, URLValue.MEN_URL, URLValue.ACC_URL, URLValue.OTHER_URL};

    Fragment[] fragments;

    public ThingsAdapter(FragmentManager fm) {
        super(fm);
        fragments = new Fragment[strings.length];
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            fragments[position] = new DailyFragment();
        } else {
            fragments[position] = NormalFragment.getInstance(url[position]);
        }
        return fragments[position];
    }



    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strings[position];
    }
}
