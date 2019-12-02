package com.example.spiseapp3.ui;

public class CartItem
{
    public String itemid;
    public String ItemName;
    public String Price;
    public String quantity;



    public CartItem(String itemName, String price) {
        ItemName = itemName;
        Price = price;

    }

    public CartItem(String itemid, String itemName, String price) {
        this.itemid = itemid;
        ItemName = itemName;
        Price = price;
        quantity="1";
    }

    public CartItem() {
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
