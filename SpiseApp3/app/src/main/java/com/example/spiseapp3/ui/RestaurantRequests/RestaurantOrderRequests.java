package com.example.spiseapp3.ui.RestaurantRequests;

import android.content.Intent;
import android.os.Bundle;

import com.example.spiseapp3.OrderFiles.Order;
import com.example.spiseapp3.OrderFiles.Orderres;
import com.example.spiseapp3.R;
import com.example.spiseapp3.ui.RestaurantFiles.RestaurantHistory.RestaurantOrderHistory;
import com.example.spiseapp3.ui.RestaurantFiles.RestaurantLogout;
import com.example.spiseapp3.ui.RestaurantFiles.RestaurantProfileFiles.RestaurantProfile;
import com.example.spiseapp3.ui.RestaurantFiles.RestaurantUpdateMenu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;

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
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class RestaurantOrderRequests extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    DrawerLayout drawer;
    NavigationView navigationView;
    DatabaseReference databaseReference;
    ArrayList<Orderres> orders;
    Toolbar toolbar=null;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_order_requests);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //========================FIREBASEEEEEE===================================

        RecyclerView list = findViewById(R.id.rec_order_requests);
        list.setLayoutManager(new LinearLayoutManager(this));
        orders=new ArrayList<Orderres>();
        final RestaurantRequestsAdapter adapter=new RestaurantRequestsAdapter(this, orders);
        list.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance()
                .getReference("Orders");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    final String cus_id=dataSnapshot1.getKey();
                    databaseReference.child(cus_id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot dataSnapshot2: dataSnapshot.getChildren())
                            {
                                final String order_id=dataSnapshot2.getKey();
                                databaseReference.child(cus_id).child(order_id).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        Order o=dataSnapshot.getValue(Order.class);
                                        String hh= FirebaseAuth.getInstance().getCurrentUser().getUid();

                                        if (hh.equals(o.res_id) && o.status.equals("pending")) {
                                            Orderres orderres = new Orderres(o.status, o.date, cus_id, order_id, o.items, o.getCustomer_address());
                                            orders.add(orderres);
                                           adapter.notifyDataSetChanged();
                                        }

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
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        //========================================================================
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_restaurant_order_requests, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        switch (id){


            case R.id.nav_logout:
                finish();
                System.exit(0);
                break;
            case R.id.nav_profile:
                Intent g= new Intent(RestaurantOrderRequests.this, RestaurantProfile.class);
                startActivity(g);
                break;

            case R.id.nav_orders_history:
                Intent t= new Intent(RestaurantOrderRequests.this, RestaurantOrderHistory.class);
                startActivity(t);
                break;
            case R.id.nav_update_menu:
                Intent v= new Intent(RestaurantOrderRequests.this, RestaurantUpdateMenu.class);
                startActivity(v);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
