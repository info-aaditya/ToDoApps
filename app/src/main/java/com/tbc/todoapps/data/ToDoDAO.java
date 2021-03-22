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
    void insert (EToDo eToDo);

    @Query("DELETE FROM eToDo")
    void deleteALL();

    @Delete
    void deleteById(EToDo eToDo);

    @Query("SELECT * FROM eToDo WHERE id = :id")
    EToDo getToDoItemById(Integer id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(EToDo... todo);
}
