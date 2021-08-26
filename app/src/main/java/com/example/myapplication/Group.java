package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class Group {
    @SerializedName("group_id")
    int groupID;
    @SerializedName("group_name")
    String groupName;
    @SerializedName("admin")
    int adminID;

    public Group(String groupName) {
        this.groupName = groupName;
        this.adminID = adminID = AppConfig.sp.getInt("user_id",-1);
    }

    public int getGroupID() {
        return groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getAdminID() {
        return adminID;
    }

}
