package com.example.dllo.colorfulchoice.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by mayinling on 16/9/27.
 */
@Entity
public class GoodThings {

    @Id(autoincrement = true)
    private Long id;
    private String imgUrl;
    private String username;

    @Generated(hash = 299017040)
    public GoodThings(Long id, String imgUrl, String username) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.username = username;
    }

    @Generated(hash = 515992398)
    public GoodThings() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
