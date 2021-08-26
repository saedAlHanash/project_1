package com.example.myapplication.APIs;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("result_code")
    int resultCode = -1;
    @SerializedName("status")
    String status;

    public int getResultCode() {
        return resultCode;
    }

    public String getStatus() {
        return status;
    }
}
