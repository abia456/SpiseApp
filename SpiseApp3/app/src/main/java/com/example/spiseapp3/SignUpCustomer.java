package com.example.spiseapp3;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpCustomer extends AppCompatActivity {

    TextView Fname;
    TextView Sname;
    TextView email;
    TextView address;
    TextView phone;
    TextView pass;
    Button SingupCus;
    ProgressBar progress;
    TextView t1;

    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_customer);
        t1=findViewById(R.id.LoginFromSignCustomer);
        Fname=findViewById(R.id.FNAme);
        Sname=findViewById(R.id.SName);
        email=findViewById(R.id.Email_cus);
        address=findViewById(R.id.CustomerPostalAddress);
        phone=findViewById(R.id.CustomerPhoneNumber);
        pass=findViewById(R.id.CustomerPassword);
        progress=findViewById(R.id.SignupCustProgress);

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference("Customer");
        databaseReference=FirebaseDatabase.getInstance().getReference("User");

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (SignUpCustomer.this,MainActivity.class);
                startActivity(intent);
            }
        });
        SingupCus   = findViewById(R.id.SignupCusbtn);
        SingupCus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);


                final String FnameString = Fname.getText().toString();


                final String SnameString = Sname.getText().toString();


                final String EmailString = email.getText().toString();


                final String PhoneString = phone.getText().toString();


                final String AddressString= address.getText().toString();


                String PasswordString= pass.getText().toString();

                if (FnameString.matches(""))
                {
                    Toast.makeText(SignUpCustomer.this, "You did not enter your first name you RETARD", Toast.LENGTH_SHORT).show();
                }

                else if (SnameString.matches(""))
                {
                    Toast.makeText(SignUpCustomer.this, "You did not enter your second name", Toast.LENGTH_SHORT).show();
                }

                else if (EmailString.matches(""))
                {
                    Toast.makeText(SignUpCustomer.this, "You did not enter your Email", Toast.LENGTH_SHORT).show();
                }

                else if (PhoneString.matches(""))
                {
                    Toast.makeText(SignUpCustomer.this, "You did not enter your Phone Number", Toast.LENGTH_SHORT).show();
                }

                else if (AddressString.matches(""))
                {
                    Toast.makeText(SignUpCustomer.this, "You did not enter your Postal Address", Toast.LENGTH_SHORT).show();
                }

                else if (PasswordString.matches(""))
                {
                    Toast.makeText(SignUpCustomer.this, "You did not enter your Password", Toast.LENGTH_SHORT).show();
                }
                else if (PasswordString.length()<6 || PasswordString.length()>15)
                {
                    Toast.makeText(SignUpCustomer.this, "Password length should be between 5 and 16", Toast.LENGTH_SHORT).show();
                }

                else
                    {
                        firebaseAuth.createUserWithEmailAndPassword(EmailString,PasswordString)
                                .addOnCompleteListener(SignUpCustomer.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progress.setVisibility(View.GONE);
                                        if (task.isSuccessful()) {
                                            Customer cus=new Customer(FnameString, SnameString, EmailString, PhoneString,AddressString);

                                            User u=new User(true);
                                            FirebaseDatabase.getInstance().getReference("User")
                                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .setValue(u);
                                            FirebaseDatabase.getInstance().getReference("Customer")
                                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .setValue(cus).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Intent intent = new Intent(SignUpCustomer.this, chome.class);
                                                    startActivity(intent);

                                                }
                                            });


                                        }
                                        else{
                                            Toast.makeText(SignUpCustomer.this, "Failed!!", Toast.LENGTH_SHORT).show();


                                        }

                                        // ...
                                    }
                                });


                    }
            }

        });




    }
}
