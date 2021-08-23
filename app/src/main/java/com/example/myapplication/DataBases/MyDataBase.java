package com.example.myapplication.DataBases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class MyDataBase extends SQLiteAssetHelper {
    public static final String DATA_BASE_NAME = "saed.db";
    public static final String NAME_TAbLE = "tasks";
    public static final String TASK_ID = "id";
    public static final String TASK_TITLE = "title";
    public static final String TASK_TEXT = "text";
    public static final String TASK_COLOR = "color";
    public static final String TASK_ICON_COLOR = "iconColor";
    public static final String TASK_IS_DONE = "isDone";
    public static final String TASK_ICON_POSITION = "positionIcon";
    public static final String TASK_DATE_START = "dateStart";
    public static final String TASK_DATE_END = "dateEnd";

    public static final int DATA_BASE_version = 4;
    Context context;

    public MyDataBase(@Nullable Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_version);
        this.context = context;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NAME_TAbLE);
        onCreate(db);
    }


}
