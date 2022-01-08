package com.example.plana;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TASK_NAME = "TASK_NAME";
    public static final String COLUMN_TASK_DESC = "TASK_DESC";
    public static final String COLUMN_DONE = "DONE";
    public static final String COLUMN_TIME = "TIME";
    public static final String COLUMN_DAY = "DAY";
    public static final String TABLE_TASKS = "TABLE_TASKS";

    public DataBaseHelper(@Nullable Context context) {
        super(context,
                "scheduler.db",
                null,
                1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TABLE_TASKS + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TASK_NAME + " TEXT, " + COLUMN_TASK_DESC + " TEXT, " + COLUMN_DONE + " BOOL, " + COLUMN_TIME + " TEXT, " + COLUMN_DAY + " DATE)";
        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(TaskModel taskModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TASK_NAME, taskModel.getTaskName());
        cv.put(COLUMN_TASK_DESC, taskModel.getTaskDesc());
        cv.put(COLUMN_DONE, taskModel.isMarkAsDone());
        cv.put(COLUMN_DAY, taskModel.getDate());
        cv.put(COLUMN_TIME, taskModel.getTime());

        return (db.insert(TABLE_TASKS, null, cv) > 0);
    }
    public List<TaskModel> selectTasks(String date)
    {
      List<TaskModel> returnList = new ArrayList<>();
      String selectStatement = "SELECT * FROM "+TABLE_TASKS+" WHERE "+COLUMN_DAY+" = "+ date;
      SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectStatement,null);
        if (cursor.moveToFirst())
        {
            do{
                int taskid = cursor.getInt(0);
                String taskName = cursor.getString(1);
                String taskDesc = cursor.getString(2);
                boolean taskDone = cursor.getInt(3)==1?true:false;
                String time = cursor.getString(4);
                String day = cursor.getString(5);

            }while(cursor.moveToNext());
        }
        else
        {

        }
        cursor.close();
        db.close();

        return  returnList;
    }
}
