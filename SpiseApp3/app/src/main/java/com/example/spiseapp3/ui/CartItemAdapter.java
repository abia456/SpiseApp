package com.example.spiseapp3.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spiseapp3.Cart;
import com.example.spiseapp3.Menu.Lists;
import com.example.spiseapp3.R;

import java.util.ArrayList;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {


    ArrayList<CartItem> CartItemList;
    Context C;
    CartItemAdapter d=this;


    public CartItemAdapter(Context c, ArrayList<CartItem> list)
    {
        C=c;


       CartItemList=list;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator= LayoutInflater.from(parent.getContext());
        View view= inflator.inflate(R.layout.activity_cart__itemrow,parent,false);
        return new CartItemAdapter.ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.FName.setText(CartItemList.get(position).ItemName);
        holder.Fprice.setText(CartItemList.get(position).Price);
        holder.ItemCount.setText(CartItemList.get(position).quantity);


        holder.inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(C.getApplicationContext(),"INCREMENTED"+ CartItemList.get(position).quantity, Toast.LENGTH_SHORT).show();

                String count=  CartItemList.get(position).quantity;
                int c= Integer.parseInt(count);

                Toast.makeText(C.getApplicationContext(),"C: "+ c, Toast.LENGTH_SHORT).show();

                c++;

                Toast.makeText(C.getApplicationContext(),"C after inc : "+ c, Toast.LENGTH_SHORT).show();


                count=Integer.toString(c);

                Toast.makeText(C.getApplicationContext(),"Count after inc : "+ count, Toast.LENGTH_SHORT).show();

                CartItemList.get(position).quantity=count;


                holder.ItemCount.setText(count);
                Cart.calculate();
            //    Intent refresh = new Intent(C, Cart.class);
              //  C.startActivity(refresh);


            }
        });

        holder.dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String count=  CartItemList.get(position).quantity;

                int c= Integer.parseInt(count);

                if(c>1)
                {
                    c--;
                    count=Integer.toString(c);
                    CartItemList.get(position).quantity=count;
                    holder.ItemCount.setText(count);

                }
                else if(c==1)
                {

                    Boolean stop=false;
                    for(int i=0; i<Lists.desserts.size()   && stop==false ;i++ )
                    {
                        if( Lists.desserts.get(i).item_id .matches( CartItemList.get(position).itemid ) ) {
                            Lists.desserts.remove(i);
                            stop = true;
                            d.notifyDataSetChanged();

                        }



                    }

                    stop=false;
                    for(int i=0; i<Lists.drinks.size()   && stop==false ;i++ )
                    {
                        if( Lists.drinks.get(i).item_id .matches( CartItemList.get(position).itemid ) ) {
                            Lists.drinks.remove(i);
                            stop = true;
                            d.notifyDataSetChanged();
                        }
                    }


                    stop=false;
                    for(int i=0; i<Lists.maincourse.size()   && stop==false ;i++ )
                    {
                        if( Lists.maincourse.get(i).item_id .matches( CartItemList.get(position).itemid ) ) {
                            Lists.maincourse.remove(i);
                            stop = true;
                            d.notifyDataSetChanged();

                        }
                    }

                    Lists.CartItemsList.remove(position);



                }
                Cart.calculate();
                //Intent refresh = new Intent(C, Cart.class);
                //C.startActivity(refresh);




            }
        });

    }

    @Override
    public int getItemCount() {
        return CartItemList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView FName;
        TextView Fprice;
        Button inc;
        Button dec;
        TextView ItemCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            FName= itemView.findViewById(R.id.ItemNamecart);
            Fprice= itemView.findViewById(R.id.pirceItemCart);
            inc= itemView.findViewById(R.id.btnincrementCart);
            dec= itemView.findViewById(R.id.btncdecrementCart);
            ItemCount=itemView.findViewById(R.id.itemCountCart);


        }
    }

}




