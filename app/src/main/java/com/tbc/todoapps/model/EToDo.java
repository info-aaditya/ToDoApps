package com.tbc.todoapps.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.tbc.todoapps.util.DateConverter;

import java.util.Date;

@Entity(tableName = "todo_table")
public class EToDo {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name ="description")
    private String description;

    @ColumnInfo(name ="priority")
    private int priority;

    @TypeConverters({DateConverter.class})
    @ColumnInfo(name ="todo_Date")
    private Date todoDate;

    @ColumnInfo(name ="is_Completed")
    private boolean isCompleted;

    @Ignore
    public EToDo() {
    }

    public EToDo(@NonNull String title, String description, int priority, Date todoDate, boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.todoDate = todoDate;
        this.isCompleted = isCompleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
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

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
