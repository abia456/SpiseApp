package com.example.spiseapp3.OrderFiles;

import java.util.ArrayList;

public class Orderres {
    public String status;
    public String date;
    public String customer_id;
    public String order_id;
    public ArrayList<OrderItem> items;
    public String cus_address;

    public Orderres() {
    }

    public Orderres(String status, String date, String customer_id, String order_id, ArrayList<OrderItem> items, String cus_address) {
        this.status = status;
        this.date = date;
        this.customer_id = customer_id;
        this.order_id = order_id;
        this.items = items;
        this.cus_address = cus_address;
    }
}
