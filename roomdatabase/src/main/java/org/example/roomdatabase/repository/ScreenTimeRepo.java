package org.example.roomdatabase.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

import org.example.roomdatabase.ScreenTime;
import org.example.roomdatabase.database.DatabaseClass;

import java.util.List;


public class ScreenTimeRepo {

    private String DB_NAME = "app_core_db";
    private DatabaseClass screentimeDatabase;

    public ScreenTimeRepo(Context context) {
        getInstance(context);
    }

    private DatabaseClass getInstance(Context context){
        if (screentimeDatabase!=null){
            return screentimeDatabase;
        }else{
            screentimeDatabase = Room.databaseBuilder(context, DatabaseClass.class, DB_NAME).build();
        }
        return screentimeDatabase;
    }

    public void insertTask(String title, String description) {
        ScreenTime screentime = new ScreenTime();
        screentime.setTitle(title);
        screentime.setDescription(description);
        insertTask(screentime);
    }

    @SuppressLint("StaticFieldLeak")
    public void insertTask(final ScreenTime screentime) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Log.e("screentimeRepo", "Inserting!!!! from Background Thread: " + Thread.currentThread().getId());
                screentimeDatabase.daoAccess().insertTask(screentime);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void updateTask(final ScreenTime screentime) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                screentimeDatabase.daoAccess().updateTask(screentime);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void deleteTask(final ScreenTime screentime) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                screentimeDatabase.daoAccess().deleteTask(screentime);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private ScreenTime getTask(final int id) {
        new AsyncTask<Void, Void, ScreenTime>() {
            @Override
            protected ScreenTime doInBackground(Void... voids) {
                return screentimeDatabase.daoAccess().getTask(id);
            }

            @Override
            protected void onPostExecute(ScreenTime screentimes) {
                super.onPostExecute(screentimes);
            }
        }.execute();
        return null;
    }

    @SuppressLint("StaticFieldLeak")
    public List<ScreenTime> getTasks() {
        new AsyncTask<Void, Void, List<ScreenTime>>() {
            @Override
            protected List<ScreenTime> doInBackground(Void... voids) {
                return screentimeDatabase.daoAccess().fetchAllTasks();
            }

            @Override
            protected void onPostExecute(List<ScreenTime> screentimes) {
                Log.e("screentimeRepo1", "Db size: "+screentimes.size());
                for (ScreenTime element : screentimes) {
                    Log.e("screentimeRepo1", element.getTitle());
                }
                super.onPostExecute(screentimes);
            }
        }.execute();
        return null;
    }

}
