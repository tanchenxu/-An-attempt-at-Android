package com.akuya.mylitepal.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.Date;


public class LoginUserBean extends LitePalSupport{
//属性
    @Column(nullable = false)
    private String userName;//不能为空

    @Column(nullable = false)
    private String passWord;

    @Column(unique = true)
    private String userId;//不可重复

    private String tel;

    private Date createTime;

    private Date updateTime;

//构造器
    public LoginUserBean(){

    }

    public LoginUserBean(String id,String name){
        this.userId=id;
        this.userName=name;
    }

//方法
    public String getPassWord() {
        return passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
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
}
}

/**
 * Created by akuya
 * Used 登录账户信息bean类
 */