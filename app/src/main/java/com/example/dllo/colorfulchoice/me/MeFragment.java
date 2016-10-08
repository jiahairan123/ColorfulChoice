package com.example.dllo.colorfulchoice.me;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseFragment;
import com.example.dllo.colorfulchoice.base.MyApp;
import com.example.dllo.colorfulchoice.me.logandregister.CollActivity;
import com.example.dllo.colorfulchoice.me.logandregister.LogInActivity;

import cn.bmob.v3.BmobUser;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Coder: JiaHaiRan
 * created on 16/9/13 11:36
 */

public class MeFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout myPicture, signDesigner, wishList, newsCenter, adviceAndQuestion;
    private DrawerLayout mDrawerLayout;
    private FrameLayout dummyFrameLayout;
    private CircleImageView civ, clickLogin;
    private BmobUser currentUser;
    private View loginMode, guestMode;
    private Button exitBtn, loginBtn;
    private TextView countName, noName;
    private RelativeLayout relativeLayout;

    @Override
    protected int setLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {
        //占位布局
        dummyFrameLayout = bindView(R.id.me_frame_layout_dummy);
        //我的画报
        myPicture = bindView(R.id.me_my_picture);
        //喜欢的设计师
        signDesigner = bindView(R.id.me_sign_designer);
        //愿望清单
        wishList = bindView(R.id.me_wish_list);
        //信息中心
        newsCenter = bindView(R.id.me_news_center);
        //建议与意见
        adviceAndQuestion = bindView(R.id.me_advice_question);

        //账号已经登录模式的
        loginMode = LayoutInflater.from(mContext).inflate(R.layout.login_ll, null);
        exitBtn = (Button) loginMode.findViewById(R.id.exit_count);
        countName = (TextView) loginMode.findViewById(R.id.count_name);
        exitBtn.setOnClickListener(this);

        //未登录状态
        guestMode = LayoutInflater.from(MyApp.getContext()).inflate(R.layout.not_login_ll, null);
        loginBtn = (Button) guestMode.findViewById(R.id.log_your_count);
        noName = (TextView) guestMode.findViewById(R.id.no_count_name);
        clickLogin = (CircleImageView) guestMode.findViewById(R.id.click_to_log_in);
        clickLogin.setOnClickListener(this);
        loginBtn.setOnClickListener(this);

        relativeLayout = bindView(R.id.collection_rl);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 查看我的心愿单
                BmobUser user = BmobUser.getCurrentUser();
                if (user == null){
                    Toast.makeText(mContext, "请先登录", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getContext(), CollActivity.class);

                    startActivity(intent);
                }

            }
        });

    }

    @Override
    protected void initData() {

        //获取当前的用户
        currentUser = BmobUser.getCurrentUser();
        if (currentUser != null) {
            dummyFrameLayout.removeAllViews();
            dummyFrameLayout.addView(loginMode);
            countName.setText(currentUser.getUsername());
        } else {
            dummyFrameLayout.removeAllViews();
            dummyFrameLayout.addView(guestMode);
        }
    }

    @Override
    public void onResume() {

        super.onResume();
        currentUser = BmobUser.getCurrentUser();
        if (currentUser != null) {
            dummyFrameLayout.removeAllViews();
            dummyFrameLayout.addView(loginMode);
            Log.d("MeFragment", currentUser.getUsername().toString());
            countName.setText(currentUser.getUsername());
        } else {
            dummyFrameLayout.removeAllViews();
            dummyFrameLayout.addView(guestMode);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.log_your_count:
                Intent logInIntent = new Intent(getActivity(), LogInActivity.class);
                getActivity().startActivity(logInIntent);
                break;


            case R.id.click_to_log_in:
                Intent logInIntent1 = new Intent(getActivity(), LogInActivity.class);
                getActivity().startActivity(logInIntent1);
                break;


            case R.id.exit_count :
                BmobUser.logOut();

                BmobUser user1 = BmobUser.getCurrentUser();
                if (user1 == null) {
                    dummyFrameLayout.removeAllViews();
                    dummyFrameLayout.addView(guestMode);

                } else {
                    dummyFrameLayout.removeAllViews();
                    dummyFrameLayout.addView(loginMode);
                    countName.setText(currentUser.getUsername());
                }
                break;
        }
    }
}
