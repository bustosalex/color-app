package com.example.alex.colorapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 11/24/15.
 */
public class User implements Serializable {
    private String gender;
    private int age;
    private String favoriteColor;
    private Map<String, String> userAnswers;
    //Constructor
    public User(String gender, String age, String favoriteColor){
        this.gender = gender;
        this.age = Integer.parseInt(age.toString());
        this.favoriteColor = favoriteColor;
    }

    public void setHashMap(Map<String, String> userAnswers){
        this.userAnswers = userAnswers;
    }

    public Map<String, String> getHashMap(){
        return this.userAnswers;
    }

    public void setAge(String age){
        int integerAge = Integer.parseInt(age.toString());
        this.age = integerAge;
    }

    public int getAge(){
        return this.age;
    }

    public void setGender(String gender){
        this.gender=gender;
    }
    public String getGender(){
        return this.gender;
    }

    public void setFavoriteColor(String favoriteColor){
        this.favoriteColor=favoriteColor;
    }
    public String getFavoriteColor(){
        return this.favoriteColor;
    }

}
