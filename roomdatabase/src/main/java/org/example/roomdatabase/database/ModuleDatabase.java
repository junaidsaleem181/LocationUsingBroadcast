package org.example.roomdatabase.database;

import androidx.room.RoomDatabase;

import org.example.roomdatabase.dao.locationDao;
import org.example.roomdatabase.dao.placesDao;
import org.example.roomdatabase.dao.screenDao;
import org.example.roomdatabase.location_tracking;
import org.example.roomdatabase.places_visits;
import org.example.roomdatabase.screentime_used;

@androidx.room.Database(entities = {location_tracking.class, places_visits.class,
        screentime_used.class},version = 3)
public abstract class ModuleDatabase extends RoomDatabase {
    public abstract locationDao location();

    public abstract screenDao screen();

    public abstract placesDao place();
}
