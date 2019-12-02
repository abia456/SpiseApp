package com.example.spiseapp3.Menu;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spiseapp3.R;

import java.util.ArrayList;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.DrinksViewHolder> {


    ArrayList<Drinksrow> rowslist;

    public static Boolean newlist= false;
    public static ArrayList<Drinksrow> drinksTemp;


    String RID;
    Context v;

    public DrinksAdapter(Context C, ArrayList<Drinksrow>d,String rid) {
        RID=rid;
        v=C;

        this.rowslist= d;

        drinksTemp = new ArrayList<Drinksrow>();

    }

    @NonNull
    @Override
    public DrinksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator= LayoutInflater.from(parent.getContext());
        View view= inflator.inflate(R.layout.drinks_row,parent,false);
        return new DrinksAdapter.DrinksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DrinksViewHolder holder, final int position) {

        holder.txt.setText(rowslist.get(position).DName);
        holder.txt1.setText(rowslist.get(position).Dprice);



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
                            drinksTemp.add(rowslist.get(position));
                            newlist=true;
                        }
                        else{newlist=false;}
                    }

                    ///+++++++++++++++++++++++++++++++++++
                    if(newlist==false) {
                        Lists.drinks.add(rowslist.get(position));
                        Toast.makeText(v.getContext(), "Checked item addded drinks size " + Lists.drinks.size(), Toast.LENGTH_SHORT).show();
                    }
                }
                else{

                    boolean stop= false;
                    for(int i=0; i<Lists.drinks.size() && stop==false ;i++) //if a checkbox is unchecked, see if it exists on the list, if it does, remove it from the list
                    {
                        if(Lists.drinks.get(i)==rowslist.get(position))
                        {
                            Lists.drinks.remove(rowslist.get(position));
                            stop=true;
                            Toast.makeText(v.getContext(), "Checked item Removed drinks" +  Lists.drinks.size() , Toast.LENGTH_SHORT).show();

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

    public class DrinksViewHolder extends RecyclerView.ViewHolder{

        TextView txt;
        TextView txt1;
        CheckBox c1;
        public DrinksViewHolder(@NonNull View itemView) {
            super(itemView);
            c1=itemView.findViewById(R.id.checkBoxDRink);

            txt= itemView.findViewById(R.id.DrinkName);
            txt1= itemView.findViewById(R.id.DrinkPrice);

        }



    }
}


