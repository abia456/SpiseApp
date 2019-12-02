package com.example.spiseapp3.Menu;

////////////////////////////////////////////
public class Drow
{
    public String item_id;
    public String Name;
    public String Description;
    public String price;
    public String quantity;

    public Drow(String name, String description, String price) {
        item_id=null;
        Name = name;
        Description = description;
        this.price = price;
    }

    public Drow(String item_id,String name, String description, String price) {
        item_id=item_id;
        Name = name;
        Description = description;
        this.price = price;
    }

    public Drow() {
    }

    public Drow(String item_id, String name, String description, String price, String quantity) {
        this.item_id = item_id;
        Name = name;
        Description = description;
        this.price = price;
        this.quantity = quantity;
    }

}
