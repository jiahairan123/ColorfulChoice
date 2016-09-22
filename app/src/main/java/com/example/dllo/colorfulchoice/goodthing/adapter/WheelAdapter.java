package com.example.dllo.colorfulchoice.goodthing.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.goodthing.bean.TwoNormalBean;

/**
 * Created by mayinling on 16/9/21.
 */
public class WheelAdapter extends PagerAdapter{

    private Context context;
    private TwoNormalBean bean;

    public WheelAdapter(Context context) {
        this.context = context;

    }


    public void setBean(TwoNormalBean bean) {
        this.bean = bean;
        notifyDataSetChanged();

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.wheel, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.wheel_iv);
        int size = bean.getData().getCover_images().size();
        String s = bean.getData().getCover_images().get(position % size);
        Glide.with(this.context).load(s).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
