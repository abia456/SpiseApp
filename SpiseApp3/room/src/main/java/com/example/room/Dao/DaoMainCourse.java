package com.example.room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.room.Entities.DrinkItem;
import com.example.room.Entities.MainCourseItem;

import java.util.List;

@Dao
public interface DaoMainCourse {
    @Insert
    Long insertTask(MainCourseItem mainCourseItem);


    @Query("SELECT * FROM MainCourseItem")
    List<MainCourseItem> fetchAllTasks();


    @Query("SELECT * FROM MainCourseItem WHERE id =:taskId")
    MainCourseItem getTask(int taskId);


    @Update
    void updateTask(MainCourseItem mainCourseItem);


    @Delete
    void deleteTask(MainCourseItem mainCourseItem);
}
