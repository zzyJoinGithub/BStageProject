package com.ziyuanziwen.everybodyvideo.myeverybodyvideo.mine;

/**
 * Created by dllo on 17/3/7.
 */

public class MineEntity {

    private String userName;
    private String title;
    private String image;
    private boolean isClicked;
    private boolean isLogin;

    public MineEntity(String userName, String title, boolean isClicked, String image, boolean isLogin) {
        this.userName = userName;
        this.title = title;
        this.isClicked = isClicked;
        this.image = image;
        this.isLogin = isLogin;
    }

    public MineEntity() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}