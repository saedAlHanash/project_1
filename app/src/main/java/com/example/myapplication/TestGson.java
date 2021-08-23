package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class TestGson {
    @SerializedName("status")
    public String status;
    @SerializedName("result_code")
    int resultCode;
    @SerializedName("name")
    String name;

    public String getStatus() {
        return status;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getName() {
        return name;
    }
}
