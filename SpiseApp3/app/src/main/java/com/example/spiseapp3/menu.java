package com.example.spiseapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.spiseapp3.ui.menuAdapter;

public class menu extends AppCompatActivity {

    Button addtocart;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        RecyclerView recyclerViewDemo = findViewById(R.id.recyclerViewFoodItems);
        recyclerViewDemo.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDemo.setAdapter(new menuAdapter(getApplicationContext()));

        addtocart= findViewById(R.id.menuAddtocartBtn);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent (menu.this,Cart.class);
                startActivity(intent);
            }

        });

    }
}
