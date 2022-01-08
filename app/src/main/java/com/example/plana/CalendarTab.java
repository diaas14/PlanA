package com.example.plana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class CalendarTab extends AppCompatActivity {

    private ListView listViewTasks;
    private static String [] mainTitle = {"Math Quiz", "Chem Lab", "C++ Contest"};
    private static String [] desc = {"Casual Quiz", "Portions: Organic Chemistry and sub chapters", "Contest on Leetcode"};
    private static String [] dueTime = {"7 am", "10 pm", "11 pm"};
    private static Boolean [] markedAsDone = {true, false, false};
    private Button btnAdd;
    private ImageButton imageButtonMotivation;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_tab);


        imageButtonMotivation = (ImageButton) findViewById(R.id.imageButton1);

        date=getIntent().getStringExtra("date");
        TextView textViewDay = (TextView) findViewById(R.id.textViewDay);
        textViewDay.setText(date);

        DataBaseHelper db = new DataBaseHelper(CalendarTab.this);
        List<TaskModel> taskModelList = db.selectTasks(date);

        TaskAdapter adapter = new TaskAdapter(this, taskModelList);
        listViewTasks = (ListView) findViewById(R.id.listView);
        listViewTasks.setAdapter(adapter);
        btnAdd = (Button) findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarTab.this, TaskForm.class);
                intent.putExtra("date",date);
                startActivity(intent);

            }
        });

        imageButtonMotivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarTab.this, MotivationTab.class);
                startActivity(intent);
            }
        });
    }
}