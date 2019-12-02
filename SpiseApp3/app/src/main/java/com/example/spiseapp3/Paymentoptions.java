package com.example.spiseapp3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class Paymentoptions extends AppCompatActivity {

    RadioButton onDelivery;
    RadioButton onlineP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentoptions);

        onlineP=findViewById(R.id.onlinepayment);
        onlineP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent (Paymentoptions.this,OnlinePayment.class);
                startActivity(intent);
            }

        });


        onDelivery=findViewById(R.id.ondelivery);
        onDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent (Paymentoptions.this,Cart.class);
                startActivity(intent);
            }

        });


    }
}
