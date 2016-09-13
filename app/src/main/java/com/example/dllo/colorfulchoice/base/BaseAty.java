package com.example.dllo.colorfulchoice.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.dllo.colorfulchoice.nettool.NetTool;


/**
 * Coder: JiaHaiRan
 * created on 16/9/12 14:22
 */

public abstract class BaseAty extends AppCompatActivity implements View.OnClickListener {

    protected NetTool netTool;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        netTool = new NetTool();

        //严谨的写法
        if (setLayout() != 0) {
            setContentView(setLayout());
        } else {
            Log.e("BaseAty", "Activity" + this.getClass().getSimpleName() + "没有绑定布局");
        }

        initView();
        initData();
    }

    //protected 他的子类 同包内 可以访问
    protected abstract int setLayout();
    protected abstract void initView();
    protected abstract void initData();

    //简化findViewById
    protected <T extends View> T bindView (int id){
        return (T) findViewById(id);
    }

    //设置点击事件  个数不是固定的
    protected void setClick(View ... views){
        for (View view : views) {
            view.setOnClickListener(this);

        }
    }

}
