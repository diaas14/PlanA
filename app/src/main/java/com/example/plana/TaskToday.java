package com.example.plana;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TaskToday extends AppCompatActivity {
    private ListView listViewTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_task_today);

        listViewTasks = (ListView) findViewById(R.id.listViewToday);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObj = new Date();
        String date = formatter.format(dateObj);
        Log.i("Today", date);

        DataBaseHelper db = new DataBaseHelper(TaskToday.this);
        try {
            List<TaskModel> taskModelList = db.selectTasks(date);
            TaskAdapter adapter = new TaskAdapter(TaskToday.this, taskModelList);
            listViewTasks.setAdapter(adapter);
        } catch (SQLQueryException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}