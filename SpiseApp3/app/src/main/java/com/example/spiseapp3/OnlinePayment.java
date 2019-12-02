package com.example.spiseapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class OnlinePayment extends AppCompatActivity {


    TextView Fname;
    CheckBox cb;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_payment);

        Fname= findViewById(R.id.NameOncard);

        cb= findViewById(R.id.saveCardCheckbox);
        if(cb.isChecked())
        {
            //Save State
        }
        btn=findViewById(R.id.applycardbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent (OnlinePayment.this,Cart.class);
                startActivity(intent);
            }

        });



    }

    /////////////////////////////// SAVE STATE /////////////////////////////////////////////////

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        String g1=Fname.getText().toString();
        savedInstanceState.putString("t1", g1);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int s = savedInstanceState.getInt("int1");
        Fname.setText(savedInstanceState.getString("t1"));
    }


}
