package com.example.plana;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;

public class TaskForm extends AppCompatActivity {
    private String date;
    private Button btnFinish;
    private EditText taskName;
    private EditText taskDesc;
    private TimePicker dueTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.activity_task_form);
        date = getIntent().getStringExtra("date");
        btnFinish = (Button) findViewById(R.id.buttonFinish);
        taskName = (EditText) findViewById(R.id.editTextTaskName);
        taskDesc = (EditText) findViewById(R.id.editTextDesc);
        dueTime = (TimePicker) findViewById(R.id.timePickerForm);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    TaskModel taskModel = new TaskModel(-1, taskName.getText().toString(), taskDesc.getText().toString(), false,
                            date, getTime());
                    DataBaseHelper db = new DataBaseHelper(TaskForm.this);
                    boolean success = db.addOne(taskModel);
                    Toast.makeText(TaskForm.this, success ? "Successful" : "Unsuccessful", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(TaskForm.this, "ERROR", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public String getTime() {
        StringBuilder sb = new StringBuilder();
        sb.append(dueTime.getCurrentHour() <= 9 ? "0" + dueTime.getCurrentHour() : dueTime.getCurrentHour());
        sb.append(":");
        sb.append(dueTime.getCurrentMinute() <= 9 ? "0" + dueTime.getCurrentMinute() : dueTime.getCurrentMinute());

        return sb.toString();
    }


}