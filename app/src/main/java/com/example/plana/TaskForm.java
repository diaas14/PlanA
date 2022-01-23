package com.example.plana;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class TaskForm extends AppCompatActivity {
    private String date;
    private Button btnFinish;
    private EditText taskName;
    private EditText taskDesc;
    private TimePicker dueTime;
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

        setContentView(R.layout.activity_task_form);
        date = getIntent().getStringExtra("date");
        btnFinish = (Button) findViewById(R.id.buttonFinish);
        taskName = (EditText) findViewById(R.id.editTextTaskName);
        taskDesc = (EditText) findViewById(R.id.editTextDesc);
        dueTime = (TimePicker) findViewById(R.id.timePickerForm);

        imageButtonMotivation = (ImageButton) findViewById(R.id.imageButton1);
        imageButtonCalendar = (ImageButton) findViewById(R.id.imageButton2);
        imageButtonToday = (ImageButton) findViewById(R.id.imageButton3);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Calendar cal = Calendar.getInstance();

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (taskName.getText().toString().equals(""))
                        throw new EmptyFieldException("Task Name Empty");
                    TaskModel taskModel = new TaskModel(-1, taskName.getText().toString(), taskDesc.getText().toString(), false,
                            date, getTime());
                    DataBaseHelper db = new DataBaseHelper(TaskForm.this);
                    boolean success = db.addOne(taskModel);

                    cal.clear();
                    cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(extras.getString("day")));
                    cal.set(Calendar.MONTH, Integer.valueOf(extras.getString("month")));
                    cal.set(Calendar.YEAR, Integer.valueOf(extras.getString("year")));
                    cal.set(Calendar.HOUR_OF_DAY, dueTime.getCurrentHour());
                    cal.set(Calendar.MINUTE, dueTime.getCurrentMinute());
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.MILLISECOND, 0);

                    Intent intent1 = new Intent(TaskForm.this, ReminderBroadcast.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(TaskForm.this, 0, intent1, 0);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                    alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

                    Toast.makeText(TaskForm.this, success ? "Successful" : "Unsuccessful", Toast.LENGTH_LONG).show();

                }
                catch (EmptyFieldException e)
                {
                    Toast.makeText(TaskForm.this, e.getMessage(), Toast.LENGTH_LONG).show();

                }
                catch (Exception e) {
                    Toast.makeText(TaskForm.this, "ERROR", Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(TaskForm.this, CalendarTab.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });
        imageButtonMotivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskForm.this, MotivationTab.class);
                startActivity(intent);
            }
        });

        imageButtonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskForm.this, FirstCalendarTab.class);
                startActivity(intent);
            }
        });

        imageButtonToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskForm.this, TaskToday.class);
                startActivity(intent);
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