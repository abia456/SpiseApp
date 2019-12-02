package com.example.spiseapp3.ui.CustomerProfile;

import android.content.Intent;
import android.os.Bundle;

import com.example.spiseapp3.Customer;
import com.example.spiseapp3.R;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

public class CProfile extends AppCompatActivity  {
    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    TextView t5;
    FirebaseUser user;
    String uid;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cprofile);
        t1 = findViewById(R.id.edit);
        t2=findViewById(R.id.name);
        t3=findViewById(R.id.Lname);
        t4=findViewById(R.id.email);
        t5=findViewById(R.id.phone);
        t1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(CProfile.this, editCProfile.class);
                startActivity(i);
            }
        });


        databaseReference= FirebaseDatabase.getInstance().getReference("Customer");
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        databaseReference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Customer c=dataSnapshot.getValue(Customer.class);
                t2.setText(c.FirstName);
                t3.setText(c.LastName);
                t4.setText(c.address);
                t5.setText(c.phone);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
