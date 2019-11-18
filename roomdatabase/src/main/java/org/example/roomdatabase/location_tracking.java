package org.example.roomdatabase;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.sql.Time;

@Entity
public class location_tracking {
    public location_tracking(){

    }
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "latitude")
    public double latitude;

    @ColumnInfo(name = "longitude")
    public double longitude;


    @ColumnInfo(name = "accuracy")
    public float accuracy;

    public location_tracking(double lt,double ln,float ac){
        this.latitude=lt;
        this.longitude=ln;
        this.accuracy=ac;
    }

}
