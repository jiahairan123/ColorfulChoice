package com.example.dllo.colorfulchoice.goodthing;

import android.os.Bundle;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseFragment;

/**
 * Created by mayinling on 16/9/13.
 * 复用的fragment
 */
public class NormalFragment extends BaseFragment {

    public static NormalFragment getInstance(String tab){
        Bundle bundle = new Bundle();
        bundle.putString("url", tab);
        NormalFragment fragment = new NormalFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_normal;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        String url = bundle.getString("url");
    }
}
