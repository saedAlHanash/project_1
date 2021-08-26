package com.example.myapplication.APIs;

import com.example.myapplication.Group;
import com.example.myapplication.ItemTask;
import com.example.myapplication.User;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {


    @POST("tms/register.php")
    Call<ApiResponse> insertUser(@Body User user);

    @POST("tms/insert_task.php")
    Call<ApiResponse> insertTask(@Body ItemTask task);

    @GET("tms/tojson.php")
    Call<ArrayList<User>> getAllUsers();

    @GET("tms/login.php")
    Call<User> getUser(@Query("user_name") String userName, @Query("password") String password);

    @FormUrlEncoded
    @POST("tms/insert_group.php")
    Call<ApiResponse> insertGroup(@Field("group_name") String  groupName, @Field("user_id") int userID);

    @GET("tms/user_group.php")
    Call<ArrayList<Group>> getGroups(@Query("user_id") int userID);

    @GET("tms/return_tasks.php")
    Call<ArrayList<ItemTask>> getAllTasksForGroup(@Query("group_id") int groupID);

}
