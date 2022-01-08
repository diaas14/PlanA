package com.example.plana;

public class TaskModel {
    private String taskName;
    private String taskDesc;
    private boolean markAsDone;
    private String date;
    private String time;
    private int id;


    public TaskModel(int id, String taskName, String taskDesc, boolean markAsDone, String date, String time) {
        this.taskName = taskName;
        this.taskDesc = taskDesc;
        this.markAsDone = markAsDone;
        this.date = date;
        this.time = time;
        this.id=id;
    }

    public TaskModel() {
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public boolean isMarkAsDone() {
        return markAsDone;
    }

    public void setMarkAsDone(boolean markAsDone) {
        this.markAsDone = markAsDone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
