package com.example.spiseapp3.Menu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spiseapp3.Client;
import com.example.spiseapp3.R;
import com.example.spiseapp3.SignUpCustomer;
import com.example.spiseapp3.Upload;
import com.example.spiseapp3.chome;
import com.example.spiseapp3.scrolling;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Desserts extends Fragment {

    String uid;
    DatabaseReference databaseReference;
    ArrayList<Drow> items;
    public static ArrayList <Drow> lst;

    FirebaseDatabase firebaseDatabase;


    public Desserts()
    {
    }
    public Desserts(String s)
    {
        uid=s;
    }

    public static Desserts newInstance(String u) {
        Desserts fragment = new Desserts(u);
        Log.e("dESSERTS", " Loading new INstance");
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


        View view = inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        /////////////////////////////////////////add new recyclerview/////////////////////
        RecyclerView recyclerViewDemo = view.findViewById(R.id.recyclerViewDemo1);
        recyclerViewDemo.setLayoutManager(new LinearLayoutManager(getContext()));

        items= new ArrayList<Drow>();
        final DessertsAdapter adapter=new DessertsAdapter(getContext(), items, uid);

        recyclerViewDemo.setAdapter(adapter);
        lst= adapter.getCheckedDessrts(); //gets checked dessert items

        ///////////////////////////////////////////// get values from firebase ///////////////////////////



        databaseReference=FirebaseDatabase.getInstance().getReference("Client").child(uid);

        databaseReference.child("Desserts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {

                    final String a=dataSnapshot1.getKey();



                    databaseReference.child("Desserts").child(a).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Drow d=dataSnapshot.getValue(Drow.class);
                            d.item_id=a;
                            items.add(d);
                            adapter.notifyDataSetChanged();
                            ArrayList <Drow> lst= adapter.getCheckedDessrts();

                            Toast.makeText(getActivity(), "Checked " + lst.size(), Toast.LENGTH_SHORT).show();

                            ArrayList <Drow> Checkedlst= adapter.getCheckedDessrts(); //gets checked dessert items


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


        ///////////////////////////////////////////////////////////////////////////////////////////

        return view;
    }

}
