package com.example.room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.room.Entities.DessertItem;

import java.util.List;

@Dao
public interface DaoDessert {
    @Insert
    Long insertTask(DessertItem dessertItem);


    @Query("SELECT * FROM DessertItem")
    List<DessertItem> fetchAllTasks();


    @Query("SELECT * FROM DessertItem WHERE id =:taskId")
    DessertItem getTask(int taskId);


    @Update
    void updateTask(DessertItem dessertItem);


    @Delete
    void deleteTask(DessertItem dessertItem);
}
