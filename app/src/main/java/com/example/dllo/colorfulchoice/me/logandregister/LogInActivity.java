package com.example.dllo.colorfulchoice.me.logandregister;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.activity.MainActivity;
import com.example.dllo.colorfulchoice.base.BaseAty;

/**
 * Coder: JiaHaiRan
 * created on 16/9/23 16:23
 */

public class LogInActivity extends BaseAty{

    private Button logInBtn, guestBtn, registerBtn;
    private EditText countEt, passwordEt;

    @Override
    protected int setLayout() {
        return R.layout.activity_log_in;
    }

    @Override
    protected void initView() {

        logInBtn = bindView(R.id.log_in_login_btn);
        guestBtn = bindView(R.id.log_in_guest_btn);
        registerBtn = bindView(R.id.log_in_register_btn);
        countEt = bindView(R.id.log_in_count_et);
        passwordEt = bindView(R.id.log_in_password_et);
        logInBtn.setOnClickListener(this);
        guestBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.log_in_login_btn :

                if (countEt.length() == 0) {
                    Toast.makeText(this, "请您输入账号", Toast.LENGTH_SHORT).show();
                } else if (passwordEt.length() == 0){
                    Toast.makeText(this, "请您输入密码", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "welcome to ColorChoice", Toast.LENGTH_SHORT).show();

                    Intent startIntent = new Intent(LogInActivity.this, MainActivity.class);
                    startActivity(startIntent);
                }


                //点击登录
                break;


            case R.id.log_in_guest_btn :

                //游客模式
                Intent guestIntent = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(guestIntent);
                break;


            case R.id.log_in_register_btn :

                //点击注册
                Intent registerIntent = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivityForResult(registerIntent, 111);

                break;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 111 :
                if (requestCode == 17) {
                    countEt.setText(data.getStringExtra("count"));
                    passwordEt.setText(data.getStringExtra("password"));
                }

                break;

            default:

                break;
        }

    }
}
