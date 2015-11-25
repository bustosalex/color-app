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
    private String sex;
    private int age;
    private Map<String, String> userAnswers;
    //Constructor
    public User(String sex, String age){
        this.sex = sex;
        this.age = Integer.parseInt(age.toString());
    }

    public void setHashMap(Map<String, String> userAnswers){
        this.userAnswers = userAnswers;
    }

    public void setAge(String age){
        int integerAge = Integer.parseInt(age.toString());
        this.age = integerAge;
    }

    public int getAge(){
        return this.age;
    }

    public void setGender(String gender){
        this.sex=gender;
    }
    public String getGender(){
        return this.sex;
    }

}
