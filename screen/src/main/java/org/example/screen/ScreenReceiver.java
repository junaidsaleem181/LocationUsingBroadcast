package org.example.screen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import org.example.roomdatabase.repository.ScreenTimeRepo;


public class ScreenReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Screen is on, saving in database", Toast.LENGTH_SHORT).show();
        ScreenTimeRepo screenTimeRepo = new ScreenTimeRepo(context);
        String title = "Screen On Event";
        String description = "Time in Miliseconds: "+System.currentTimeMillis();
        screenTimeRepo.insertTask(title, description);
        screenTimeRepo.getTasks();
    }
}
