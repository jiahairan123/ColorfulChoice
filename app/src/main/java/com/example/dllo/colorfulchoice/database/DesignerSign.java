package com.example.dllo.colorfulchoice.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Coder: JiaHaiRan
 * created on 16/9/28 09:43
 */

@Entity
public class DesignerSign {
    
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String label;
    private String imgUrl;
    private String personImgUrl;

    @Generated(hash = 528142718)
    public DesignerSign(Long id, String name, String label, String imgUrl,
            String personImgUrl) {
        this.id = id;
        this.name = name;
        this.label = label;
        this.imgUrl = imgUrl;
        this.personImgUrl = personImgUrl;
    }

    @Generated(hash = 153365629)
    public DesignerSign() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPersonImgUrl() {
        return personImgUrl;
    }

    public void setPersonImgUrl(String personImgUrl) {
        this.personImgUrl = personImgUrl;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
