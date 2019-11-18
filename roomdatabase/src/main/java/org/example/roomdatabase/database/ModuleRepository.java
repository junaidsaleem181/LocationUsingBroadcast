package org.example.roomdatabase.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

import org.example.roomdatabase.location_tracking;
import org.example.roomdatabase.places_visits;
import org.example.roomdatabase.screentime_used;

public class ModuleRepository {
    private ModuleDatabase modDB;
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("drop TABLE screentime_used");
            database.execSQL("create table screentime_used (id integer primary key autoincrement,screen_event String,[desc] String)");
        }
    };
    public ModuleRepository(Context context) {
        modDB = Room.databaseBuilder(context, ModuleDatabase.class, "my_DB").fallbackToDestructiveMigration().build();
    }

    @SuppressLint("StaticFieldLeak")
    public void insertplaces(String name,Boolean b, String ct) {
        final places_visits places = new places_visits(name, b, ct);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                modDB.place().insert(places);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void insertlocation(double lt,
                               double ln,float pr) {
        final location_tracking location = new location_tracking(lt,
                ln,pr);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                modDB.location().insert(location);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void insertscreen(String eve, String desc) {

        final screentime_used screen= new screentime_used(eve, desc);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                modDB.screen().insert(screen);
                return null;
            }
        }.execute();
    }
    @SuppressLint("StaticFieldLeak")
    public List<screentime_used> getTasks() {
        new AsyncTask<Void, Void, List<screentime_used>>() {
            @Override
            protected List<screentime_used> doInBackground(Void... voids) {
                return modDB.screen().listall();
            }

            @Override
            protected void onPostExecute(List<screentime_used> screentimes) {
                Log.e("screentimeRepo1", "Db size: "+screentimes.size());
                for (screentime_used element : screentimes) {
                    Log.e("screentimeRepo1", element.screen_event);
                }
                super.onPostExecute(screentimes);
            }
        }.execute();
        return null;
    }
    @SuppressLint("StaticFieldLeak")
    public List<location_tracking> getlocs() {
        new AsyncTask<Void, Void, List<location_tracking>>() {
            @Override
            protected List<location_tracking> doInBackground(Void... voids) {
                return modDB.location().listall();
            }

            @Override
            protected void onPostExecute(List<location_tracking> locations) {
                Log.e("LocationRepo", "Db size: "+locations.size());
                for (location_tracking element : locations) {
                    Log.e("LocationRepo", "Location added");
                }
                super.onPostExecute(locations);
            }
        }.execute();
        return null;
    }
}
