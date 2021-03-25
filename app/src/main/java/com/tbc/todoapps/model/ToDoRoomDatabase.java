package com.tbc.todoapps.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tbc.todoapps.data.ToDoDAO;

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
}
