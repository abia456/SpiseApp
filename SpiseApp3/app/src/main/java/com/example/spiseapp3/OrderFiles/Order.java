package com.example.spiseapp3.OrderFiles;

import com.example.spiseapp3.Menu.Lists;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    public String status;
    public String date;
    public String res_id;
    public ArrayList<OrderItem> items;
    String customer_address;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRes_id() {
        return res_id;
    }

    public void setRes_id(String res_id) {
        this.res_id = res_id;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public Order() {
        items=new ArrayList<OrderItem>();
    }

    public Order(String status, String date, String res_id, ArrayList<OrderItem> items, String customer_address) {
        this.status = status;
        this.date = date;
        this.res_id = res_id;
        this.customer_address = customer_address;
        this.items = items;

    }
    public void update()
    {
        status="pending";
        Date d=new Date();
        date=d.toString();
        res_id= Lists.cart_res_id;
        customer_address=Lists.getCustomer_address();
        for(int i=0;i<Lists.CartItemsList.size();i++)
        {
            OrderItem o=new OrderItem(Lists.CartItemsList.get(i).itemid,Lists.CartItemsList.get(i).quantity);
            items.add(o);
        }


    }
}
