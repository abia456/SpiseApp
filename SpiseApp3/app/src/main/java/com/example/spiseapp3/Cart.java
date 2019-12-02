package com.example.spiseapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.room.Repos.Repo;
import com.example.spiseapp3.Menu.Courserow;
import com.example.spiseapp3.Menu.Drinksrow;
import com.example.spiseapp3.Menu.Drow;
import com.example.spiseapp3.Menu.Lists;
import com.example.spiseapp3.OrderFiles.Order;
import com.example.spiseapp3.ui.CartItem;
import com.example.spiseapp3.ui.CartItemAdapter;
import com.example.spiseapp3.ui.CustomerOrders.COrders;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cart extends AppCompatActivity {

    CardView payment;
    ArrayList<CartItem> list;
    List<Address> addresses;
    Geocoder geocoder;
    EditText t1;
    TextView t2;
    TextView total_cost;
    TextView sub_total;
    Button Placeorder;
    DatabaseReference databaseReference;
    EditText addr;
    private  final String CHANNEL_ID ="example";
    private final int NOTIFICATION_ID=100;
    ///////////////////////// Retrofit////////////////////////////////////////////////////

    Button retrobtn;

    ///////////////////////////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ////////////////////////// RETROFIT////////////////////////////////////////////

        retrobtn= findViewById(R.id.retroBTN);

        retrobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i= new Intent(Cart.this, retro.class);
                startActivity(i);
            }
        });



//////////////////////////////////////////////////////////////////////////



        //==================FOR LOCATION===============================================
        geocoder = new Geocoder(this, Locale.getDefault());
        if(Lists.user_location!=null) {

            try {
                addresses = geocoder.getFromLocation(Lists.user_location.latitude,
                        Lists.user_location.longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (addresses.get(0) != null) {
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();

                String complete = address + ' ' + city + ' ' + state + ' ' + country;
                Lists.customer_address=complete;
                t1 = findViewById(R.id.addressDesCart);
                t1.setText(complete);

            }
        }


        //=============================================================================
        Placeorder= findViewById(R.id.PlaceOrderBtn);
        addr= findViewById(R.id.addressDesCart);
        Placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Lists.CartItemsList==null)
                {
                    Toast.makeText(Cart.this,"Cart is empty", Toast.LENGTH_SHORT).show();


                }
                  else if(Lists.CartItemsList.size()==0)
                  {


                        Toast.makeText(Cart.this, "Cart is empty", Toast.LENGTH_SHORT).show();
                  }


                else if(addr.getText().toString().matches("Not available"))
                {

                    Toast.makeText(Cart.this,"Cart is empty", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Order o=new Order();
                    o.update();
                    o.setCustomer_address(t1.getText().toString());

                    //==========================FIREBASE=====================================

                    databaseReference=FirebaseDatabase.getInstance().getReference("Orders")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                   String kk=databaseReference.push().getKey();
                    databaseReference.child(kk).setValue(o);
                   // for(int i=0;i<o.items.size();i++)
                    //{
                     //   databaseReference.child(kk).setValue(o.items.get(i));
                    //}
                    Toast.makeText(Cart.this, "Orderadded id: " + kk, Toast.LENGTH_SHORT).show();

                    //====================NOTIFICATION====================================

                    Intent intent = new Intent(Cart.this, COrders.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent pendingIntent = PendingIntent.getActivity(Cart.this, 0, intent, 0);
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(Cart.this,CHANNEL_ID)
                            .setSmallIcon(R.drawable.logo)
                            .setContentTitle("Your Order has been placed")
                            .setContentText("")
                            .setContentIntent(pendingIntent)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(Cart.this);

// notificationId is a unique int for each notification that you must define
                    notificationManager.notify(NOTIFICATION_ID, builder.build());



                    //===================================================================











                    //========================================================================

                }

            }
        });


        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        list=new ArrayList<CartItem>();
        int total=0;
        //for(int i=0;i<Lists.maincourse.size();i++)
        //{
          //  Courserow c=Lists.maincourse.get(i);
           // CartItem ct=new CartItem(c.item_id,c.Name, c.price);
            //list.add(ct);
            //String temp=c.price;
            //String temp2=temp.replace("Rs ", "");
            //total=total+Integer.parseInt(temp2);
        //}
        //for(int i=0;i<Lists.desserts.size();i++)
        //{
            //Drow c=Lists.desserts.get(i);
            //CartItem ct=new CartItem(c.item_id,c.Name, c.price);
            //list.add(ct);
           // String temp=c.price;
            //String temp2=temp.replace("Rs ", "");
            //total=total+Integer.parseInt(temp2);
            //String q= c.quantity
        //}
        //for(int i=0;i<Lists.drinks.size();i++)
        //{
          //  Drinksrow c=Lists.drinks.get(i);
           // CartItem ct=new CartItem(c.item_id,c.DName, c.Dprice);
            //list.add(ct);
            //String temp=c.Dprice;
            //String temp2=temp.replace("Rs ", "");
            //total=total+Integer.parseInt(temp2);
        //}

        //for(int i=0 ;i<Lists.CartItemsList.size();i++)
        //{
           // String temp=Lists.CartItemsList.get(i).Price;

            //String temp2=temp.replace("Rs ", "");
            //int price= Integer.parseInt(temp2);
            //int quan= Integer.parseInt(Lists.CartItemsList.get(i).quantity);
            //price= price * quan;
            //total=total+price;

        //}




        String t= calculate();

        sub_total=findViewById(R.id.Subtotalcart);
        total_cost=findViewById(R.id.total_cost);
        sub_total.setText(t);

        total_cost.setText(t);

        //=---------===============RETRIEVE FROM SHARED PREFERENCES==========
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);

        String mail=pref.getString("email", null); // getting String
        t2=findViewById(R.id.userEmailCart);
        t2.setText(mail);



        //====================================================================

        RecyclerView recyclerViewDemo = findViewById(R.id.RecycleViewCart);
        recyclerViewDemo.setLayoutManager(new LinearLayoutManager(this));
        CartItemAdapter a= new CartItemAdapter(getApplicationContext(), Lists.CartItemsList);
        recyclerViewDemo.setAdapter(a);

        payment= findViewById(R.id.paymentcardview);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent (Cart.this,Paymentoptions.class);
                startActivity(intent);
            }

        });


    }


    public static String calculate()
    {

        int total=0;



        for(int i=0 ;i<Lists.CartItemsList.size();i++)
        {

            String temp=Lists.CartItemsList.get(i).Price;

            String temp2=temp.replace("Rs ", "");
            int price= Integer.parseInt(temp2);
            int quan= Integer.parseInt(Lists.CartItemsList.get(i).quantity);
            price= price * quan;
            total=total+price;


        }


        String t= Integer.toString(total);
        return t;
   }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(CHANNEL_ID);
            String description = getString("basic");
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private String getString(String channel_id) {
        return CHANNEL_ID;
    }
}

