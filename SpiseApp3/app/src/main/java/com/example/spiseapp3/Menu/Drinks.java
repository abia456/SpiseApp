package com.example.spiseapp3.Menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spiseapp3.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Drinks extends Fragment {

    String uid;
    DatabaseReference databaseReference;
    ArrayList<Drinksrow>list;



    public Drinks()
    {
    }
    public Drinks(String s)

    {
        uid=s;
    }
    public static Drinks newInstance(String s) {
        Drinks fragment = new Drinks(s);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_blank_fragment3, container, false);
        /////////////////////////////////////////add new recyclerview/////////////////////
        final RecyclerView recyclerViewDemo = view.findViewById(R.id.recyclerViewDemo2);
        recyclerViewDemo.setLayoutManager(new LinearLayoutManager(getContext()));
        ///////////////////////////////////////////// get values from firebase ///////////////////////////
        list = new ArrayList<Drinksrow>();
        final DrinksAdapter  dAdapter = new DrinksAdapter(getContext(), list,uid);
        recyclerViewDemo.setAdapter(dAdapter);


        databaseReference=FirebaseDatabase.getInstance().getReference("Client").child(uid);

        databaseReference.child("Drinks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {

                    final String a=dataSnapshot1.getKey();

                    databaseReference.child("Drinks").child(a).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Drinksrow d=dataSnapshot.getValue(Drinksrow.class);
                            d.item_id=a;
                            list.add(d);
                            dAdapter.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Toast.makeText(getContext(), "filled drinks", Toast.LENGTH_SHORT).show();
        ///////////////////////////////////////////////////////////////////////////////////////////
        return view;
    }


}
