package com.example.spiseapp3.ui.CustomerOrders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

import com.example.spiseapp3.Menu.Courserow;
import com.example.spiseapp3.Menu.Drinks;
import com.example.spiseapp3.Menu.Drinksrow;
import com.example.spiseapp3.Menu.Drow;
import com.example.spiseapp3.OrderFiles.Order;
import com.example.spiseapp3.OrderFiles.OrderItem;
import com.example.spiseapp3.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cordernew extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView sub_total;
    TextView delivery_fee;
    TextView total;
    TextView customer_address;
    TextView status;
    DatabaseReference databaseReference;
    DatabaseReference ref2;
    ArrayList<Drinksrow> everything;
    Order o;
    int total_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cordernew);
        Intent intent = getIntent();
        total_order=0;
        String order_id = intent.getExtras().getString("order_id");
        everything = new ArrayList<Drinksrow>();
        recyclerView = findViewById(R.id.CorderRecycleView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final COrdernewAdapter adapter = new COrdernewAdapter(this, everything);

        recyclerView.setAdapter(adapter);


        sub_total = findViewById(R.id.SubtotalCorder);
        delivery_fee = findViewById(R.id.DeliveryFeeCorder);
        total = findViewById(R.id.total_cost);
        customer_address = findViewById(R.id.Corderaddress);
        status = findViewById(R.id.Corderstatusvalue);
        databaseReference = FirebaseDatabase.getInstance()
                .getReference("Orders")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(order_id);
        o = new Order();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                o = dataSnapshot.getValue(Order.class);
                customer_address.setText(o.getCustomer_address());
                status.setText(o.status);
                if (o.res_id != null) {
                    ref2 = FirebaseDatabase.getInstance()
                            .getReference("Client").child(o.res_id);
                    final ArrayList<Drinksrow> temp = new ArrayList<Drinksrow>();
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
                                            sub_total.setText(put);
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
                                            sub_total.setText(put);
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
                                            sub_total.setText(put);
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

    }

}
