package com.tbc.todoapps.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.tbc.todoapps.model.EToDo;

import java.util.List;

@Dao
public interface ToDoDAO {
    @Insert
    void insert (EToDo todo);

    @Query("DELETE FROM todo_table")
    void deleteAll();

    @Query("DELETE FROM todo_table WHERE is_completed=1")
    void deleteAllCompleted();

    @Delete
    void deleteById(EToDo todo);

    @Query("SELECT * FROM todo_table WHERE id = :id")
    EToDo getToDoById(int id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(EToDo... toDo);

    @Query("SELECT * FROM todo_table ORDER BY todo_date, priority desc")
    LiveData<List<EToDo>> getAllToDos();
}
