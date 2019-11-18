package org.example.roomdatabase.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import org.example.roomdatabase.ScreenTime;
import org.example.roomdatabase.dao.DaoAccess;


@Database(entities = {ScreenTime.class}, version = 1, exportSchema = false)
public abstract class DatabaseClass extends RoomDatabase {

    public abstract DaoAccess daoAccess();
}