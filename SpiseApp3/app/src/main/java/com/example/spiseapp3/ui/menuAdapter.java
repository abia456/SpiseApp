package com.example.spiseapp3.ui;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spiseapp3.FoodItemDetail;
import com.example.spiseapp3.R;

import java.util.ArrayList;


public class menuAdapter extends RecyclerView.Adapter<menuAdapter.ViewHolder> {


    ArrayList<Fooditem> Items;
    Context C;

    public  menuAdapter(Context c)
    {
        C=c;
        Items= new ArrayList<Fooditem>();
        Resources r = c.getResources();

        String[] Names= r.getStringArray(R.array.FoodNames);
        String[] prices= r.getStringArray(R.array.FoodItemPrices);

        Fooditem a;
        for(int i=0 ;i<Names.length; i++)
        {
            a= new Fooditem(Names[i],prices[i]);
            Items.add(a);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator= LayoutInflater.from(parent.getContext());
        View view= inflator.inflate(R.layout.activity_menu_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.FName.setText(Items.get(position).name);
        holder.Fprice.setText(Items.get(position).price);

        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (C , FoodItemDetail.class);
                C.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView FName;
        TextView Fprice;
        RelativeLayout rl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            FName= itemView.findViewById(R.id.FoodItemName);
            Fprice= itemView.findViewById(R.id.FoodItemPrice);

            rl= itemView.findViewById(R.id.menuRow);

        }
    }
}


class Fooditem
{
    String name;
    String price;

    public Fooditem(String name, String price) {
        this.name = name;
        this.price = price;
    }
}