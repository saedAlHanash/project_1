package com.example.myapplication;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {
    @GET("saed/1")
    public Call<User> getPost();

    @FormUrlEncoded
    @POST("ss/register.php")
    Call<ResponseBody> insertUser(
            @Field("user_name") String name,
            @Field("name") String email,
            @Field("password") String password);

    @POST("ss/json_mysql.php")
    Call<User> store(@Body User user);

    @GET("ss/tojson.php")
    Call<User> store();

    @POST("saed/tojson.php")
    Call<User> saed();
}
