package com.example.spiseapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.spiseapp3.ui.FoodItemDetailAdapter;

public class FoodItemDetail extends AppCompatActivity {

    private int Counter=0;
    Button btnInc;
    Button btnDec;
    TextView count;
    Button addtocartBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item_detail);
        RecyclerView recyclerViewDemo = findViewById(R.id.RecycleViewItemDetail);
        recyclerViewDemo.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDemo.setAdapter(new FoodItemDetailAdapter(getApplicationContext()));
        btnInc= findViewById(R.id.btnincrement);
        btnDec= findViewById(R.id.btncdecrement);
        count= findViewById(R.id.itemCount);

        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Counter++;
                count.setText(Integer.toString(Counter)) ;
            }
        });

        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Counter!=0) {
                    Counter--;
                    count.setText(Integer.toString(Counter));

                }
            }
        });

        addtocartBTN   = findViewById(R.id.AddToCartBtn);
        addtocartBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent (FoodItemDetail.this,Cart.class);
                startActivity(intent);
            }

        });


    }
}
