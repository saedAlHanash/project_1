package com.example.myapplication.APIs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClint {

    public  static final String BASE_URL = "http://192.168.43.218:8080/";
    public static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){
        if(retrofit==null)
        {
            Gson gson = new GsonBuilder().setLenient().create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory( GsonConverterFactory.create(gson))
                    .build();
            return retrofit;
        }
        return retrofit;
    }


}
