package com.example.mentalmath;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "experiencepoints")
public class ExperiencePoints {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "userXP")
    public String user;


    @NonNull
    @ColumnInfo(name = "XP")
    public int XP;


    public ExperiencePoints(@NonNull String user,  @NonNull int XP) {

        this.user = user;
        this.XP = XP;
    }


    @NonNull
    public String getUser() {
        return user;
    }

    public int getXP() {
        return XP;
    }



}
