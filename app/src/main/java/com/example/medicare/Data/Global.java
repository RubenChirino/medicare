package com.example.medicare.Data;

import android.app.Application;

import com.example.medicare.Models.UserModel;

public class Global extends Application {

    private UserModel user;

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

}
