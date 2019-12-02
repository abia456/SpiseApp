package com.example.spiseapp3.ui.CustomerProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spiseapp3.Customer;
import com.example.spiseapp3.R;
import com.example.spiseapp3.SignUpCustomer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class editCProfile extends AppCompatActivity {

   EditText t1;
    EditText t2;
    EditText t3;
    EditText t4;
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseUser user;
    String uid;
    Button b1;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_customer_profile);
        t1=findViewById(R.id.editText);
        t2=findViewById(R.id.editText2);
        t3=findViewById(R.id.editText3);
        t4=findViewById(R.id.editText4);
        final Customer[] c = new Customer[1];
        databaseReference=FirebaseDatabase.getInstance().getReference("Customer");
        user=FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        final String[] s1 = new String[1];
        final String[] s2 = new String[1];
        final String[] s3 = new String[1];
        final String[] s4 = new String[1];
        databaseReference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                c[0] =dataSnapshot.getValue(Customer.class);
                t1.setText(c[0].FirstName);

                s1[0] =c[0].FirstName;
                s2[0]=c[0].LastName;
                s3[0]=c[0].email;
                s4[0]=c[0].phone;


                t2.setText(c[0].LastName);
                t3.setText(c[0].address);
                t4.setText(c[0].phone);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        b1=findViewById(R.id.update_button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean found=true;
                if(!s1[0].equals(t1.getText().toString()))
                {
                    databaseReference.child(uid).child("FirstName").setValue(t1.getText().toString());
                    found=false;
                }
                if(!s2[0].equals(t2.getText().toString()))
                {
                    databaseReference.child(uid).child("LastName").setValue(t2.getText().toString());
                    found=false;
                }

              /*  if(!s3[0].equals(t3.getText().toString()))
                {
                    databaseReference.child(uid).child("email").setValue(t3.getText());
                    found=false;
                }*/
                if(!s4[0].equals(t4.getText().toString()))
                {
                    databaseReference.child(uid).child("phone").setValue(t4.getText().toString());
                    found=false;
                }
                if(!found)
                {
                    Toast.makeText(editCProfile.this, "Updated Successfully", Toast.LENGTH_SHORT).show();

                }

            }
        });



    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        String g1=t1.getText().toString();
        savedInstanceState.putString("t1", g1);
        savedInstanceState.putString("t2", t2.getText().toString());
        savedInstanceState.putString("t3", t3.getText().toString());
        savedInstanceState.putString("t4", t4.getText().toString());
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        t1.setText(savedInstanceState.getString("t1"));
        t2.setText(savedInstanceState.getString("t2"));
        t3.setText(savedInstanceState.getString("t3"));
    }

}
