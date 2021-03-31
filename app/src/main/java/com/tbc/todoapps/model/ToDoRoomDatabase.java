package com.tbc.todoapps.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.tbc.todoapps.data.ToDoDAO;

import java.util.Date;

@Database(entities =  {EToDo.class}, version =1, exportSchema = false)
public abstract class ToDoRoomDatabase  extends RoomDatabase {

    public abstract ToDoDAO mToDoDao();

    public static ToDoRoomDatabase INSTANCE;
    public static ToDoRoomDatabase getDatabase(Context context){
        if (INSTANCE == null){
            synchronized (ToDoRoomDatabase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ToDoRoomDatabase.class, "todo.db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static class PopulateDbAsync extends AsyncTask<EToDo,Void,Void> {
        private  ToDoDAO mToDoDAO;
        private PopulateDbAsync(ToDoRoomDatabase db)
        {
            mToDoDAO=db.mToDoDao();
        }

        @Override
        protected Void doInBackground(EToDo... toDos) {
            Date date=new Date();
            EToDo todo= new EToDo("Demo title","Demo Description",1,date,false);
            mToDoDAO.insert(todo);
            return null;
        }
    }

    private static RoomDatabase.Callback sCallback=new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };
}
