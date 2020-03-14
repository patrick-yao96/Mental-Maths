package com.example.mentalmath;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ExperiencePoints.class, Task.class},version = 1)
public abstract class ExperiencePointsDatabase extends RoomDatabase {
    //DAOs
    public abstract ExperiencePointsDao experiencePointsDao();
    public abstract TaskDao taskDao();

    private static ExperiencePointsDatabase instance; //creates a database instance

    // Getting an instance of the ExperiencePointsDatabase
    public static ExperiencePointsDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, ExperiencePointsDatabase.class, "MentalMathDb")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
