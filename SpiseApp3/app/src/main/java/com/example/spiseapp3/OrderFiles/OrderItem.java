package com.example.spiseapp3.OrderFiles;

public class OrderItem {
    public String item_id;
    public String quantity;

    public OrderItem() {
    }

    public OrderItem(String item_id, String quantity) {
        this.item_id = item_id;
        this.quantity = quantity;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
