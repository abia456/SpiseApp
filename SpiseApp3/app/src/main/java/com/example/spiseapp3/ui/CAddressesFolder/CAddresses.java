package com.example.spiseapp3.ui.CAddressesFolder;

import android.content.Intent;
import android.os.Bundle;

import com.example.spiseapp3.R;
import com.example.spiseapp3.chome;
import com.example.spiseapp3.ui.RestaurantRequests.RestaurantRequestsAdapter;
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

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;

public class CAddresses extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caddresses);
        RecyclerView list = findViewById(R.id.rec_customer_addresses);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new CAddressesAdapter(this));

    }
}
