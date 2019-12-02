package com.example.spiseapp3.Menu;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.spiseapp3.R;

import java.util.ArrayList;


public class DessertsAdapter extends RecyclerView.Adapter< DessertsAdapter.DessertViewHolder>{

    ArrayList<Drow> rowslist;
    ArrayList<Drow> CheckedDesserts;
    public static Boolean  newlist= false;
    public static ArrayList<Drow> dessertsTemp;

    String RID;
    Context v;

    public DessertsAdapter(Context c, ArrayList<Drow> list,String rid )
    {
        RID=rid;
        v=c;
        this.rowslist= list;
        this.CheckedDesserts= new ArrayList<Drow>();

         dessertsTemp= new ArrayList<Drow>();

    }

    public ArrayList<Drow> getCheckedDessrts()
    {
        return CheckedDesserts;
    }

    @NonNull
    @Override
    public DessertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator= LayoutInflater.from(parent.getContext());
        View view= inflator.inflate(R.layout.r_item,parent,false);
        return new DessertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DessertViewHolder holder, final int position) {


        holder.txt.setText(rowslist.get(position).Name);
        holder.txt1.setText(rowslist.get(position).Description);
        holder.txt2.setText(rowslist.get(position).price);




        //if(holder.c1.isChecked()) //if checkbox is checked, then add to list
        //{
          //  CheckedDesserts.add(rowslist.get(position));
           // Toast.makeText(v.getApplicationContext(), "Checked item addded " +  CheckedDesserts.size() , Toast.LENGTH_SHORT).show();

        //}
        //if (holder.c1.isChecked()==false)
        //{
          //  boolean stop= false;
            //for(int i=0; i<CheckedDesserts.size() && stop==false ;i++) //if a checkbox is unchecked, see if it exists on the list, if it does, remove it from the list
            //{
              //  if(CheckedDesserts.get(i)==rowslist.get(position))
                //{
                  //  CheckedDesserts.remove(rowslist.get(position));
                   // stop=true;
                //}
            //}
        //}

        holder.c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                if(holder.c1.isChecked())
                {
                    ///+++++++++++++++++++++++++++++++++++
                    if(Lists.cart_res_id!=null) {
                        if (!RID.matches(Lists.cart_res_id)) {
                            //adds items in a new temp list
                            dessertsTemp.add(rowslist.get(position));
                                 newlist=true;
                        }
                        else{newlist=false;}
                    }

                    ///+++++++++++++++++++++++++++++++++++

                    if(newlist==false) {
                        Lists.desserts.add(rowslist.get(position));
                        Toast.makeText(v.getContext(), "Checked item addded " + Lists.desserts.size(), Toast.LENGTH_SHORT).show();
                    }


                }
                else{

                    boolean stop= false;
                    for(int i=0; i<Lists.desserts.size() && stop==false ;i++) //if a checkbox is unchecked, see if it exists on the list, if it does, remove it from the list
                    {
                      if(Lists.desserts.get(i)==rowslist.get(position))
                    {
                        Lists.desserts.remove(rowslist.get(position));
                     stop=true;
                        Toast.makeText(v.getContext(), "Checked item Removed " +  Lists.desserts.size() , Toast.LENGTH_SHORT).show();

                    }
                    }

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return rowslist.size();
    }


    public class DessertViewHolder extends RecyclerView.ViewHolder {


        TextView txt;
        TextView txt1;
        TextView txt2;
        CheckBox c1;


        public DessertViewHolder(@NonNull View itemView) {
            super(itemView);
            txt= itemView.findViewById(R.id.ItemName);
            txt1= itemView.findViewById(R.id.ItemDes);
            txt2= itemView.findViewById(R.id.ItemPrice);
            c1= itemView.findViewById(R.id.checkBoxDessert);
        }
    }



}


