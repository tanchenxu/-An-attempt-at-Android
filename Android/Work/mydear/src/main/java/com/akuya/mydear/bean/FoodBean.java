package com.akuya.mydear.bean;

import androidx.viewpager.widget.PagerAdapter;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class FoodBean extends LitePalSupport{
    //属性
    @Column(unique = true)
    private int id;

    private LoginUserBean loginUserBean;

    @Column(nullable = false)
    private String name;//不能为空

    @Column(nullable = false)
    private String content;

    @Column(unique = true)
    private String userId;//不可重复

    private String tel;

    private Date createTime;

    private Date updateTime;

    private String picture;

    private String function;



    private int calorie;


    private String types;

    //构造器
    public FoodBean(){

    }
    public FoodBean(String content, String name){
        this.content=content;
        this.name=name;
    }

    //方法

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }
    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }


    public LoginUserBean getLoginUserBean() {
        return loginUserBean;
    }

    public void setLoginUserBean(LoginUserBean loginUserBean) {
        this.loginUserBean = loginUserBean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getcontent() {
        return content;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }
    public void setcontent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

}

