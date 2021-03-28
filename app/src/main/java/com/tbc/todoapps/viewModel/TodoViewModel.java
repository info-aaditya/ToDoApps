package com.tbc.todoapps.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tbc.todoapps.data.ToDoReprositry;
import com.tbc.todoapps.model.EToDo;

import java.util.List;

public class TodoViewModel  extends AndroidViewModel {

    public ToDoReprositry mToDoReprositry;
    private LiveData<List<EToDo>> allToDos;
    public TodoViewModel(@NonNull Application application){
        super(application);
        mToDoReprositry = new ToDoReprositry(application);
        allToDos = mToDoReprositry.getAllToDoList();
    }

    public LiveData<List<EToDo>> getAllToDos(){
        return allToDos;
    }

    public void insert(EToDo ToDo){
        mToDoReprositry.insert(ToDo);
    }

    public void deleteById(EToDo ToDo){
        mToDoReprositry.delete(ToDo);
    }

    public EToDo getTodoById(int id){
        mToDoReprositry.getTodoById(id);
        return null;
    }

    public void update(EToDo eToDo){
        mToDoReprositry.update(eToDo);
    }
}
