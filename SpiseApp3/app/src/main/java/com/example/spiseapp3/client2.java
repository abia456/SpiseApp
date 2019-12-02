package com.example.spiseapp3;

import java.util.ArrayList;

public class client2 {


    public String uid;
    public String Fname;
    public String Sname;
    public String Email;
    public String RestAddress;
    public String Phone;
    public String resName;
    public String city;
    public String cuisine;
    public String filter;
    public String DeliveryCost;
    public String DeliveryTime;
    public String Ratings;

    ///////////////////// MENU /////////////////////////////////
    ArrayList<Desserts> DessertsList;
    ArrayList<Drinks> DrinksList;
    ArrayList<MainCourse> MainCOurseList;
    //////////////////////////////////////////////////////////


    public client2(String uid, String fname, String sname, String email, String restAddress, String phone, String resName, String city, String cuisine, String filter, String deliveryCost, String deliveryTime, String ratings) {
        this.uid=uid;
        Fname = fname;
        Sname = sname;
        Email = email;
        RestAddress = restAddress;
        Phone = phone;
        this.resName = resName;
        this.city = city;
        this.cuisine = cuisine;
        this.filter = filter;
        DeliveryCost = deliveryCost;
        DeliveryTime = deliveryTime;
        this.Ratings = ratings;

        //////////////////////////////////////////// MENU ///////////////////////////
        DessertsList= new ArrayList<Desserts>();
        DrinksList= new ArrayList<Drinks>();
        MainCOurseList= new ArrayList<MainCourse>();
        ////////////////////////////////////////////////////////////////////////////



    }

    public void addDessert(Desserts A)
    {
        DessertsList.add(A);
    }


    public client2() {
    }
}


