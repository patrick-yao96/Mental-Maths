package com.example.mentalmath;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM `User Tasks` WHERE question LIKE :question")
    Task findQuestion(String question);

    @Insert(onConflict = IGNORE)
    void insert(Task task);
}
