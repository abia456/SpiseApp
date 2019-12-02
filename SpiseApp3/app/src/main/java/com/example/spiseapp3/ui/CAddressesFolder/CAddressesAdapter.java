package com.example.spiseapp3.ui.CAddressesFolder;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spiseapp3.R;

import java.util.ArrayList;

class CAddressesRow
{

    String address;




    public CAddressesRow( String o) {


        address=o;

    }
}
public class CAddressesAdapter extends RecyclerView.Adapter<CAddressesAdapter.CAddressesHolder> {
    protected ArrayList<CAddressesRow> orders;
    private CAddressesHolder holder;
    private int position;

    public CAddressesAdapter (Context c) {
        this.orders= new ArrayList<CAddressesRow>();
        Resources res=c.getResources();

        String[]add=res.getStringArray(R.array.caddresses);

        for(int i=0;i<add.length;i++)
        {
            orders.add(new CAddressesRow(add[i]));
        }
    }
    @NonNull
    @Override
    public CAddressesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.caddresses_row, parent,false);
        return new CAddressesHolder(view);

    }






    @Override
    public void onBindViewHolder(@NonNull CAddressesHolder holder, int position) {
        holder.txt1.setText(orders.get(position).address);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class CAddressesHolder extends RecyclerView.ViewHolder
    {

        TextView txt1;



        public CAddressesHolder(@NonNull View itemView) {

            super(itemView);

            txt1=itemView.findViewById(R.id.caddresses);




        }
    }
}

