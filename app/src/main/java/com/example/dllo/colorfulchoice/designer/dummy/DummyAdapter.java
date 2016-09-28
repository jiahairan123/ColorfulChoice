package com.example.dllo.colorfulchoice.designer.dummy;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.MyApp;
import com.example.dllo.colorfulchoice.database.DBTools;
import com.example.dllo.colorfulchoice.database.DesignerSign;
import com.example.dllo.colorfulchoice.designer.bean.DesignerBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Coder: JiaHaiRan
 * created on 16/9/18 14:01
 */

public class DummyAdapter extends BaseAdapter {

    DesignerBean designerBean;


    public void setDesignerBean(DesignerBean designerBean) {
        this.designerBean = designerBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (designerBean == null) {
            return 0;
        }
        return designerBean == null ? 0 : designerBean.getData().getDesigners().size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        Viewholder viewholder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(MyApp.getContext()).inflate(R.layout.item_designer_dummy, null);
            viewholder = new Viewholder(convertView);
            convertView.setTag(viewholder);

        } else {
            viewholder = (Viewholder) convertView.getTag();
        }
        DesignerBean.DataBean.DesignersBean bean = designerBean.getData().getDesigners().get(position);

        viewholder.name.setText(bean.getName());
        viewholder.position.setText(bean.getLabel());
        Glide.with(MyApp.getContext()).load(bean.getAvatar_url()).into(viewholder.personImg);
        Glide.with(MyApp.getContext()).load(bean.getRecommend_images().get(0)).into(viewholder.contentImg);

        viewholder.button.setOnClickListener(new MyOnClick(bean) {
            @Override
            public void onClick(View v) {
                DesignerSign designerSign = new DesignerSign();
                designerSign.setName(bean.getName());
                Log.d("Sysout", bean.getName());
                designerSign.setLabel(bean.getLabel());
                designerSign.setPersonImgUrl(bean.getAvatar_url());
                designerSign.setImgUrl(bean.getRecommend_images().get(0));
                DBTools.getInstance(MyApp.getContext()).insertSignDesigner(designerSign);
            }
        });



        return convertView;
    }

    abstract class MyOnClick implements View.OnClickListener{
        protected DesignerBean.DataBean.DesignersBean bean;

        public MyOnClick(DesignerBean.DataBean.DesignersBean bean) {
            this.bean = bean;
        }
    }

    class Viewholder {

        TextView name, position;
        ImageView contentImg;
        CircleImageView personImg;
        Button button;


        public Viewholder(View view) {

            personImg = (CircleImageView) view.findViewById(R.id.item_designer_person_iv);
            contentImg = (ImageView) view.findViewById(R.id.item_designer_content_iv);
            name = (TextView) view.findViewById(R.id.item_designer_person_name);
            position = (TextView) view.findViewById(R.id.item_designer_person_position);
            button = (Button) view.findViewById(R.id.item_designer_btn);

        }
    }


}


