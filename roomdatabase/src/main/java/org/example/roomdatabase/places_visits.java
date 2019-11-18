package org.example.roomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.sql.Time;

@Entity
public class places_visits {
    public places_visits(){

    }
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "place_name")
    public String place_name;

    @ColumnInfo(name = "checkin")
    public Boolean checkin;

    @ColumnInfo(name = "custom_text")
    public String custom_text;

    public places_visits(String p,Boolean b,String ct){
        this.place_name=p;
        this.checkin=b;
        this.custom_text=ct;
    }
}

