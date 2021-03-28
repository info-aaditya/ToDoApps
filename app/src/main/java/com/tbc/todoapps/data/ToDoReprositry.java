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

    public void delete(EToDo eToDo){
        new deleteToDoAysncTask(mToDoDAO).execute(eToDo);
    }

    public EToDo getTodoById(int id){
        return mToDoDAO.getToDoById(id);
    }

    public void update(EToDo eToDo){
        new updateToDoAysncTask(mToDoDAO).execute(eToDo);
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

    public static class deleteToDoAysncTask extends AsyncTask<EToDo, Void, Void>{
        private ToDoDAO mToDoDao;
        private deleteToDoAysncTask(ToDoDAO toDoDAO){
            mToDoDao = toDoDAO;
        }
        @Override
        protected Void doInBackground(EToDo... eToDos){
            mToDoDao.deleteById(eToDos[0]);
            return null;
        }
    }

    private static class updateToDoAysncTask extends AsyncTask<EToDo, Void, Void>{
        private ToDoDAO mToDoDao;
        private updateToDoAysncTask(ToDoDAO toDoDAO){
            mToDoDao = mToDoDao;
        }
        @Override
        protected Void doInBackground(EToDo... eToDos){
            mToDoDao.update(eToDos[0]);
            return null;
        }
    }
}
