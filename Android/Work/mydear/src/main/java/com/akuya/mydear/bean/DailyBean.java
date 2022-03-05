package com.akuya.mydear.bean;

import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

public class DailyBean extends LitePalSupport {



    //属性
    private int id;

    private String time;

    private List<FoodBean>  foodBeanList =new ArrayList<FoodBean>();

    //方法

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<FoodBean> getFoodBeanList() {
        return foodBeanList;
    }

    public void setFoodBeanList(List<FoodBean> foodBeanList) {
        this.foodBeanList = foodBeanList;
    }
}
