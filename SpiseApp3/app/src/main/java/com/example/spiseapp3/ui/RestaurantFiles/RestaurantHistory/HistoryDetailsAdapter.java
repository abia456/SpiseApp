package com.example.spiseapp3.ui.RestaurantFiles.RestaurantHistory;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spiseapp3.Customer;
import com.example.spiseapp3.Menu.Drinksrow;
import com.example.spiseapp3.OrderFiles.Orderres;
import com.example.spiseapp3.R;
import com.example.spiseapp3.ui.CustomerOrders.COrdernewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoryDetailsAdapter extends RecyclerView.Adapter<HistoryDetailsAdapter.DetailHolder> {
    protected ArrayList<Drinksrow> items;
    Context context;
    String cus_mail;


    public HistoryDetailsAdapter(Context c, ArrayList<Drinksrow> o) {
        this.items= o;
        context=c;

    }


    @NonNull
    @Override
    public DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.detail_row, parent,false);
        return new HistoryDetailsAdapter.DetailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailHolder holder, int position) {

        holder.txt1.setText(items.get(position).quantity);
        holder.txt2.setText(items.get(position).DName);
        holder.txt3.setText(items.get(position).Dprice);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class DetailHolder extends RecyclerView.ViewHolder
    {

        TextView txt1;
        TextView txt2;
        TextView txt3;


        public DetailHolder(@NonNull View itemView) {

            super(itemView);

            txt1=itemView.findViewById(R.id.Dcount);

            txt2=itemView.findViewById(R.id.Dname);
            txt3=itemView.findViewById(R.id.Dprice);



        }
    }
}
