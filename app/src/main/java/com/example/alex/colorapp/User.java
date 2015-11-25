package com.example.alex.colorapp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 11/24/15.
 */
public class User {
    private String sex;
    private String age;
    private Map<String, String> userAnswers;
    //Constructor
    public User(String sex, String age){
        this.sex = sex;
        this.age = age;
    }

    public void setHashMap(Map<String, String> userAnswers){
        this.userAnswers = userAnswers;
    }

}
