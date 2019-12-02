package com.example.spiseapp3.Menu;

import android.widget.Toast;

import com.example.spiseapp3.R;
import com.example.spiseapp3.ui.CartItem;
import com.example.spiseapp3.ui.CartItemAdapter;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class Lists {
    public static ArrayList<Drow> desserts;
    public static ArrayList<Courserow> maincourse;
    public static ArrayList<Drinksrow> drinks;
    public static ArrayList<CartItem> CartItemsList;
    public static String cart_res_id;
    public static String customer_address;

    public static LatLng user_location;
    public static String res_uid;

    static
    {
        desserts=new ArrayList<Drow>();
        maincourse=new ArrayList<Courserow>();
        drinks=new ArrayList<Drinksrow>();
        CartItemsList= new ArrayList<CartItem>();
    }
    public Lists()
    {
        desserts=new ArrayList<Drow>();
        maincourse=new ArrayList<Courserow>();
        drinks=new ArrayList<Drinksrow>();
        CartItemsList= new ArrayList<CartItem>();
    }


    public static  void addtoCartItemList()
    {
        CartItemsList= new ArrayList<CartItem>();

        //++++
        Boolean found=false;
        //++++

        cart_res_id=res_uid;
        int total=0;
        for(int i=0;i<maincourse.size();i++)
        {
            Courserow c=Lists.maincourse.get(i);
            CartItem ct=new CartItem(c.item_id,c.Name, c.price);

            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            found=false;

            for(int j=0 ;j<CartItemsList.size(); j++) {  //this makes sure that the already present item is not added twice
                if( c.item_id.matches (CartItemsList.get(j).itemid) )
                {
                    found=true;
                }
            }

            if(found==false) {
                CartItemsList.add(ct);
            }

            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=

        }

        for(int i=0;i<desserts.size();i++)
        {
            //++++
            found=false;
            //++++

            Drow c=Lists.desserts.get(i);
            CartItem ct=new CartItem(c.item_id,c.Name, c.price);

            // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            for(int j=0 ;j<CartItemsList.size(); j++) {  //this makes sure that the already present item is not added twice
                if( c.item_id.matches (CartItemsList.get(j).itemid) )
                {
                    found=true;
                }
            }

            if(found==false) {
                CartItemsList.add(ct);
            }

            // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        }
        for(int i=0;i<drinks.size();i++)
        {
            //++++
            found=false;
            //++++

            Drinksrow c=Lists.drinks.get(i);
            CartItem ct=new CartItem(c.item_id,c.DName, c.Dprice);
            // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            for(int j=0 ;j<CartItemsList.size(); j++) {  //this makes sure that the already present item is not added twice
                if( c.item_id.matches (CartItemsList.get(j).itemid) )
                {
                    found=true;
                }
            }

            if(found==false) {
                CartItemsList.add(ct);
            }

            // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        }
    }

    public static String getCart_res_id() {
        return cart_res_id;
    }

    public static void setCart_res_id(String cart_res_id) {
        Lists.cart_res_id = cart_res_id;
    }

    public static String getCustomer_address() {
        return customer_address;
    }

    public static void setCustomer_address(String customer_address) {
        Lists.customer_address = customer_address;
    }


    public static void EMptyAllLists()  //for new Cart
    {
        for(int i=0; i<CartItemsList.size();i++)
        {
            CartItemsList.remove(i);
        }
        for(int i=0; i<desserts.size();i++)
        {
            desserts.remove(i);
        }

        for(int i=0; i<drinks.size();i++)
        {
            drinks.remove(i);
        }
        for(int i=0; i<maincourse.size();i++)
        {
            maincourse.remove(i);
        }
    }



}
