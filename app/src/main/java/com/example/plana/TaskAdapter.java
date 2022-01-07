package com.example.plana;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class TaskAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] mainTitle;
    private final String[] dueTime;
    private final String[] desc;
    private final Boolean[] markedAsDone;

    public TaskAdapter(Activity context, String[] mainTitle,String[] dueTime, String[] desc, Boolean[] markedAsDone) {
        super(context, R.layout.task, mainTitle);

        this.context=context;
        this.mainTitle=mainTitle;
        this.dueTime=dueTime;
        this.desc=desc;
        this.markedAsDone=markedAsDone;

    }

    @Override
    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.task, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.textViewTitle);
        TextView dueTimeText= (TextView)rowView.findViewById(R.id.textViewDueTime);
        TextView descText = (TextView) rowView.findViewById(R.id.textViewDescription);
        CheckBox doneCheckBox = (CheckBox) rowView.findViewById(R.id.checkBoxDone);

        titleText.setText(mainTitle[position]);
        dueTimeText.setText(dueTime[position]);
        descText.setText(desc[position]);
        doneCheckBox.setChecked(markedAsDone[position]);
        return rowView;

    };
}