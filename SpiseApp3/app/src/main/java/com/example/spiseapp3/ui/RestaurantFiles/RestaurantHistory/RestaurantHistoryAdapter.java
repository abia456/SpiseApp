package com.example.spiseapp3.ui.RestaurantFiles.RestaurantHistory;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spiseapp3.Customer;
import com.example.spiseapp3.OrderFiles.Orderres;
import com.example.spiseapp3.R;
import com.example.spiseapp3.SignUpClient;
import com.example.spiseapp3.ui.RestaurantRequests.ExpandRequest;
import com.example.spiseapp3.ui.RestaurantRequests.RestaurantOrderRequests;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

class rHistoryRow
{
    String delivered_to;
    String address;
    String order_date;
    String bill;



    public rHistoryRow(String r, String o, String or, String b) {

       delivered_to=r;
        address=o;
        order_date=or;
        bill=b;
    }
}
public class RestaurantHistoryAdapter extends RecyclerView.Adapter<RestaurantHistoryAdapter.OrderHistoryHolder> {
    protected ArrayList<Orderres> orders;
   Context context;
   String cus_mail;


    public RestaurantHistoryAdapter(Context c, ArrayList<Orderres> o) {
        this.orders= o;
        context=c;

    }
    @NonNull
    @Override
    public RestaurantHistoryAdapter.OrderHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.restaurant_order_history_row, parent,false);
        return new RestaurantHistoryAdapter.OrderHistoryHolder(view);

    }



    @Override
    public void onBindViewHolder(@NonNull final RestaurantHistoryAdapter.OrderHistoryHolder holder, final int position) {


        DatabaseReference databaseReference= FirebaseDatabase.getInstance()
                .getReference("Customer").child(orders.get(position).customer_id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Customer c=dataSnapshot.getValue(Customer.class);
                cus_mail=c.email;
                holder.txt1.setText(cus_mail);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        holder.txt2.setText(orders.get(position).cus_address);
        holder.txt3.setText(orders.get(position).date);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HistoryDetails.class);
                intent.putExtra("cus_id", orders.get(position).customer_id);
                intent.putExtra("cus_email", cus_mail);
                intent.putExtra("order_id", orders.get(position).order_id);
                intent.putExtra("cus_address", orders.get(position).cus_address);
                intent.putExtra("date", orders.get(position).date);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class OrderHistoryHolder extends RecyclerView.ViewHolder
    {

        TextView txt1;
        TextView txt2;
        TextView txt3;
        Button btn;

        public OrderHistoryHolder(@NonNull View itemView) {

            super(itemView);

            txt1=itemView.findViewById(R.id.delivered_to_customer);

            txt2=itemView.findViewById(R.id.customer_delivery_address);
            txt3=itemView.findViewById(R.id.orderdate);
            btn=itemView.findViewById(R.id.details);


        }
    }
}
