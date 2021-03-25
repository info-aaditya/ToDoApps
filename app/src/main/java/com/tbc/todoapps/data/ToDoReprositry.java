package com.tbc.todoapps.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.tbc.todoapps.model.EToDo;
import com.tbc.todoapps.model.ToDoRoomDatabase;

import java.util.List;

public class ToDoReprositry {
    private ToDoDAO mToDoDAO;
    private LiveData<List<EToDo>> allToDoList;

    public ToDoReprositry(Application application){
        ToDoRoomDatabase database = ToDoRoomDatabase.getDatabase(application);
        mToDoDAO = database.mToDoDao();
        allToDoList = mToDoDAO.getAllToDos();
    }

    public ToDoDAO getmToDoDAO(){
        return mToDoDAO;
    }

    public void setmToDoDAO(ToDoDAO mToDoDAO) {
        this.mToDoDAO = mToDoDAO;
    }

    public LiveData<List<EToDo>> getAllToDoList(){
        return allToDoList;
    }

    public void setAllTodoList(LiveData<List<EToDo>> allTodoList) {
        this.allToDoList = allToDoList;
    }

    public void insert(EToDo eToDo){
        new insertToDoAysncTask(mToDoDAO).execute(eToDo);
    }

    public static class insertToDoAysncTask extends AsyncTask<EToDo, Void, Void>{
        private ToDoDAO mToDoDao;
        private insertToDoAysncTask(ToDoDAO toDoDAO){
            mToDoDao = toDoDAO;
        }
        @Override
        protected Void doInBackground(EToDo... eToDos){
            mToDoDao.insert(eToDos[0]);
            return null;
        }
    }
}
