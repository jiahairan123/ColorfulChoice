package com.example.dllo.colorfulchoice.goodthing.fragment;

import com.example.dllo.colorfulchoice.goodthing.bean.PopBean;
import com.example.dllo.colorfulchoice.nettool.NetTool;
import com.example.dllo.colorfulchoice.nettool.URLValue;

import java.util.List;

/**
 * Created by mayinling on 16/9/19.
 */
public class NotmalTab {
    private static NotmalTab ourInstance = new NotmalTab();
    private PopBean popBean;
    private final NetTool netTool;

    public static NotmalTab getInstance() {
        return ourInstance;
    }

    private NotmalTab() {
        netTool = new NetTool();
        getData();
    }

    private void getData(){
        netTool.getNetData(URLValue.GOODTHINTS_POP, PopBean.class, new NetTool.NetListener<PopBean>() {
            @Override
            public void onSuccess(PopBean popBean) {
                NotmalTab.this.popBean = popBean;
            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }

    public PopBean.DataBean.CategoriesBean getBeanFormPos(int id){
        if(popBean == null){
            getData();
            return null;
        }
        for (PopBean.DataBean.CategoriesBean categoriesBean : popBean.getData().getCategories()) {
            if (categoriesBean.getId() == id){
                return categoriesBean;
            }
        }
        return null;
    }
}
