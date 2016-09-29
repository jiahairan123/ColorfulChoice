package com.example.dllo.colorfulchoice.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseAty;

/**
 * Created by ${马庭凯} on 16/9/23.
 */

public class WelcomeToColorfurChoiceActivity extends BaseAty {

    private TextView textView;
    private boolean intentjudge = false;
    private Intent intent;
    private CountDownTimer countDownTimer;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    @Override
    protected int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        textView = bindView(R.id.welcome_skip);
        setClick(textView);
        intent = new Intent(this, MainActivity.class);
    }

    @Override
    protected void initData() {
        sharedPreferences = this.getSharedPreferences("com.example.dllo.colorfulchoice.SETTING",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        boolean downTimerboolean = sharedPreferences.getBoolean("countDownTimer", false);
//        netTool.getNetData("http://111.202.15.139/gslb/bucket/get?app=xiaomi&ver=37000&pver=1&d=a1.go2yd.com", WelcomeBean.class, new NetTool.NetListener<WelcomeBean>() {
//            @Override
//            public void onSuccess(WelcomeBean welcomeBean) {
//                if (welcomeBean != null) {
//                    URLValue.VIDEO_COOKIE = welcomeBean.getCookie();
//                    Log.d("WelcomeToColorfurChoice", "welcomeBean.getUser_channels().size():" + welcomeBean.getUser_channels().size());
//                    URLValue.VIDEO_RECOMMEND = String.valueOf(welcomeBean.getUser_channels().get(1).getGroup_id());
//                    URLValue.VIDEO_ROFL = String.valueOf(welcomeBean.getUser_channels().get(1).getChannel_ids().get(0));
//                    URLValue.VIDEO_VARIETY = String.valueOf(welcomeBean.getUser_channels().get(1).getChannel_ids().get(1));
//                    URLValue.VIDEO_SMALLMOVIE = String.valueOf(welcomeBean.getUser_channels().get(1).getChannel_ids().get(2));
//                    URLValue.VIDEO_SISTER = String.valueOf(welcomeBean.getUser_channels().get(1).getChannel_ids().get(3));
//                    URLValue.VIDEO_SCENE = String.valueOf(welcomeBean.getUser_channels().get(1).getChannel_ids().get(4));
//                    URLValue.VIDEO_HUGGIES = String.valueOf(welcomeBean.getUser_channels().get(1).getChannel_ids().get(5));
//                    URLValue.VIDEO_ANIMALWORLD = String.valueOf(welcomeBean.getUser_channels().get(1).getChannel_ids().get(6));
//                    URLValue.VIDEO_LIFEVIDEO = String.valueOf(welcomeBean.getUser_channels().get(1).getChannel_ids().get(7));
//                    URLValue.VIDEO_EUPHONIOUS = String.valueOf(welcomeBean.getUser_channels().get(1).getChannel_ids().get(8));
//                    URLValue.VIDEO_SPORTS = String.valueOf(welcomeBean.getUser_channels().get(1).getChannel_ids().get(9));
//                    URLValue.VIDEO_CUTEPET = String.valueOf(welcomeBean.getUser_channels().get(1).getChannel_ids().get(10));
//                }
//        intentjudge = true;
//                finish();
//            }
//
//            @Override
//            public void onError(String errorMsg) {
//
//            }
//        });
        if(downTimerboolean){
            startActivity(intent);
            finish();
        }else {
            editor.putBoolean("countDownTimer", true);
            editor.commit();
            this.countDownTimer = new CountDownTimer(10000, 1000) {
                @Override
                public void onTick(long l) {
                    Log.d("WelcomeToColorfurChoice", "(int)(l / 1000):" + (int) (l / 1000));
                }

                @Override
                public void onFinish() {
                    startActivity(intent);
                    finish();
                }
            }.start();
        }
    }

    @Override
    public void onClick(View view) {
        startActivity(intent);
        countDownTimer.cancel();
        finish();
    }
}
