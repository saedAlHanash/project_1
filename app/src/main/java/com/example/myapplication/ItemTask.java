package com.example.myapplication;

import android.graphics.Color;
import android.view.View;
import java.util.Calendar;

public class ItemTask {
    public int ID;
    private String title = "?";
    private String text = " ";
    private int color;
    private int iconColor;
    private int pointVisibility = View.INVISIBLE;
    private int iconVisibility = View.VISIBLE;
    private boolean done = false;
    private  int positionIcon;
    private  String dateStart = now();
    private  String dateEnd;

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

    public String now()
    {
        return DateConverter.dateToString(Calendar.getInstance().getTime());
    }
}
