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
import com.example.spiseapp3.SignUpClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainCourse extends Fragment {

    String uid;
    DatabaseReference databaseReference;
    ArrayList<Courserow>list;
    MainCourseAdapter adapter;


    public MainCourse()
    {
    }
    public MainCourse(String s)
    {
        uid=s;
    }
    public static MainCourse newInstance(String s) {

        MainCourse fragment = new MainCourse(s);
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

        View view = inflater.inflate(R.layout.fragment_blank_fragment4, container, false);
        /////////////////////////////////////////add new recyclerview/////////////////////
        RecyclerView recyclerViewDemo = view.findViewById(R.id.recyclerViewDemo4);
        recyclerViewDemo.setLayoutManager(new LinearLayoutManager(getContext()));


        ///////////////////////////////////////////// get values from firebase ///////////////////////////


        list=new ArrayList<Courserow>();
        adapter=new MainCourseAdapter(getContext(), list,uid);
        recyclerViewDemo.setAdapter(adapter);
        databaseReference=FirebaseDatabase.getInstance().getReference("Client").child(uid);

        databaseReference.child("MainCourse").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {

                    final String a=dataSnapshot1.getKey();

                    databaseReference.child("MainCourse").child(a).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           Courserow d=dataSnapshot.getValue(Courserow.class);
                           d.item_id=a;
                           list.add(d);

                            adapter.notifyDataSetChanged();
                           Toast.makeText(getActivity(),"MainCourse on Data Changed: "+list.size(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                            Toast.makeText(getActivity(),"MainCourse on Cancelled", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getActivity(),"MainCourse onChanaskldjlas", Toast.LENGTH_SHORT).show();
            }

        });


       Toast.makeText(getContext(), "main course filled", Toast.LENGTH_SHORT).show();
        ///////////////////////////////////////////////////////////////////////////////////////////


        return view;
    }


}
