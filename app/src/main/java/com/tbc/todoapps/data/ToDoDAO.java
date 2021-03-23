package com.tbc.todoapps.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.tbc.todoapps.model.EToDo;

@Dao
public interface ToDoDAO {
    @Insert
    void insert (EToDo todo);

    @Query("DELETE FROM todo_table")
    void deleteALL();

    @Delete
    void deleteById(EToDo todo);

    @Query("SELECT * FROM todo_table WHERE id = :id")
    EToDo getToDoItemById(String id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(EToDo... todo);
}
