package com.example.room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.room.Entities.DessertItem;
import com.example.room.Entities.DrinkItem;

import java.util.List;

@Dao
public interface DaoDrink {
    @Insert
    Long insertTask(DrinkItem drinkItem);


    @Query("SELECT * FROM DrinkItem")
    List<DrinkItem> fetchAllTasks();


    @Query("SELECT * FROM DrinkItem WHERE id =:taskId")
    DrinkItem getTask(int taskId);


    @Update
    void updateTask(DrinkItem drinkItem);


    @Delete
    void deleteTask(DrinkItem drinkItem);
}
