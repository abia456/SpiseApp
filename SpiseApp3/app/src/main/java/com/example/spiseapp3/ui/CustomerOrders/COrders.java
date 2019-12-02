package com.example.spiseapp3.ui.CustomerOrders;

import android.content.Intent;
import android.os.Bundle;

import com.example.spiseapp3.Cart;
import com.example.spiseapp3.Client;
import com.example.spiseapp3.Menu.DessertsAdapter;
import com.example.spiseapp3.OrderFiles.Order;
import com.example.spiseapp3.OrderFiles.OrderItem;
import com.example.spiseapp3.R;
import com.example.spiseapp3.SignUpClient;
import com.example.spiseapp3.Upload;
import com.example.spiseapp3.chome;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.Toast;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class COrders extends AppCompatActivity  {
    DatabaseReference databaseReference;
    ArrayList<Order>orders;
    ArrayList<String>order_ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corders);
        //===============================================================
        orders=new ArrayList<Order>();
        order_ids=new ArrayList<String>();

        RecyclerView list = findViewById(R.id.rec_orders);
        list.setLayoutManager(new LinearLayoutManager(this));
        final COrdersAdapter adapter=new COrdersAdapter(this,orders, order_ids);

        list.setAdapter(adapter);



        databaseReference= FirebaseDatabase.getInstance().getReference("Orders")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {

                    String order_id=dataSnapshot1.getKey();
                    order_ids.add(order_id);
                    adapter.notifyDataSetChanged();


                    databaseReference.child(order_id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                            Order o = dataSnapshot.getValue(Order.class);
                            orders.add(o);

                            adapter.notifyDataSetChanged();


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //==============================================================


    }

}
