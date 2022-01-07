package com.example.plana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton imageButtonMotivation;
    private ImageButton imageButtonCalendar;
    private ImageButton imageButtonFocus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButtonMotivation = (ImageButton) findViewById(R.id.imageButton1);
        imageButtonCalendar = (ImageButton) findViewById(R.id.imageButton2);
        imageButtonFocus = (ImageButton) findViewById(R.id.imageButton3);

        imageButtonMotivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MotivationTab.class);
                startActivity(intent);
            }
        });

        imageButtonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalendarTab.class);
                startActivity(intent);
            }
        });
    }
}