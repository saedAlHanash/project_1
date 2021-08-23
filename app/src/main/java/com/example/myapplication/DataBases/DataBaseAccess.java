package com.example.myapplication.DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.ItemTask;

import java.util.ArrayList;

import static com.example.myapplication.DataBases.MyDataBase.NAME_TAbLE;
import static com.example.myapplication.DataBases.MyDataBase.TASK_COLOR;
import static com.example.myapplication.DataBases.MyDataBase.TASK_DATE_END;
import static com.example.myapplication.DataBases.MyDataBase.TASK_DATE_START;
import static com.example.myapplication.DataBases.MyDataBase.TASK_ICON_COLOR;
import static com.example.myapplication.DataBases.MyDataBase.TASK_ICON_POSITION;
import static com.example.myapplication.DataBases.MyDataBase.TASK_ID;
import static com.example.myapplication.DataBases.MyDataBase.TASK_IS_DONE;
import static com.example.myapplication.DataBases.MyDataBase.TASK_TEXT;
import static com.example.myapplication.DataBases.MyDataBase.TASK_TITLE;

public class DataBaseAccess {
    SQLiteDatabase dataBase;
    SQLiteOpenHelper openHelper;
    Context context;
    public static DataBaseAccess instance;

    private DataBaseAccess(Context context) {
        this.openHelper = new MyDataBase(context);
        this.context = context;
    }

    public static DataBaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DataBaseAccess(context);
        }
        return instance;
    }

    public void openWritable() {
        dataBase = this.openHelper.getWritableDatabase();
    }

    public void openReadable() {
        dataBase = this.openHelper.getReadableDatabase();
    }

    public void close() {
        if (this.dataBase != null)
            this.dataBase.close();
    }

    public boolean insertDB(ItemTask task) {
        openWritable();
        ContentValues values = addTaskHelper(task, false);
        long result = dataBase.insert(NAME_TAbLE, null, values);
        close();
        return result != -1;
    }

    public boolean updateDB(ItemTask task) {
        openWritable();
        ContentValues values = addTaskHelper(task, true);
        String[] args = {task.ID + ""};
        int result = dataBase.update(NAME_TAbLE, values, "id=?", args);
        close();
        return result == 1;
    }

    public boolean deleteTask(ItemTask task) {
        openWritable();
        String[] args = {task.ID + ""};
        int result = dataBase.delete(NAME_TAbLE, "id=?", args);
        close();
        return result == 1;
    }

    public ArrayList<ItemTask> restoreFromDataBase(boolean done) {
        openReadable();
        ArrayList<ItemTask> tasks = new ArrayList<>();
        Cursor cursor = null;

        if (done){
            cursor = dataBase.rawQuery("SELECT * FROM " + NAME_TAbLE +" WHERE isDone= 1", null);
        }else {
            cursor = dataBase.rawQuery("SELECT * FROM " + NAME_TAbLE +" WHERE isDone= 0", null);
        }

        if (cursor != null && cursor.moveToFirst()) {
            do {
                ItemTask task = new ItemTask();
                task.ID = cursor.getInt(cursor.getColumnIndex(TASK_ID));
                task.setTitle(cursor.getString(cursor.getColumnIndex(TASK_TITLE)));
                task.setText(cursor.getString(cursor.getColumnIndex(TASK_TEXT)));
                task.setColor(cursor.getInt(cursor.getColumnIndex(TASK_COLOR)));
                task.setIconColor(cursor.getInt(cursor.getColumnIndex(TASK_ICON_COLOR)));
                task.setPositionIcon(cursor.getInt(cursor.getColumnIndex(TASK_ICON_POSITION)));
                task.setDone(cursor.getInt(cursor.getColumnIndex(TASK_IS_DONE)) == 1);
                task.setDateStart(cursor.getString(cursor.getColumnIndex(TASK_DATE_START)));
                task.setDateEnd(cursor.getString(cursor.getColumnIndex(TASK_DATE_END)));
                tasks.add(task);
            } while (cursor.moveToNext());
            cursor.close();
        }
        close();
        return tasks;
    }

    private ContentValues addTaskHelper(ItemTask task, boolean isEdit) {
        ContentValues values = new ContentValues();
        values.put(TASK_TITLE, task.getTitle());
        values.put(TASK_TEXT, task.getText());
        values.put(TASK_COLOR, task.getColor());
        values.put(TASK_ICON_COLOR, task.getIconColor());
        values.put(TASK_ICON_POSITION, task.getPositionIcon());
        if (task.isDone()) {
            values.put(TASK_IS_DONE, 1);
        } else {
            values.put(TASK_IS_DONE, 0);
        }
        if (!isEdit)
            values.put(TASK_DATE_START, task.getDateStart());
        values.put(TASK_DATE_END, task.getDateEnd());
        return values;
    }
}
