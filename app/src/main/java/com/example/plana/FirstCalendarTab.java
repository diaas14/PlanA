package com.example.plana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class FirstCalendarTab extends AppCompatActivity {
    private DatePicker datePicker;
    private Button taskbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_calendar_tab);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        taskbutton = (Button) findViewById(R.id.buttonTask);
        taskbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String date = getDate();
                //Toast.makeText(FirstCalendarTab.this,date,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(FirstCalendarTab.this, CalendarTab.class);
                intent.putExtra("date",date);
                startActivity(intent);

            }
            public String getDate()
            {
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
        });

    }

}