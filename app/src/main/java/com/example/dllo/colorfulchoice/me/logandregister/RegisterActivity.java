package com.example.dllo.colorfulchoice.me.logandregister;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseAty;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Coder: JiaHaiRan
 * created on 16/9/23 16:23
 * <p>
 * 注册界面
 */

public class RegisterActivity extends BaseAty {
    private EditText countEt, passWordEt;
    private Button registerBtn;
    private Intent intent;


    @Override
    protected int setLayout() {

        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

        countEt = bindView(R.id.register_count_et);
        passWordEt = bindView(R.id.register_pw_et);

        registerBtn = bindView(R.id.register_register_btn);
        registerBtn.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        intent = new Intent(RegisterActivity.this, LogInActivity.class);

        switch (v.getId()) {
            case R.id.register_register_btn:

                if (countEt.length() == 0) {
                    Toast.makeText(this, "账号为空,请您输入", Toast.LENGTH_SHORT).show();
                } else if (passWordEt.length() == 0) {
                    Toast.makeText(this, "请您输入密码", Toast.LENGTH_SHORT).show();
                } else {
                    BmobUser user = new BmobUser();
                    user.setUsername(countEt.getText().toString());
                    user.setPassword(passWordEt.getText().toString());

                    user.signUp(new SaveListener<Object>() {
                        @Override
                        public void done(Object o, BmobException e) {
                            if (e == null) {
                                Log.d("RegisterActivity", "恭喜您, 注册成功");
                                intent.putExtra("count", countEt.getText().toString());
                                intent.putExtra("password", passWordEt.getText().toString());
                                setResult(17, intent);

                            } else {
                                Toast.makeText(RegisterActivity.this, "用户名已经存在", Toast.LENGTH_LONG).show();
                            }

                            finish();
                        }
                    });


                }

                break;
        }
    }
}
