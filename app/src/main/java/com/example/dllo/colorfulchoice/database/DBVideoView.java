package com.example.dllo.colorfulchoice.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ${马庭凯} on 16/9/29.
 */
@Entity
public class DBVideoView {
    @Id(autoincrement = true)
    private Long id;
    private String userName;
    private String itemId;
    private String title;
    private String date;
    private String image;
    private String videoUrl;
    @Generated(hash = 1446607392)
    public DBVideoView(Long id, String userName, String itemId, String title,
            String date, String image, String videoUrl) {
        this.id = id;
        this.userName = userName;
        this.itemId = itemId;
        this.title = title;
        this.date = date;
        this.image = image;
        this.videoUrl = videoUrl;
    }
    @Generated(hash = 1137851661)
    public DBVideoView() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getItemId() {
        return this.itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getVideoUrl() {
        return this.videoUrl;
    }
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
