package com.example.dllo.colorfulchoice.me.logandregister;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseAty;

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
                } else if (passWordEt.length() == 0){
                    Toast.makeText(this, "请您输入密码", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(this, "恭喜您, 注册成功", Toast.LENGTH_SHORT).show();
                    intent.putExtra("count", countEt.getText().toString());
                    intent.putExtra("password", passWordEt.getText().toString());
                    setResult(17, intent);
                    finish();
                }

                    break;
        }
    }
}
