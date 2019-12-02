package com.example.spiseapp3.ui.RestaurantRequests;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spiseapp3.Menu.Courserow;
import com.example.spiseapp3.Menu.Drinksrow;
import com.example.spiseapp3.Menu.Drow;
import com.example.spiseapp3.OrderFiles.Order;
import com.example.spiseapp3.OrderFiles.OrderItem;
import com.example.spiseapp3.R;
import com.example.spiseapp3.SignUpClient;
import com.example.spiseapp3.ui.RestaurantFiles.RestaurantHistory.HistoryDetailsAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExpandRequest extends AppCompatActivity {

    TextView address;
    TextView order_time;
    RecyclerView recyclerView;
    TextView total;
    TextView status;

    DatabaseReference databaseReference;
    DatabaseReference ref2;
    ArrayList<Drinksrow> everything;
    Button accept_button;
    Button reject_button;

    int total_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_request);
        //=======================================================\
        accept_button=findViewById(R.id.accept);
        reject_button=findViewById(R.id.reject);

        Intent myIntent = getIntent(); // gets the previously created intent
        String cus_email = myIntent.getStringExtra("cus_email"); // will return "FirstKeyValue"
        String cus_id= myIntent.getStringExtra("cus_id");
        String order_id= myIntent.getStringExtra("order_id");
        String cus_address= myIntent.getStringExtra("cus_address");
        String date= myIntent.getStringExtra("date");
        address=findViewById(R.id.customer_address);
        order_time=findViewById(R.id.order_time_value);
        recyclerView=findViewById(R.id.RecycleViewExpand);
        total=findViewById(R.id.total_value);
        total.setText("0");
        address.setText(cus_address);
        order_time.setText(date);


        everything = new ArrayList<Drinksrow>();


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final HistoryDetailsAdapter adapter = new HistoryDetailsAdapter(this, everything);

        recyclerView.setAdapter(adapter);

        //===============DATABASE=============================


        databaseReference = FirebaseDatabase.getInstance()
                .getReference("Orders")
                .child(cus_id)
                .child(order_id);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Order o = dataSnapshot.getValue(Order.class);

                if (o.res_id != null) {
                    ref2 = FirebaseDatabase.getInstance()
                            .getReference("Client").child(o.res_id);

                    for (int i = 0; i < o.items.size(); i++) {
                        final OrderItem oj = o.items.get(i);
                        if (o.items.get(i).item_id.contains("Main")) {
                            ref2.child("MainCourse").child(o.items.get(i).item_id)
                                    .addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            Courserow c = dataSnapshot.getValue(Courserow.class);
                                            Drinksrow d = new Drinksrow(c.item_id,c.Name,c.price,oj.quantity);
                                            String temp1=c.price;
                                            String temp2=temp1.replace("Rs ", "");
                                            int hh=Integer.parseInt(d.quantity) * Integer.parseInt(temp2);
                                            total_order=total_order+hh;
                                            String put= String.valueOf(total_order);
                                            int tt=total_order+50;
                                            String put2= String.valueOf(tt);
                                            total.setText(put2);

                                            everything.add(d);
                                            adapter.notifyDataSetChanged();


                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                        }

                        /////////////////////FOR DRINK////////////////////////
                        else if (o.items.get(i).item_id.contains("drink")) {
                            ref2.child("Drinks").child(o.items.get(i).item_id)
                                    .addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            Drinksrow c = dataSnapshot.getValue(Drinksrow.class);
                                            Drinksrow d = new Drinksrow(c.item_id,c.DName,c.Dprice,oj.quantity);
                                            String temp1=c.Dprice;
                                            String temp2=temp1.replace("Rs ", "");

                                            int hh=Integer.parseInt(d.quantity) * Integer.parseInt(temp2);
                                            total_order=total_order+hh;
                                            String put= String.valueOf(total_order);
                                            int tt=total_order+50;
                                            String put2= String.valueOf(tt);
                                            total.setText(put2);


                                            everything.add(d);
                                            adapter.notifyDataSetChanged();


                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                        }
                        /////////////////////FOR DESSERT////////////////////////
                        else if (o.items.get(i).item_id.contains("dessert")) {
                            ref2.child("Desserts").child(o.items.get(i).item_id)
                                    .addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            Drow c = dataSnapshot.getValue(Drow.class);
                                            Drinksrow d = new Drinksrow(c.item_id,c.Name,c.price,oj.quantity);
                                            String temp1=c.price;
                                            String temp2=temp1.replace("Rs ", "");
                                            int hh=Integer.parseInt(d.quantity) * Integer.parseInt(temp2);
                                            total_order=total_order+hh;
                                            String put= String.valueOf(total_order);

                                            int tt=total_order+50;
                                            String put2= String.valueOf(tt);
                                            total.setText(put2);


                                            everything.add(d);
                                            adapter.notifyDataSetChanged();


                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                        }

                    }
                }


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //====================================================
        accept_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("status").setValue("accepted");
                finish();

                Toast.makeText(ExpandRequest.this, "You accepted the order request", Toast.LENGTH_SHORT).show();

                Intent intent2 = new Intent(ExpandRequest.this,RestaurantOrderRequests.class);
                startActivity(intent2);


            }
        });
        reject_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("status").setValue("rejected");
                finish();

                Toast.makeText(ExpandRequest.this, "You rejected the request", Toast.LENGTH_SHORT).show();

                Intent intent2 = new Intent(ExpandRequest.this,RestaurantOrderRequests.class);
                startActivity(intent2);



            }
        });
        //=========================================================
    }
}
