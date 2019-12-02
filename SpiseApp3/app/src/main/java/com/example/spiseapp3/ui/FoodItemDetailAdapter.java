package com.example.spiseapp3.ui;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spiseapp3.R;

import java.util.ArrayList;

public class FoodItemDetailAdapter extends RecyclerView.Adapter<FoodItemDetailAdapter.ViewHolder> {


    ArrayList<FoodItemOption> Options;
    Context C;

    public FoodItemDetailAdapter(Context c)
    {
        C=c;
        Options= new ArrayList<FoodItemOption>();
        ArrayList<options> optionsList= new ArrayList<options>();


        Resources r = c.getResources();

        String [] NameOptions= r.getStringArray(R.array.OptionName);
        options O1= new options(r.getStringArray(R.array.Option1));
        options O2= new options(r.getStringArray(R.array.Option2));
        optionsList.add(O1);
        optionsList.add(O2);


        FoodItemOption a;

        for(int i=0; i<NameOptions.length;i++)
        {
            a= new FoodItemOption(NameOptions[i],optionsList.get(i));
            Options.add(a);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator= LayoutInflater.from(parent.getContext());
        View view= inflator.inflate(R.layout.activity_food_item_options_row,parent,false);
        return new FoodItemDetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.Name.setText(Options.get(position).OptionNAme);
        RadioButton rb;
        int id=(position+1)*100;
        for(int i=0; i<2; i++) {
            rb = new RadioButton(C);
            rb.setText(Options.get(position).O.getopt(i));
            rb.setId(id++);
            holder.radiogroup.addView(rb);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView Name;
        RadioGroup radiogroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Name=itemView.findViewById(R.id.OptionTitle);
            radiogroup= itemView.findViewById(R.id.FoodItemOptions);


        }

    }
}


class FoodItemOption
{
    String OptionNAme;

    options O;


    public FoodItemOption(String optionNAme, options o) {
        OptionNAme = optionNAme;
        O = o;
    }
}

class options
{
    //int no_ofOptions;
    String[] opts;

    public options(String[] opts) {
        this.opts = opts;
    }

    String getopt(int i)
    {
        return opts[i];
    }
}
