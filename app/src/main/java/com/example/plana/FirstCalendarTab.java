package com.example.plana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;


public class FirstCalendarTab extends AppCompatActivity {
    private DatePicker datePicker;
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
        setContentView(R.layout.activity_first_calendar_tab);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        Button taskbutton = (Button) findViewById(R.id.buttonTask);
        taskbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = getDate();
                //Toast.makeText(FirstCalendarTab.this,date,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(FirstCalendarTab.this, CalendarTab.class);
                // intent.putExtra("date", date);
                Bundle extras = new Bundle();
                extras.putString("date", date);
                extras.putString("day", String.valueOf(datePicker.getDayOfMonth()));
                extras.putString("month", String.valueOf(datePicker.getMonth()));
                extras.putString("year", String.valueOf(datePicker.getYear()));
                intent.putExtras(extras);

                startActivity(intent);
            }
        });

        imageButtonMotivation = (ImageButton) findViewById(R.id.imageButton1);
        imageButtonCalendar = (ImageButton) findViewById(R.id.imageButton2);
        imageButtonToday = (ImageButton) findViewById(R.id.imageButton3);

        imageButtonMotivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstCalendarTab.this, MotivationTab.class);
                startActivity(intent);
            }
        });

        imageButtonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstCalendarTab.this, FirstCalendarTab.class);
                startActivity(intent);
            }
        });

        imageButtonToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstCalendarTab.this, TaskToday.class);
                startActivity(intent);
            }
        });

    }

    public String getDate() {
        StringBuilder sb = new StringBuilder();
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();
        sb.append(year);
        sb.append("-");
        sb.append(month<=9?"0"+month:month);
        sb.append("-");
        sb.append(day<=9?"0"+day:day);

        return sb.toString();
    }

}