package com.example.dllo.colorfulchoice.me;

import android.support.v4.widget.DrawerLayout;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseFragment;

/**
 * Coder: JiaHaiRan
 * created on 16/9/13 11:36
 */

public class MeFragment extends BaseFragment {
    private LinearLayout myPicture, signDesigner, wishList, newsCenter, adviceAndQuestion;
    private DrawerLayout mDrawerLayout;
    private FrameLayout mFrameLayout;
    //我 界面
    //注意命名规范
    @Override
    protected int setLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {
        myPicture = bindView(R.id.me_my_picture);
        signDesigner = bindView(R.id.me_sign_designer);
        wishList = bindView(R.id.me_wish_list);
        newsCenter = bindView(R.id.me_news_center);
        adviceAndQuestion = bindView(R.id.me_advice_question);

//        mDrawerLayout = bindView(R.id.me_drawer_layout);
//        mFrameLayout = bindView(R.id.me_right_framelayout);
//        //DrawerLayout手势滑动
//        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
    }

    @Override
    protected void initData() {

    }
}
