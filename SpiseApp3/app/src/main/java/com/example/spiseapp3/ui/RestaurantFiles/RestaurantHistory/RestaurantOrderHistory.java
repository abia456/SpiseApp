package com.example.spiseapp3.ui.RestaurantFiles.RestaurantHistory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.spiseapp3.OrderFiles.Order;
import com.example.spiseapp3.OrderFiles.Orderres;
import com.example.spiseapp3.R;
import com.example.spiseapp3.SignUpClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestaurantOrderHistory extends AppCompatActivity  {
    DatabaseReference databaseReference;
    ArrayList<Orderres>orders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_order_history);
        RecyclerView list = findViewById(R.id.rec_restaurant_order_history);
        orders= new ArrayList<Orderres>();
        final RestaurantHistoryAdapter adapter=new RestaurantHistoryAdapter(this, orders);
        list.setLayoutManager(new LinearLayoutManager(this));

        list.setAdapter(adapter);

        //============================FIREBASE===============================
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
                                        String hh=FirebaseAuth.getInstance().getCurrentUser().getUid();

                                            if (hh.equals(o.res_id)) {
                                                Orderres orderres = new Orderres(o.status, o.date, cus_id, order_id, o.items, o.getCustomer_address());
                                                orders.add(orderres);
                                                Toast.makeText(RestaurantOrderHistory.this, "id: " + orders.size(), Toast.LENGTH_SHORT).show();
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



        //=======================================================================

    }
    public void click_listener3(View view)
    {
        Intent i= new Intent(RestaurantOrderHistory.this, HistoryDetails.class);
        startActivity(i);
    }
}
