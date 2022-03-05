package com.akuya.mydear.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class ReduceFoodBean extends LitePalSupport {

    @Column(unique = true)
    private int id;

    @Column(nullable = false)
    private String userName;

    private String picture;

    private String harm;

    private String kaluli;

    private String way;

    private String lasttime;

    //方法

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getHarm() {
        return harm;
    }

    public void setHarm(String harm) {
        this.harm = harm;
    }

    public String getKaluli() {
        return kaluli;
    }

    public void setKaluli(String kaluli) {
        this.kaluli = kaluli;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }
}
