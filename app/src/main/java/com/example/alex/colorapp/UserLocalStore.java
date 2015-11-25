package com.example.alex.colorapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by alex on 11/25/15.
 */

public class UserLocalStore {

    public static final String SP_NAME = "userDetails";

    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putString("gender", user.getGender());
        userLocalDatabaseEditor.putInt("age", user.getAge());
        userLocalDatabaseEditor.putStringSet("answers", user.getHashMap().keySet());
        userLocalDatabaseEditor.commit();
    }

//    public void setUserLoggedIn(boolean loggedIn) {
//        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
//        userLocalDatabaseEditor.putBoolean("loggedIn", loggedIn);
//        userLocalDatabaseEditor.commit();
//    }

    public void clearUserData() {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.clear();
        userLocalDatabaseEditor.commit();
    }


}

