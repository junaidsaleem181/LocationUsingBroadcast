package org.example.simplifiedlocation;
import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.example.geofencing.SavedLocation;
import org.example.location.GPS_Service;
import org.example.roomdatabase.database.ModuleRepository;
import org.example.screen.ScreenTimeFunctionality;

public class MainActivity extends AppCompatActivity {

    private Button btn_start, btn_stop;
    private TextView textView;
    private BroadcastReceiver broadcastReceiver;
    ScreenTimeFunctionality screenTimeFunctionality;
    ModuleRepository db;
    SavedLocation check=new SavedLocation();
    int i=0;

    @Override
    protected void onResume() {
        super.onResume();
        if(broadcastReceiver == null){
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String data=intent.getStringExtra("coordinates");
                    String[] params=data.split(" ");
                    db.insertlocation(Double.parseDouble(params[0]),Double.parseDouble(params[1]),1);
                    String address=intent.getStringExtra("address");
                    textView.append("\n" + data);
                    if(check.checkedInToFast(address))
                    {
                        if(i==0)
                        {
                            Toast.makeText(context,"Welcome to FAST-NUCES",Toast.LENGTH_SHORT).show();
                        }
                        i++;
                    }
                    else
                    {
                        Toast.makeText(context,address,Toast.LENGTH_SHORT).show();
                    }
                }
            };
        }
        registerReceiver(broadcastReceiver,new IntentFilter("location_update"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(broadcastReceiver != null){
            unregisterReceiver(broadcastReceiver);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = (Button) findViewById(R.id.button);
        btn_stop = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.textView);
        screenTimeFunctionality = new ScreenTimeFunctionality();
        if(!runtime_permissions())
        {
            enable_buttons();
        }
    }

    private void enable_buttons() {

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screenTimeFunctionality.registerEvents(getApplicationContext());
                db=new ModuleRepository(getApplicationContext());
                Intent i =new Intent(getApplicationContext(), GPS_Service.class);
                startService(i);
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screenTimeFunctionality.unregisterEvents(getApplicationContext());
                Intent i = new Intent(getApplicationContext(),GPS_Service.class);
                stopService(i);
            }
        });

    }

    private boolean runtime_permissions() {
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},100);

            return true;
        }
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            if( grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                enable_buttons();
            }else {
                runtime_permissions();
            }
        }
    }
}
