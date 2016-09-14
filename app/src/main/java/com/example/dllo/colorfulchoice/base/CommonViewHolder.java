package com.example.dllo.colorfulchoice.base;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Coder: JiaHaiRan
 * created on 16/9/13 14:38
 * 一个通用的ViewHolder 适用于所有的ViewHolder
 */

public class CommonViewHolder {
    //SparseArray  google key值强制是int类型的HashMap
    // Android特有的 效率高
    private SparseArray<View> views;

    private View convertView; //行布局

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    //返回行布局
    public View getConvertView() {
        return convertView;
    }


    public static CommonViewHolder getHolder(View convertView, LayoutInflater inflater, int id, ViewGroup parent) {
        CommonViewHolder viewHolder = null;

        if (convertView == null) {
            View view = inflater.inflate(id, parent, false);
            viewHolder = new CommonViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (CommonViewHolder) convertView.getTag();
        }
        return viewHolder;
    }


    public CommonViewHolder(View convertView) {
        views = new SparseArray<>();
        this.convertView = convertView;
        this.convertView.setTag(this);
    }

    //通过id来获得行布局中指定的view的方法
    //id view 的id
    //return 该id 对应的view

    public <T extends View> T getView(int id) {
        View view = views.get(id);
        if (view == null) {
            //执行findViewById 找到这个组件
            view = convertView.findViewById(id);
            //然后放到views里
            views.put(id, view);
        }
        return (T) view;
    }

    //设置文字
    public void setText(int id, String text) {
        TextView textView = getView(id);
        textView.setText(text);
    }

    public void setImage(int id,int resid){
        ImageView imageView = getView(id);
        imageView.setBackgroundResource(resid);
    }

}
