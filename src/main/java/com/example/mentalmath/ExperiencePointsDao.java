package com.example.mentalmath;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ExperiencePointsDao {
    @Query("SELECT * FROM experiencepoints WHERE userXP LIKE :user")
    ExperiencePoints findXPWithUserXP(String user);

    @Insert (onConflict = IGNORE)
    void insert( ExperiencePoints experiencePoints);


    @Query ("UPDATE experiencepoints SET XP = :XP WHERE userXP = :user")
    int updateXP ( String user, int XP);
}
