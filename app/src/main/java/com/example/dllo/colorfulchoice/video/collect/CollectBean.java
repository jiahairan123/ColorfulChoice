package com.example.dllo.colorfulchoice.video.collect;

import com.example.dllo.colorfulchoice.database.DBVideoView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${马庭凯} on 16/9/14.
 */

public class CollectBean {
    private List<DBVideoView> resultBeanList;
    private static CollectBean collectBean = null;
    private CollectBean(){
        resultBeanList = new ArrayList<>();
    }
    public static CollectBean getInstance(){
        if(collectBean == null){
            synchronized (CollectBean.class){
                if(collectBean == null){
                    collectBean = new CollectBean();
                }
            }
        }
        return collectBean;
    }

    public List<DBVideoView> getResultBeanList() {
        return resultBeanList;
    }

    public void setResultBeanList(List<DBVideoView> resultBeanList) {
        this.resultBeanList = resultBeanList;
    }

    public void setResultBeanList(DBVideoView dbVideoView){
        resultBeanList.add(dbVideoView);
    }
}
