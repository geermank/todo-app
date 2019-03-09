package com.geermank.todoapp.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.geermank.todoapp.Models.Task;

import java.util.List;


@Dao
public interface TasksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createTask(Task t);

    @Update
    void updateTask(Task t);

    @Query("UPDATE tasks SET finished = 1 WHERE id = :id")
    void finishTask(String id);

    @Query("SELECT * FROM tasks")
    List<Task> getAllTasks();

    @Query("SELECT * FROM tasks WHERE finished")
    Task getFinishedTasks();

}
