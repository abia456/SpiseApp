package com.example.spiseapp3;

import android.content.Intent;
import android.os.Bundle;

import com.example.spiseapp3.ui.CAddressesFolder.CAddresses;
import com.example.spiseapp3.ui.CLogout;
import com.example.spiseapp3.ui.CustomerLocationFiles.CustomerLocation;
import com.example.spiseapp3.ui.CustomerLocationFiles.CustomerPickupLocation;
import com.example.spiseapp3.ui.CustomerOrders.COrders;
import com.example.spiseapp3.ui.CustomerProfile.CProfile;
import com.example.spiseapp3.ui.RestaurantRequests.RestaurantOrderRequests;
import com.example.spiseapp3.ui.ViewRestaurantsAdapter;
import com.google.android.gms.auth.api.signin.internal.Storage;
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
import com.google.firebase.storage.FirebaseStorage;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class chome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
,AdapterView.OnItemSelectedListener
{

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;
    private Spinner spinner1;
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    DatabaseReference ref2;
    String[] location = { "Current Location", "Add Location" };
    ArrayList<Client> list;
    ArrayList<Upload> list2;
//////////////////////////////////////////////// Search filter //////////////////////////////////
    ArrayList<String> g;
    ArrayList<String> g1;
    ArrayList<String> g2;

    Boolean Search=false;
////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chome);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
////////////////////////////// Search Filter////////////////////////////

        g=new ArrayList<String>();
        if( getIntent().getExtras()!= null)
        {
            Intent intent= getIntent();
            // ArrayList<CheckBox> C = getIntent().getExtras().get("CuisineList");
            g= intent.getExtras().getStringArrayList("CuisineList");
            g1= intent.getExtras().getStringArrayList("PriceList");
            g2= intent.getExtras().getStringArrayList("DeliveryPriceList");

            if(g.size()>0 || g1.size()>0 || g2.size()>0)
            {
                Toast.makeText(chome.this, "size" + g.size(), Toast.LENGTH_SHORT).show();

                Search=true;}
        }
///////////////////////////////////////////////////////////////////////


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (chome.this,Cart.class);
                startActivity(intent);
            }
        });
        ArrayList<String> uids=new ArrayList<String>();
        //-------FOR SPINNER-----
        spinner1 = (Spinner) findViewById(R.id.spinner_location);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, location);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

        spinner1.setPrompt("Select Location");





        //--------------------fot databse----------------------


        databaseReference= FirebaseDatabase.getInstance().getReference("Client");
        databaseReference.keepSynced(true);

        list=new ArrayList<Client>();
        list2=new ArrayList<Upload>();
        ref2=FirebaseDatabase.getInstance().getReference("Upload");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {

                    final Client c=dataSnapshot1.getValue(Client.class);
                    String uid=dataSnapshot1.getKey();
                    c.setUid(uid);


                   ref2.child(uid).addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           Toast.makeText(chome.this, "hereeeeeee", Toast.LENGTH_SHORT).show();


                           Upload u=dataSnapshot.getValue(Upload.class);
                           /////////////////////////////////////////////////////////
                           if(Search==true)
                           {
                               boolean stop = false;
                               String CuisineFIlter= c.cuisine;
                               for (int i = 0; i < g.size() && stop==false ; i++) {
                                   if (CuisineFIlter.matches(g.get(i))) {
                                       // if the cuisine qualifies the cuisine filter then show
                                       list2.add(u);
                                       stop=true;
                                   }
                               }

                               /////////////////////// price ////////////////////

                                stop = false;

                               String PriceFIlter= c.filter;
                               for (int i = 0; i < g1.size() && stop==false ; i++) {
                                   if (PriceFIlter.matches(g1.get(i))) {
                                       // if the price qualifies the cuisine filter then show
                                       list2.add(u);
                                       stop=true;
                                   }
                               }


                               //////////////////////////////////////////


                               //////////////////////// Delivery Price //////////////////////


                               stop = false;

                               String DPriceFIlter= c.DeliveryCost;
                               for (int i = 0; i < g2.size() && stop==false ; i++) {
                                   if (DPriceFIlter.matches(g2.get(i))) {
                                       // if the price qualifies the cuisine filter then show
                                       list2.add(u);
                                       stop=true;
                                   }
                               }


                               //////////////////////////////////////////////////////////

                           }

                           /////////////////////////////////////////////////
                           else {
                               list2.add(u);
                           }
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
                    /////////////////////////////////////////////////////////
                    if(Search==true)
                    {
                        Toast.makeText(chome.this, "search true" + g.size(), Toast.LENGTH_SHORT).show();

                        String CuisineFIlter= c.cuisine;

                        for (int i = 0; i < g.size(); i++) {
                            //Toast.makeText(chome.this, "search true " + CuisineFIlter, Toast.LENGTH_SHORT).show();
                            //Toast.makeText(chome.this, "search true " + g.get(i), Toast.LENGTH_SHORT).show();

                            if (CuisineFIlter.matches(g.get(i)) ) {
                                // if the cuisine qualifies the cuisine filter then show
                                Toast.makeText(chome.this, "cuinsine added" + g.size(), Toast.LENGTH_SHORT).show();

                                list.add(c);
                            }
                        }

                        ////////////////////////////////////////////////////////////////////


                        /////////////////////// price ////////////////////

                        boolean stop = false;

                        String PriceFIlter= c.filter;
                        for (int i = 0; i < g1.size() && stop==false ; i++) {
                            if (PriceFIlter.matches(g1.get(i))) {
                                // if the price qualifies the cuisine filter then show
                                list.add(c);
                                stop=true;
                            }
                        }


                        //////////////////////////////////////////


                        //////////////////////// Delivery Price //////////////////////


                        stop = false;

                        String DPriceFIlter= c.DeliveryCost;
                        for (int i = 0; i < g2.size() && stop==false ; i++) {
                            if (DPriceFIlter.matches(g2.get(i))) {
                                // if the price qualifies the cuisine filter then show
                                list.add(c);
                                stop=true;
                            }
                        }


                        //////////////////////////////////////////////////////////


                    }
                    /////////////////////////////////////////////////


                    else {
                        list.add(c);
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //___________________________________________
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ImageView filter;
        ////////////////////////// changes /////////////////////////
        RecyclerView recyclerViewDemo= findViewById(R.id.RecyclerViewRestaurants);
        recyclerViewDemo.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDemo.setAdapter(new ViewRestaurantsAdapter( getApplicationContext(), list, list2 ));

        filter= findViewById(R.id.filersearch);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent (chome.this,SearchFilter.class);
                startActivity(intent);
            }

        });
        /////////////////////////////////////////////////////////




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
        getMenuInflater().inflate(R.menu.chome, menu);
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
            Intent i= new Intent(chome.this, CLogout.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        //here is the main place where we need to work on.
        int id=item.getItemId();
        switch (id){


            case R.id.nav_corders:
                Intent i= new Intent(chome.this, COrders.class);
                startActivity(i);
                break;
            case R.id.nav_caddresses:
                Intent g= new Intent(chome.this, CAddresses.class);
                startActivity(g);
                break;
            case R.id.nav_cprofile:
                Intent s= new Intent(chome.this, CProfile.class);
                startActivity(s);
                break;
            case R.id.nav_logout:
                finish();
                System.exit(0);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Object itemAtPosition = adapterView.getItemAtPosition(i);
        if (itemAtPosition == "Current Location") {
            Intent intent = new Intent(chome.this, CustomerLocation.class);
            startActivity(intent);
        } else if (itemAtPosition == "Add Location") {
            Intent intent2 = new Intent(chome.this, CustomerPickupLocation.class);
            startActivity(intent2);
        }
    }



    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        spinner1.setPrompt("Select a location");
    }
    //------------ SPINNER----------

    //-------------------------TO SAVE STATE---------

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("int1", spinner1.getSelectedItemPosition());
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int s = savedInstanceState.getInt("int1");
        spinner1.setSelection(s);
    }

}
