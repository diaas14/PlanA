package com.example.plana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CheckBox;

import java.util.List;

public class CalendarTab extends AppCompatActivity {

    private ListView listViewTasks;
    private Button btnAdd;
    private ImageButton imageButtonMotivation;
    private String date;
    private List<TaskModel> taskModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_calendar_tab);


        imageButtonMotivation = (ImageButton) findViewById(R.id.imageButton1);

        date=getIntent().getStringExtra("date");
        TextView textViewDay = (TextView) findViewById(R.id.textViewDay);
        textViewDay.setText(date);

        DataBaseHelper db = new DataBaseHelper(CalendarTab.this);
        try {
            taskModelList = db.selectTasks(date);
            TaskAdapter adapter = new TaskAdapter(CalendarTab.this, taskModelList);
            listViewTasks = (ListView) findViewById(R.id.listView);
            listViewTasks.setAdapter(adapter);
        } catch (SQLQueryException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        listViewTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBoxDone);

                // db.updateDone(checkBox.isChecked(), taskModelList.get(position).getId());
                Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            }
        });

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