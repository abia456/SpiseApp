package com.example.spiseapp3.Menu;

public class Courserow
{
    public String item_id;
    public String Name;
    public String Description;
    public String price;
    public String quantity;

    public Courserow() {
    }

    public Courserow(String name, String description, String price) {
        item_id=null;
        Name = name;
        Description = description;
        this.price = price;
    }
    public Courserow(String id,String name, String description, String price) {
        item_id=id;
        Name = name;
        Description = description;
        this.price = price;
    }

    public Courserow(String item_id, String name, String description, String price, String quantity) {
        this.item_id = item_id;
        Name = name;
        Description = description;
        this.price = price;
        this.quantity = quantity;
    }

}
