package com.example.plana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.lang.Math;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TaskToday extends AppCompatActivity {
    private ListView listViewTasks;
    private ProgressBar progressBar;
    private TextView progressText;
    private List<TaskModel> taskModelList;
    private ImageButton imageButtonMotivation;
    private ImageButton imageButtonCalendar;
    private ImageButton imageButtonToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_task_today);

        listViewTasks = (ListView) findViewById(R.id.listViewToday);
        progressBar = (ProgressBar) findViewById(R.id.circularProgressbar);
        progressText = (TextView) findViewById(R.id.textViewProgress);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObj = new Date();
        String date = formatter.format(dateObj);
        Log.i("Today", date);

        DataBaseHelper db = new DataBaseHelper(TaskToday.this);
        try {
            taskModelList = db.selectTasks(date);
            TaskAdapter adapter = new TaskAdapter(TaskToday.this, taskModelList);
            listViewTasks.setAdapter(adapter);
        } catch (SQLQueryException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        imageButtonMotivation = (ImageButton) findViewById(R.id.imageButton1);
        imageButtonCalendar = (ImageButton) findViewById(R.id.imageButton2);
        imageButtonToday = (ImageButton) findViewById(R.id.imageButton3);

        imageButtonMotivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskToday.this, MotivationTab.class);
                startActivity(intent);
            }
        });

        imageButtonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskToday.this, FirstCalendarTab.class);
                startActivity(intent);
            }
        });

        imageButtonToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskToday.this, TaskToday.class);
                startActivity(intent);
            }
        });


        float percentageProgress = getPercentage();
        progressBar.setProgress((int)percentageProgress);
        progressText.setText(String.format("%.2f", percentageProgress) + "%");


    }

    float getPercentage() {
        if (taskModelList == null) return 100;
        int tasksDone = 0, totalTasks;
        totalTasks = taskModelList.size();
        for (TaskModel taskModel: taskModelList) {
            if (taskModel.isMarkAsDone()) tasksDone++;
        }
        return ((float) tasksDone / totalTasks)* 100;
    }
}