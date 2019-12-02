package com.example.room.Entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class DrinkItem implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String item_id;
    private String Name;
    private String price;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public DrinkItem() {
    }

    public DrinkItem(String item_id, String name, String price) {
        this.item_id = item_id;
        Name = name;
        this.price = price;
    }
}
