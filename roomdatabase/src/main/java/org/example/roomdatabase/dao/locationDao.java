package org.example.roomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import org.example.roomdatabase.location_tracking;

import java.util.List;

@Dao
public interface locationDao {
    @Query("Select * from location_tracking")
    List<location_tracking> listall();

    @Insert
    void insert(location_tracking l);
}
