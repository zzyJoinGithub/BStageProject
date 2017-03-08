package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.mine;

/**
 * Created by dllo on 17/3/7.
 */

public class MineEntity {

    private String title;
    private String image;


    public MineEntity(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public MineEntity() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}