package com.anis.app.todoapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ToDoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ToDo toDo);

    @Update()
    void update(ToDo toDo);

    @Delete()
    void delete(ToDo toDo);

    @Query("SELECT * FROM todo ORDER BY id ASC")
    LiveData<List<ToDo>> getAllToDos();
}
