package com.example.dllo.colorfulchoice.me.logandregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.MyApp;
import com.example.dllo.colorfulchoice.database.GoodThings;
import com.example.dllo.colorfulchoice.me.CollBean;

import java.util.ArrayList;

/**
 * Created by mayinling on 16/10/8.
 */
public class CollAdapter extends BaseAdapter{

    private ArrayList<GoodThings> goodThingses;


    public void setGoodThingses(ArrayList<GoodThings> goodThingses) {
        this.goodThingses = goodThingses;
    }

    @Override
    public int getCount() {
        return goodThingses != null ? goodThingses.size() : 0;
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
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(MyApp.getContext()).inflate(R.layout.item_coll, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(MyApp.getContext()).load(goodThingses.get(position).getImgUrl()).into(viewHolder.imageView);
        return convertView;
    }

    private class ViewHolder{
        public ImageView imageView;
        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.item_coll_image);
        }
    }

}
