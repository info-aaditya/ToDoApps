package com.tbc.todoapps.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.tbc.todoapps.util.DateConverter;

import java.util.Date;

@Entity
public class EToDo {
    @ColumnInfo(name ="id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name ="description")
    private String description;

    @ColumnInfo(name ="priority")
    private int priority;

    @TypeConverters({DateConverter.class})
    @ColumnInfo(name ="todo_Date")
    private Date todoDate;

    @ColumnInfo(name ="is_Complete")
    private Boolean isComplete;

    public EToDo(String title, String description, int priority, Date todoDate, Boolean isComplete) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.todoDate = todoDate;
        this.isComplete = isComplete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getTodoDate() {
        return todoDate;
    }

    public void setTodoDate(Date todoDate) {
        this.todoDate = todoDate;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }
}