package com.akuya.mydear.bean;

import androidx.viewpager.widget.PagerAdapter;

import org.litepal.LitePal;
import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class LoginUserBean extends LitePalSupport{
//属性


    @Column(unique = true)
    private int id;

    @Column(nullable = false)
    private String userName;//不能为空

    @Column(nullable = false)
    private String passWord;

    @Column(unique = true)
    private String userId;//不可重复


    private List<FoodBean> foodBeans=new ArrayList<FoodBean>();

    private List<SuggestBean> suggestBeans=new ArrayList<SuggestBean>();


    private List<ReduceFoodBean> reduceFoodBeans=new ArrayList<ReduceFoodBean>();

    private List<DailyBean> dailyBeans = new ArrayList<DailyBean>();


    private String Goal;

    private String School;

    private String NickName;

    private String City;

    private String gender;

    private String Age;

    private String Head;

    private String type;



    //构造器
    public LoginUserBean(){

    }

    public LoginUserBean(String id, String name){
        this.userId=id;
        this.userName=name;
    }

//方法
public List<DailyBean> getDailyBeans() {
    return dailyBeans;
}

    public void setDailyBeans(List<DailyBean> dailyBeans) {
        this.dailyBeans = dailyBeans;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHead() {
        return Head;
    }

    public void setHead(String head) {
        Head = head;
    }

public String getGoal() {
    return Goal;
}

    public void setGoal(String goal) {
        this.Goal = goal;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String school) {
        this.School = school;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        this.NickName = nickName;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        this.City = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

public int getId() {
    return id;
}

    public void setId(int id) {
        this.id = id;
    }

    public List<FoodBean> getFoodBeans() {
        return foodBeans;
    }

    public void setFoodBeans(List<FoodBean> foodBeans) {
        this.foodBeans = foodBeans;
    }

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

    public List<SuggestBean> getSuggestBeans() {
        return suggestBeans;
    }

    public void setSuggestBeans(List<SuggestBean> suggestBeans) {
        this.suggestBeans = suggestBeans;
    }

    public List<ReduceFoodBean> getReduceFoodBeans() {
        return reduceFoodBeans;
    }

    public void setReduceFoodBeans(List<ReduceFoodBean> reduceFoodBeans) {
        this.reduceFoodBeans = reduceFoodBeans;
    }
}


/**
 * Created by akuya
 * Used 登录账户信息bean类
 */