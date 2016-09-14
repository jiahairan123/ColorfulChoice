package com.example.dllo.colorfulchoice.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Coder: JiaHaiRan
 * created on 16/9/13 15:48
 * 通用的adapter
 */

public abstract class CommonAdapter<T> extends BaseAdapter {

    private List<T> beanList;
    private LayoutInflater inflater;
    private int convertViewId;
    private List<Integer> integerList;

    public CommonAdapter(List<T> beanList, Context context, int id,List<Integer> integerList){
        this.beanList = beanList;
        inflater = LayoutInflater.from(context);
        convertViewId = id;
        this.integerList = integerList;
    }

    @Override
    public int getCount() {
        return integerList == null ? 0 : integerList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CommonViewHolder commonViewHolder = CommonViewHolder.getHolder(convertView,inflater,convertViewId,parent);
        commonViewHolder.setPosition(position);
        setData(beanList.get(integerList.get(position)), commonViewHolder);
        return commonViewHolder.getConvertView();

    }

    public abstract void setData(T t, CommonViewHolder viewHolder);
}
