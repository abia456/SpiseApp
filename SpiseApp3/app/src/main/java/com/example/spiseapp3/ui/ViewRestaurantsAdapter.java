package com.example.spiseapp3.ui;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.icu.text.Transliterator;
import android.media.tv.TvContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spiseapp3.Client;
import com.example.spiseapp3.R;
import com.example.spiseapp3.SignUpCustomer;
import com.example.spiseapp3.Upload;
import com.example.spiseapp3.menu;
import com.example.spiseapp3.scrolling;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewRestaurantsAdapter extends RecyclerView.Adapter <ViewRestaurantsAdapter.ViewHolder>{

    ArrayList<Client> Restaurants;
    ArrayList<Upload> files;
    Context C;

    public ViewRestaurantsAdapter(Context c, ArrayList<Client> list, ArrayList<Upload> f)
    {
        C=c;
       Restaurants=list;
       files=f;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator= LayoutInflater.from(parent.getContext());
        View view= inflator.inflate(R.layout.activity_view_restaurant_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


            Picasso.get().load(files.get(position).getmImageurl()).into(holder.Logo);

        holder.RestaurantName.setText(Restaurants.get(position).resName);
        holder.DeliveryTime.setText(Restaurants.get(position).DeliveryTime);
        holder.Category.setText(Restaurants.get(position).cuisine);
        holder.Price.setText(Restaurants.get(position).filter);
        holder.DeliveryPrice.setText(Restaurants.get(position).DeliveryCost);
       holder.Rating.setText(( Restaurants.get(position).Ratings));
       final int p=position;


        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (C , scrolling.class);
                intent.putExtra("res_uid", Restaurants.get(p).getUid());
                intent.putExtra("image_url", files.get(position).getmImageurl());
                intent.putExtra("res_name",Restaurants.get(p).resName);

                C.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return Restaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView RestaurantName;
        TextView DeliveryTime;
        TextView Category;
        TextView Price;
        TextView DeliveryPrice;
        TextView Rating;
        ImageView Logo;
        RelativeLayout rl;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Logo=  itemView.findViewById(R.id.ResLogo);
            Rating= itemView.findViewById(R.id.RestaurantRating);
            DeliveryPrice= itemView.findViewById(R.id.DeliveryCost);
            Price= itemView.findViewById(R.id.RestaurantPrice);
            Category= itemView.findViewById(R.id.RestaurantCategory);
            DeliveryTime=itemView.findViewById(R.id.deliveryTime);
            RestaurantName= itemView.findViewById(R.id.RestaurantName);
            rl= itemView.findViewById(R.id.restuarantRow);
        }


    }


}


