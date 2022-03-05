package com.akuya.mydear.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class SuggestBean extends LitePalSupport {


    //属性
    @Column(unique = true)
    private  int id;

    @Column(nullable = false)
    private String FoodName;

    private String picture;

    private String binifit;

    private String kaluli;

    private String into;

    private String detail;

    private String nutri;


//方法

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNutri() {
    return nutri;

    }

    public void setNutri(String nutri) {
        this.nutri = nutri;
    }


    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getBinifit() {
        return binifit;
    }

    public void setBinifit(String binifit) {
        this.binifit = binifit;
    }

    public String getKaluli() {
        return kaluli;
    }

    public void setKaluli(String kaluli) {
        this.kaluli = kaluli;
    }

    public String getInto() {
        return into;
    }

    public void setInto(String into) {
        this.into = into;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
