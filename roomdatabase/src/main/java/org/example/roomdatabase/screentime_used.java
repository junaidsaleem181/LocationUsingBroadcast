package org.example.roomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class screentime_used {
    public screentime_used(){

    }
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "screen_event")
    public String screen_event;

    @ColumnInfo(name = "Description")
    public String desc;

    public screentime_used(String e,String d){
        this.screen_event=e;
        this.desc=d;
    }

}

