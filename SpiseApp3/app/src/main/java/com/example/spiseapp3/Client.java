package com.example.spiseapp3;

import java.util.ArrayList;

public class Client {

    String uid;
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


    public Client(String fname, String sname, String email, String restAddress, String phone, String resName, String city, String cuisine, String filter, String deliveryCost, String deliveryTime, String ratings) {
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
    public Client(String fname, String sname, String email, String restAddress, String phone, String resName, String city, String cuisine, String filter, String deliveryCost, String deliveryTime, String ratings
    , ArrayList<Drinks>Drinks, ArrayList<Desserts> Desserts, ArrayList<MainCourse>MainCourse) {
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
        DessertsList= Desserts;
        DrinksList= Drinks;
        MainCOurseList= MainCourse;
        ////////////////////////////////////////////////////////////////////////////



    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void addDessert(Desserts A)
    {
        DessertsList.add(A);
    }


    public Client() {
    }
}




//////////////////////////////////For Menu///////////////////////////////////////////////////////
class Desserts
{
    String Title;
    String Price;
    String Des;

    public Desserts(){}

    public Desserts(String t, String P,String D)
    {

        Title=t;
        Price=P;
        Des= D;

    }
}

class Drinks
{
    String Title;
    String Price;

    public Drinks(){}

    public Drinks(String t, String P)
    {

        Title=t;
        Price=P;

    }

}


class MainCourse
{
    String Title;
    String Price;
    String Des;

    public MainCourse(){}

    public MainCourse(String t, String P,String D)
    {

        Title=t;
        Price=P;
        Des= D;

    }
}



/////////////////////////////////////////////////////////////////////////////////////////////
