package com.example.dllo.colorfulchoice.video;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${马庭凯} on 16/9/14.
 */

public class CollectBean {
    private List<ResultBean> resultBeanList;
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

    public List<ResultBean> getResultBeanList() {
        return resultBeanList;
    }

    public void setResultBeanList(List<ResultBean> resultBeanList) {
        this.resultBeanList = resultBeanList;
    }

    public void setResultBeanList(ResultBean resultBean){
        resultBeanList.add(resultBean);
    }

    public class ResultBean{
        private String itemId;
        private String title;
        private String date;
        private String image;
        private String videoUrl;

        public ResultBean(String itemId, String title, String date, String image, String videoUrl) {
            this.itemId = itemId;
            this.title = title;
            this.date = date;
            this.image = image;
            this.videoUrl = videoUrl;
        }

        public String getItemId() {
            return itemId;
        }

        public String getTitle() {
            return title;
        }

        public String getDate() {
            return date;
        }

        public String getImage() {
            return image;
        }

        public String getVideoUrl() {
            return videoUrl;
        }
    }
}
