package com.tbc.todoapps.data;

import android.app.Application;
import android.os.AsyncTask;

import com.tbc.todoapps.model.EToDo;
import com.tbc.todoapps.model.ToDoRoomDatabase;

public class ToDoReprositry {
    private ToDoDAO mToDoDAO;

    public ToDoReprositry(Application application){
        ToDoRoomDatabase database = ToDoRoomDatabase.getDatabase(application);
        mToDoDAO = database.mToDoDao();
    }
    public ToDoDAO getmToDoDAO(){
        return mToDoDAO;
    }

    public void setmToDoDAO(ToDoDAO mToDoDAO) {
        this.mToDoDAO = mToDoDAO;
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
