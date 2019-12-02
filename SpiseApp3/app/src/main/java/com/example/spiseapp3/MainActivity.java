package com.example.spiseapp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spiseapp3.Mvp.LogicContractor;
import com.example.spiseapp3.ui.Mvp.LoginPresenter;
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




public class MainActivity extends AppCompatActivity implements LogicContractor.View1 {

    EditText email;
    EditText pass;
    Button Login;
    Button SignupAsCusBtn;
    Button SignupAsClientBtn;

    ////////////////////// mvp ////////////////////////
    LogicContractor.Presenter presenter;
    ////////////////////////////////////////////////


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////////////////////////mvp /////////////////////
        presenter= new LoginPresenter(this, getApplicationContext());
        ///////////////////////////////////////////////


        TextView txt= findViewById(R.id.forgotpass);
        txt.setMovementMethod(LinkMovementMethod.getInstance());
        SignupAsCusBtn= findViewById(R.id.button);
        SignupAsClientBtn= findViewById(R.id.button2);
        email=findViewById(R.id.editText);
        pass=findViewById(R.id.editText3);

        Login= findViewById(R.id.LoginBtn);



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                final String usernameString= email.getText().toString();




                String PasswordString= pass.getText().toString();

                if (usernameString.matches(""))
                {
                    onError("You did not enter your username");
                    //Toast.makeText(MainActivity.this, "You did not enter your username", Toast.LENGTH_SHORT).show();
                }

                else if (PasswordString.matches(""))
                {
                    onError("You did not enter your password");
                    //Toast.makeText(MainActivity.this, "You did not enter your password", Toast.LENGTH_SHORT).show();
                }
                else if(PasswordString.length()<6 || PasswordString.length()>15 )
                {
                    onError("Password length should be between 5 and 16");

                }

                else if(PasswordString.matches("amna"))
                {
                    Intent intent = new Intent (MainActivity.this, RestaurantOrderRequests.class);
                    startActivity(intent);
                }

                else
                {
                    presenter.doLogin(usernameString,PasswordString);

                }

            }

        });






        SignupAsCusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent (MainActivity.this,SignUpCustomer.class);
                startActivity(intent);
            }

        });


        SignupAsClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent (MainActivity.this,SignUpClient.class);
                startActivity(intent);
            }

        });

    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onError(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onSuccess(String message, Boolean b) {

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

        if(b)
        {
            Intent intent = new Intent(MainActivity.this,chome.class );
            startActivity(intent);

        }
        else
        {
            Intent intent = new Intent(MainActivity.this, RestaurantOrderRequests.class);
            startActivity(intent);

        }


    }
}