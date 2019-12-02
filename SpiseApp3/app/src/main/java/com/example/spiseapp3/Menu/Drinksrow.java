package com.example.spiseapp3.Menu;

public class Drinksrow
{

    public String item_id;
    public String DName;
    public String Dprice;
    public String quantity;


    public Drinksrow(String name, String price) {
        item_id=null;
        DName = name;
        this.Dprice = price;
    }

    public Drinksrow(String item_id,String name, String price) {
        this.item_id=item_id;
        DName = name;
        this.Dprice = price;
    }

    public Drinksrow() {
    }

    ///////////////////////////////////////////////
    public Drinksrow(String item_id, String DName, String dprice, String quantity) {
        this.item_id = item_id;
        this.DName = DName;
        Dprice = dprice;
        this.quantity = quantity;
    }
    ////////////////////////////////////////////
}