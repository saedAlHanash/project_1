package com.example.myapplication;

import android.view.View;
import com.google.gson.annotations.SerializedName;
import java.util.Calendar;

public class ItemTask {
    @SerializedName("task_id")
    public int ID;
    @SerializedName("title")
    private String title;
    @SerializedName("to_do")
    private String text ;
    @SerializedName("color")
    private int color;
    @SerializedName("icon_color")
    private int iconColor;

    private int pointVisibility = View.INVISIBLE;
    private int iconVisibility = View.VISIBLE;

    @SerializedName("status")
    private boolean done;
    @SerializedName("drawable")
    private  int positionIcon;
    @SerializedName("start_date")
    private  String dateStart;
    @SerializedName("end_date")
    private  String dateEnd=DateConverter.now();
    @SerializedName("user_id")
   private int userID;
    @SerializedName("group_id")
    private int groupID;

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getPositionIcon() {
        return positionIcon;
    }

    public void setPositionIcon(int positionIcon) {
        this.positionIcon = positionIcon;
    }

    public boolean isDone() {return done; }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getIconColor() { return iconColor; }

    public void setIconColor(int iconColor) { this.iconColor = iconColor; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getPointVisibility() {
        return pointVisibility;
    }

    public void setPointVisibility(int pointVisibility) {
        this.pointVisibility = pointVisibility;
    }

    public int getIconVisibility() {
        return iconVisibility;
    }

    public void setIconVisibility(int iconVisibility) {
        this.iconVisibility = iconVisibility;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


}
