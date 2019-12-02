package com.example.room.Entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class DessertItem implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String item_id;
    private String Name;
    private String Description;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public DessertItem(String item_id, String name, String description, String price) {
        this.item_id = item_id;
        Name = name;
        Description = description;
        this.price = price;
    }

    public DessertItem() {
    }
}
