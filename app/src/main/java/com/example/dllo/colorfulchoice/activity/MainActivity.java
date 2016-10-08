package com.example.dllo.colorfulchoice.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseAty;
import com.example.dllo.colorfulchoice.designer.fragment.DesignerFragment;
import com.example.dllo.colorfulchoice.goodthing.fragment.GoodThingFragment;
import com.example.dllo.colorfulchoice.goodthing.fragment.NotmalTab;
import com.example.dllo.colorfulchoice.me.MeFragment;
import com.example.dllo.colorfulchoice.picture.PictureFragment;
import com.example.dllo.colorfulchoice.video.VideoFragment;

import cn.bmob.v3.Bmob;

public class MainActivity extends BaseAty implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroup;
    private FragmentManager fragmentManager;
    //定义一个变量 来标记是否退出
    private static boolean isExit = false;
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    //实现原理 当按下back的时候 会被 onKeyDown 捕获 判断是 back键 ,执行exit();
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;

        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {

        if (!isExit) {

            isExit = true;

            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();

            //利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);

        } else {
            finish();
            System.exit(0);
        }
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
        //Bmob 默认初始化在Application的onCreate方法里
        Bmob.initialize(this, "dbefaba740eb9abcd0ccf17f0079d1c1");
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
        switch (checkedId) {
            //画报 PictureFragment
            case R.id.main_radbtn_picture:
                fragmentTransaction.add(R.id.main_frame_layout, new PictureFragment());
                break;

            //有货 GoodThingFragment
            case R.id.main_radbtn_goodthing:
                fragmentTransaction.replace(R.id.main_frame_layout, new GoodThingFragment());
                break;

            //视频 VideoFragment
            case R.id.main_radbtn_video:
                fragmentTransaction.replace(R.id.main_frame_layout, new VideoFragment());
                break;

            //设计师
            case R.id.main_radbtn_designer:
                fragmentTransaction.replace(R.id.main_frame_layout, new DesignerFragment());
                break;

            case R.id.main_radbtn_me:
                fragmentTransaction.replace(R.id.main_frame_layout, new MeFragment());
                break;

        }
        fragmentTransaction.commit();

    }

}
