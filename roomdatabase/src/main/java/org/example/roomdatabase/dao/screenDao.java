package org.example.roomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import org.example.roomdatabase.screentime_used;

import java.util.List;

@Dao
public interface screenDao {
    @Query("Select * from screentime_used")
    List<screentime_used> listall();

    @Insert
    void insert(screentime_used s);

}
