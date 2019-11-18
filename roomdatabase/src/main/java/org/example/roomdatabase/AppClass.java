package org.example.roomdatabase;

import android.app.Application;

import com.facebook.stetho.Stetho;


public class AppClass extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
