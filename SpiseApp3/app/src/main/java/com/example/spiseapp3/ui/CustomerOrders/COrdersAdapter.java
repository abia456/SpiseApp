package com.example.spiseapp3.ui.CustomerOrders;



import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.spiseapp3.Client;
import com.example.spiseapp3.OrderFiles.Order;
import com.example.spiseapp3.OrderFiles.OrderItem;
import com.example.spiseapp3.R;
import com.example.spiseapp3.SignUpClient;
import com.example.spiseapp3.ui.RestaurantRequests.RestaurantOrderRequests;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

class OrdersSingleRow
{
    String restaurant;
    String order_date;




    public OrdersSingleRow(String r,String or) {

        restaurant=r;
        order_date=or;

    }
}
public class COrdersAdapter extends RecyclerView.Adapter<COrdersAdapter.OrdersViewHolder> {
    protected ArrayList<Order> orders;
    protected  ArrayList<String>order_ids;
    Context context;
    public COrdersAdapter(Context c,  ArrayList<Order>o, ArrayList<String>orderss) {
        Toast.makeText(c, "size: " + o.size(), Toast.LENGTH_SHORT).show();
        orders=o;
        order_ids=orderss;
        context=c;

       /* this.orders= new ArrayList<OrdersSingleRow>();

        */
    }
    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.corders_row, parent,false);
        return new OrdersViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final OrdersViewHolder holder, final int position) {

        //=========================================

        final String[] name = new String[1];


            DatabaseReference databaseReference= FirebaseDatabase.getInstance()
                    .getReference("Client")
                    .child(orders.get(position).res_id);

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Client client =dataSnapshot.getValue(Client.class);
                    name[0] =client.resName;
                    holder.txt1.setText(name[0]);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });




        //=======================================

        holder.txt2.setText(orders.get(position).status);
        holder.txt3.setText(orders.get(position).date);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Cordernew.class);
                intent.putExtra("order_id", order_ids.get(position));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class OrdersViewHolder extends RecyclerView.ViewHolder
    {

        TextView txt1;
        TextView txt2;
        TextView txt3;
        TextView txt4;
        Button btn;

        public OrdersViewHolder(@NonNull View itemView) {

            super(itemView);

            txt1=itemView.findViewById(R.id.restaurant_name);

            txt2=itemView.findViewById(R.id.order);

            txt3=itemView.findViewById(R.id.orderdate);

            btn=itemView.findViewById(R.id.button);

        }
    }
}
