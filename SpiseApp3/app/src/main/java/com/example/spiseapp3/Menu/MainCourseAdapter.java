package com.example.spiseapp3.Menu;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
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

public class MainCourseAdapter extends RecyclerView.Adapter<MainCourseAdapter.MainCourseViewHolder> {

    String TAG = "MainCourseAdapter";
    ArrayList<Courserow> rowslist;
    public static Boolean newlist= false;
    public static ArrayList<Courserow> CourseTemp;

    String RID;
    Context v;

    public MainCourseAdapter(Context c, ArrayList<Courserow> cr,String rid)
    {
        this.rowslist= cr;

        RID=rid;
        v=c;
        CourseTemp= new ArrayList<Courserow>();


    }

    @NonNull
    @Override
    public MainCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator= LayoutInflater.from(parent.getContext());
        View view= inflator.inflate(R.layout.maincourse_row,parent,false);
        return new MainCourseAdapter.MainCourseViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final MainCourseViewHolder holder, final int position) {

        Log.e(TAG, "*****ROws: "+rowslist.get(position).Name);
        holder.txt.setText(rowslist.get(position).Name);
        holder.txt1.setText(rowslist.get(position).Description);
        holder.txt2.setText(rowslist.get(position).price);

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
                            CourseTemp.add(rowslist.get(position));
                            newlist=true;
                        }
                        else{newlist=false;}
                    }

                    ///+++++++++++++++++++++++++++++++++++
                    if(newlist==false) {

                        Lists.maincourse.add(rowslist.get(position));
                        Toast.makeText(v.getContext(), "Checked item addded course " + Lists.maincourse.size(), Toast.LENGTH_SHORT).show();
                    }
                }
                else{

                    boolean stop= false;
                    for(int i=0; i<Lists.maincourse.size() && stop==false ;i++) //if a checkbox is unchecked, see if it exists on the list, if it does, remove it from the list
                    {
                        if(Lists.maincourse.get(i)==rowslist.get(position))
                        {
                            Lists.maincourse.remove(rowslist.get(position));
                            stop=true;
                            Toast.makeText(v.getContext(), "Checked item Removed course " +  Lists.maincourse.size() , Toast.LENGTH_SHORT).show();

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

    public class MainCourseViewHolder extends RecyclerView.ViewHolder{
        CheckBox c1;

        TextView txt;
        TextView txt1;
        TextView txt2;

        public MainCourseViewHolder(@NonNull View itemView) {
            super(itemView);
            txt= itemView.findViewById(R.id.maincoourseName);
            txt1= itemView.findViewById(R.id.MainCOurseDes);
            txt2= itemView.findViewById(R.id.maincoursePrice);
            c1=itemView.findViewById(R.id.checkBoxMainCourse);

        }
    }

}

