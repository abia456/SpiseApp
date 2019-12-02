package com.example.room.Repos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.room.Room;

import com.example.room.Database.DatabaseClass;
import com.example.room.Entities.DessertItem;
import com.example.room.Entities.DrinkItem;
import com.example.room.Entities.MainCourseItem;

import java.util.List;

public class Repo {


    private String DB_NAME = "app_core_db";
    private DatabaseClass database;

    public Repo(Context context) {
        getInstance(context);
    }

    private DatabaseClass getInstance(Context context){
        if (database!=null){
            return database;
        }else{
            database = Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseClass.class, DB_NAME)
                    .fallbackToDestructiveMigration() //to cater version number
                    .build();
        }
        return database;
    }

    public void insertTask(String item_id, String name, String des, String price) {
        DessertItem dessertItem = new DessertItem();
        dessertItem.setItem_id(item_id);
        dessertItem.setName(name);
        dessertItem.setDescription(des);
        dessertItem.setPrice(price);
        insertTask(dessertItem);
    }

    @SuppressLint("StaticFieldLeak")
    public void insertTask(final DessertItem dessertItem) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Log.e("DessertRepo", "Inserting!!!! from Background Thread: " + Thread.currentThread().getId());
                database.daoDessert().insertTask(dessertItem);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void updateTask(final DessertItem dessertItem) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.daoDessert().updateTask(dessertItem);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void deleteTask(final DessertItem dessertItem) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.daoDessert().deleteTask(dessertItem);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private DessertItem getTask(final int id) {
        new AsyncTask<Void, Void, DessertItem>() {
            @Override
            protected DessertItem doInBackground(Void... voids) {
                return database.daoDessert().getTask(id);
            }

            @Override
            protected void onPostExecute(DessertItem dessertItem) {
                super.onPostExecute(dessertItem);
            }
        }.execute();
        return null;
    }

    @SuppressLint("StaticFieldLeak")
    public List<DessertItem> getTasks() {
        new AsyncTask<Void, Void, List<DessertItem>>() {
            @Override
            protected List<DessertItem> doInBackground(Void... voids) {
                return database.daoDessert().fetchAllTasks();
            }

            @Override
            protected void onPostExecute(List<DessertItem> dessertItems) {
                Log.e("Repo", "Db size: "+dessertItems.size());
                for (DessertItem element : dessertItems) {
                    Log.e("Repo1", element.getName());
                }
                super.onPostExecute(dessertItems);
            }
        }.execute();
        return null;
    }
    //====================FOR DRINKS===================

    public void insertTaskDrink(String item_id, String name, String price) {
        DrinkItem drinkItem = new DrinkItem();
        drinkItem.setItem_id(item_id);
        drinkItem.setName(name);

        drinkItem.setPrice(price);
        insertTaskDrink(drinkItem);
    }

    @SuppressLint("StaticFieldLeak")
    public void insertTaskDrink(final DrinkItem drinkItem) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Log.e("DessertRepo", "Inserting drink!!!! from Background Thread: " + Thread.currentThread().getId());
                database.daoDrink().insertTask(drinkItem);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void updateTaskDrink(final DrinkItem drinkItem) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.daoDrink().updateTask(drinkItem);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void deleteTaskDrink(final DrinkItem drinkItem) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.daoDrink().deleteTask(drinkItem);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private DrinkItem getTaskDrink(final int id) {
        new AsyncTask<Void, Void, DrinkItem>() {
            @Override
            protected DrinkItem doInBackground(Void... voids) {
                return database.daoDrink().getTask(id);
            }

            @Override
            protected void onPostExecute(DrinkItem drinkItem) {
                super.onPostExecute(drinkItem);
            }
        }.execute();
        return null;
    }

    @SuppressLint("StaticFieldLeak")
    public List<DrinkItem> getTasksDrink() {
        new AsyncTask<Void, Void, List<DrinkItem>>() {
            @Override
            protected List<DrinkItem> doInBackground(Void... voids) {
                return database.daoDrink().fetchAllTasks();
            }

            @Override
            protected void onPostExecute(List<DrinkItem> drinkItems) {
                Log.e("screentimeRepo1", "Db size: "+drinkItems.size());
                for (DrinkItem element : drinkItems) {
                    Log.e("screentimeRepo1", element.getName());
                }
                super.onPostExecute(drinkItems);
            }
        }.execute();
        return null;
    }


    //====================MAIN COURSE====================


    public void insertTaskMainCourse(String item_id, String name, String des, String price) {
        MainCourseItem mainCourseItem = new MainCourseItem();
        mainCourseItem.setItem_id(item_id);
        mainCourseItem.setName(name);
        mainCourseItem.setDescription(des);
        mainCourseItem.setPrice(price);
        insertTaskMainCourse(mainCourseItem);
    }

    @SuppressLint("StaticFieldLeak")
    public void insertTaskMainCourse(final MainCourseItem mainCourseItem) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Log.e("MainCourseRepo", "Inserting!!!! from Background Thread: " + Thread.currentThread().getId());
                database.daoMainCourse().insertTask(mainCourseItem);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void updateTaskMainCourse(final MainCourseItem mainCourseItem) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.daoMainCourse().updateTask(mainCourseItem);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void deleteTaskMainCourse(final MainCourseItem mainCourseItem) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.daoMainCourse().deleteTask(mainCourseItem);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private MainCourseItem getTaskMainCourse(final int id) {
        new AsyncTask<Void, Void, MainCourseItem>() {
            @Override
            protected MainCourseItem doInBackground(Void... voids) {
                return database.daoMainCourse().getTask(id);
            }

            @Override
            protected void onPostExecute(MainCourseItem mainCourseItem) {
                super.onPostExecute(mainCourseItem);
            }
        }.execute();
        return null;
    }

    @SuppressLint("StaticFieldLeak")
    public List<MainCourseItem> getTasksMainCourse() {
        new AsyncTask<Void, Void, List<MainCourseItem>>() {
            @Override
            protected List<MainCourseItem> doInBackground(Void... voids) {
                return database.daoMainCourse().fetchAllTasks();
            }

            @Override
            protected void onPostExecute(List<MainCourseItem> mainCourseItems) {
                Log.e("Repo", "Db size: "+mainCourseItems.size());
                for (MainCourseItem element : mainCourseItems) {
                    Log.e("Repo1", element.getName());
                }
                super.onPostExecute(mainCourseItems);
            }
        }.execute();
        return null;
    }

}
