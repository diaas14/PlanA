package com.example.plana;

import android.app.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<TaskModel> {

    private final Activity context;
    private final List<TaskModel> taskModelList;
    private DataBaseHelper db;

    public TaskAdapter(Activity context, List<TaskModel> taskModelList)
    {
        super(context, R.layout.task, taskModelList);

        this.context=context;
        this.taskModelList=taskModelList;
        this.db = new DataBaseHelper(context);
        Log.i("Length", String.valueOf(this.taskModelList.size()));

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.task, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.textViewTitle);
        TextView dueTimeText= (TextView)rowView.findViewById(R.id.textViewDueTime);
        TextView descText = (TextView) rowView.findViewById(R.id.textViewDescription);
        CheckBox doneCheckBox = (CheckBox) rowView.findViewById(R.id.checkBoxDone);
        ImageButton btnDetele = (ImageButton)rowView.findViewById(R.id.buttonBin);

        titleText.setText(taskModelList.get(position).getTaskName());
        dueTimeText.setText(taskModelList.get(position).getTime());
        descText.setText(taskModelList.get(position).getTaskDesc());
        doneCheckBox.setChecked(taskModelList.get(position).isMarkAsDone());

        doneCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db.markDone(taskModelList.get(position).getId(), doneCheckBox.isChecked());
                } catch (Error e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                }
                // Toast.makeText(context, "Checkbox " + position + " checked. ID: " + taskModelList.get(position).getId(), Toast.LENGTH_LONG).show();
                // Toast.makeText(context, "Checkbox " + position + " unchecked. ID: " + taskModelList.get(position).getId(), Toast.LENGTH_LONG).show();
            }
        });

        btnDetele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db.deleteTask(taskModelList.get(position).getId());
                    Toast.makeText(context, "Delete successful, refresh page to reflect changes", Toast.LENGTH_LONG).show();
                } catch (Error e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        return rowView;
    };
}