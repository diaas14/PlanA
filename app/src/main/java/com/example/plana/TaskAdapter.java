package com.example.plana;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final List<TaskModel> taskModelList;

    public TaskAdapter(Activity context, List<TaskModel> taskModelList)
    {
        super(context, R.layout.task);

        this.context=context;
        this.taskModelList=taskModelList;

    }

    @Override
    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.task, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.textViewTitle);
        TextView dueTimeText= (TextView)rowView.findViewById(R.id.textViewDueTime);
        TextView descText = (TextView) rowView.findViewById(R.id.textViewDescription);
        CheckBox doneCheckBox = (CheckBox) rowView.findViewById(R.id.checkBoxDone);

        titleText.setText(taskModelList.get(position).getTaskName());
        dueTimeText.setText(taskModelList.get(position).getTime());
        descText.setText(taskModelList.get(position).getTaskDesc());
        doneCheckBox.setChecked(taskModelList.get(position).isMarkAsDone());
        return rowView;

    };
}