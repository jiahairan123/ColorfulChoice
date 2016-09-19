package com.example.dllo.colorfulchoice.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseAty;
import com.example.dllo.colorfulchoice.designer.DesignerFragment;
import com.example.dllo.colorfulchoice.goodthing.fragment.GoodThingFragment;
import com.example.dllo.colorfulchoice.goodthing.fragment.NotmalTab;
import com.example.dllo.colorfulchoice.me.MeFragment;
import com.example.dllo.colorfulchoice.picture.PictureFragment;
import com.example.dllo.colorfulchoice.video.VideoFragment;

public class MainActivity extends BaseAty implements RadioGroup.OnCheckedChangeListener {


    private RadioGroup radioGroup;
    private FragmentManager fragmentManager;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //五个radio button  == 画报 picture  / 有物 good thing  /视频 video / 设计师 designer / 我 me
        radioGroup = bindView(R.id.main_radio_group);
        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.check(R.id.main_radbtn_picture); //默认按钮选择 在画报
    }

    @Override
    protected void initData() {
        NotmalTab.getInstance();
    }

    @Override
    public void onClick(View v) {
        //这里写点击事件
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (checkedId){
            //画报 PictureFragment
            case R.id.main_radbtn_picture :
                fragmentTransaction.add(R.id.main_frame_layout, new PictureFragment());
                break;

            //有货 GoodThingFragment
            case R.id.main_radbtn_goodthing :
                fragmentTransaction.replace(R.id.main_frame_layout, new GoodThingFragment());
                break;

            //视频 VideoFragment
            case R.id.main_radbtn_video :
                fragmentTransaction.replace(R.id.main_frame_layout, new VideoFragment());
                break;

            //设计师
            case R.id.main_radbtn_designer :
                fragmentTransaction.replace(R.id.main_frame_layout, new DesignerFragment());
                break;

            case R.id.main_radbtn_me :
                fragmentTransaction.replace(R.id.main_frame_layout, new MeFragment());
                break;


        }
        fragmentTransaction.commit();

    }


}
