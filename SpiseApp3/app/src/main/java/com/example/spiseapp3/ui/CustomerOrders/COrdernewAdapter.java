package com.example.spiseapp3.ui.CustomerOrders;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spiseapp3.Client;
import com.example.spiseapp3.Menu.Drinksrow;
import com.example.spiseapp3.OrderFiles.Order;
import com.example.spiseapp3.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class COrdernewAdapter extends RecyclerView.Adapter<COrdernewAdapter.OrdersnewViewHolder> {
    protected ArrayList<Drinksrow> items;

    Context context;
    public COrdernewAdapter(Context c,  ArrayList<Drinksrow>i) {

        context=c;
        items=i;


    }


    @NonNull
    @Override
    public OrdersnewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.cordernewrow, parent,false);
        return new COrdernewAdapter.OrdersnewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersnewViewHolder holder, int position) {
        holder.txt1.setText(items.get(position).quantity);
        holder.txt2.setText(items.get(position).DName);
        holder.txt3.setText(items.get(position).Dprice);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class OrdersnewViewHolder extends RecyclerView.ViewHolder
    {

        TextView txt1;
        TextView txt2;
        TextView txt3;


        public OrdersnewViewHolder(@NonNull View itemView) {

            super(itemView);

            txt1=itemView.findViewById(R.id.Cordercount);

            txt2=itemView.findViewById(R.id.Cordername);

            txt3=itemView.findViewById(R.id.Corderprice);



        }
    }
}


