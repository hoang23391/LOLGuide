package com.hoangsv.lolguide.model;

/**
 * Created by User on 2/22/2017.
 */

public class TrangPhuc {
    private String id, num, name, image;

    public TrangPhuc() {
    }

    public TrangPhuc(String id, String num, String name, String image) {
        this.id = id;
        this.num = num;
        this.name = name;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
