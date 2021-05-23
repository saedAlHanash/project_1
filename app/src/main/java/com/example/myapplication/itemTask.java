package com.example.myapplication;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;

public class itemTask {
    private String title = "?";
    private int color = Color.RED;
    private int iconColor = Color.WHITE;
    private int pointVisibility = View.INVISIBLE;
    private int iconVisibility = View.VISIBLE;
    private Drawable drawable;

    public int getIconColor() { return iconColor; }

    public void setIconColor(int iconColor) { this.iconColor = iconColor; }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
}
