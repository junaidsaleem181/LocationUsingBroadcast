package org.example.roomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.example.roomdatabase.ScreenTime;

import java.util.List;

@Dao
public interface DaoAccess {

    @Insert
    Long insertTask(ScreenTime screenTime);


    @Query("SELECT * FROM ScreenTime")
    List<ScreenTime> fetchAllTasks();


    @Query("SELECT * FROM ScreenTime WHERE id =:taskId")
    ScreenTime getTask(int taskId);


    @Update
    void updateTask(ScreenTime screenTime);


    @Delete
    void deleteTask(ScreenTime screenTime);
}