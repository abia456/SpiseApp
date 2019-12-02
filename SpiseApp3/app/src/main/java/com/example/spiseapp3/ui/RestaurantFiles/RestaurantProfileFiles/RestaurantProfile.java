package com.example.spiseapp3.ui.RestaurantFiles.RestaurantProfileFiles;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.spiseapp3.R;
import com.example.spiseapp3.ui.CustomerProfile.CProfile;
import com.example.spiseapp3.ui.CustomerProfile.editCProfile;

import androidx.appcompat.app.AppCompatActivity;

public class RestaurantProfile extends AppCompatActivity {

    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);
        t1 = findViewById(R.id.res_edit);
        t1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(RestaurantProfile.this, RestaurantProfileEdit.class);
                startActivity(i);
            }
        });

    }
}