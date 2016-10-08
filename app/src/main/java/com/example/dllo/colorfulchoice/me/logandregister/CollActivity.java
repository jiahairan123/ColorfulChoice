package com.example.dllo.colorfulchoice.me.logandregister;

import android.view.View;
import android.widget.GridView;

import com.example.dllo.colorfulchoice.R;
import com.example.dllo.colorfulchoice.base.BaseAty;
import com.example.dllo.colorfulchoice.database.DBTools;
import com.example.dllo.colorfulchoice.database.GoodThings;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import rx.functions.Action1;

/**
 * Created by mayinling on 16/10/8.
 */
public class CollActivity extends BaseAty{

    private GridView gridView;
    BmobUser bmobUser;

    @Override
    protected int setLayout() {
        return R.layout.activity_coll;
    }

    @Override
    protected void initView() {
        gridView = bindView(R.id.coll_activity_gridview);
    }

    @Override
    protected void initData() {

        bmobUser = BmobUser.getCurrentUser();

        DBTools.getInstance().queryColl(bmobUser.getUsername(), new Action1<List<GoodThings>>() {
            @Override
            public void call(List<GoodThings> goodThingses) {
                CollAdapter adapter = new CollAdapter();
                adapter.setGoodThingses((ArrayList<GoodThings>) goodThingses);
                gridView.setAdapter(adapter);
            }
        });

    }


    @Override
    public void onClick(View v) {

    }
}
