package com.example.spiseapp3.Mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.spiseapp3.MainActivity;
import com.example.spiseapp3.Mvp.LogicContractor;
import com.example.spiseapp3.chome;

import com.example.spiseapp3.ui.RestaurantRequests.RestaurantOrderRequests;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Executor;

public class LoginPresenter implements LogicContractor.Presenter {

    LogicContractor.View1 v1;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseUser user;
    Context c;


    public LoginPresenter(LogicContractor.View1 v1,Context C) {
        c=C;
        this.v1 = v1;
    }

    @Override
    public void doLogin(String usernameString, String PasswordString) {
        firebaseAuth=FirebaseAuth.getInstance();

        databaseReference=FirebaseDatabase.getInstance().getReference("User");

        firebaseAuth.signInWithEmailAndPassword(usernameString, PasswordString)
                .addOnCompleteListener( (Activity)v1, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            user= FirebaseAuth.getInstance().getCurrentUser();
                            final String uid=user.getUid();



                            databaseReference.child(uid).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    final boolean cust = (boolean) dataSnapshot.child("customer").getValue();


                                    if(cust) {
                                        v1.onSuccess("Succesful",cust);
                                    }
                                    else if(!cust)
                                    {
                                        v1.onSuccess("Succesful",cust);
                                    }
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                            SharedPreferences pref = c.getApplicationContext().getSharedPreferences("MyPref", 0);
                            SharedPreferences.Editor editor = pref.edit();
                            String u=FirebaseAuth.getInstance().getCurrentUser().getUid();
                            editor.putString("key_uid",u ); // Storing string
                            editor.putString("email",user.getEmail() ); // Storing string
                            editor.commit(); // commit changes



                        } else {

                        }

                        // ...
                    }
                });


    }
}
