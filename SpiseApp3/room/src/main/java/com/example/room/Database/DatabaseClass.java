package com.example.room.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.room.Dao.DaoDessert;
import com.example.room.Dao.DaoDrink;
import com.example.room.Dao.DaoMainCourse;
import com.example.room.Entities.DessertItem;
import com.example.room.Entities.DrinkItem;
import com.example.room.Entities.MainCourseItem;

@Database(entities = {DessertItem.class, DrinkItem.class, MainCourseItem.class}, version = 1, exportSchema = false)
public abstract class DatabaseClass extends RoomDatabase {

    public abstract DaoDessert daoDessert();
    public abstract DaoDrink daoDrink();
    public abstract DaoMainCourse daoMainCourse();
}
