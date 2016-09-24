package com.example.dllo.colorfulchoice.me;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseFragment;
import com.example.dllo.colorfulchoice.me.logandregister.LogInActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Coder: JiaHaiRan
 * created on 16/9/13 11:36
 */

public class MeFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout myPicture, signDesigner, wishList, newsCenter, adviceAndQuestion;
    private DrawerLayout mDrawerLayout;
    private FrameLayout mFrameLayout;
    private CircleImageView civ;
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

        civ = bindView(R.id.me_circle_btn);
        civ.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_circle_btn :

                Intent logInIntent = new Intent(getActivity(), LogInActivity.class);
                getActivity().startActivity(logInIntent);
        }
    }
}
