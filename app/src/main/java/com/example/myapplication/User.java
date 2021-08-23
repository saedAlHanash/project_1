package com.example.myapplication;

import com.example.myapplication.DataBases.DataBaseAccess;
import com.example.myapplication.UI.MainActivity;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("user_id")
   public String  id;
    @SerializedName("name")
    String name;
    @SerializedName("user_name")
    String user_name;
    @SerializedName("password")
    String password;
    @SerializedName("email")
    String email;
    @SerializedName("phone")
    String phone;
    @SerializedName("points")
    public  String  points;

    public User() {
    }

    public User(String name, String user_name, String password, String email) {
        this.name = name;
        this.user_name = user_name;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
