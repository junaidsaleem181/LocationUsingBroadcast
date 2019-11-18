package org.example.roomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import org.example.roomdatabase.places_visits;

import java.util.List;

@Dao
public interface placesDao {
    @Query("Select * from places_visits")
    List<places_visits> listall();

    @Insert
    void insert(places_visits p);
}
