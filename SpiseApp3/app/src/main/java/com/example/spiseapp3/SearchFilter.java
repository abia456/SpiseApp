package com.example.spiseapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.nio.file.DirectoryStream;
import java.util.ArrayList;

public class SearchFilter extends AppCompatActivity {

    Button FilterBtn;
    ArrayList <CheckBox> PriceCheckBoxes;
    ArrayList <CheckBox> CuisineCheckBoxes;
    ArrayList <CheckBox> DeliveryFeeCheckBoxes;

    ArrayList<String> CuisineFilter;
    ArrayList<String> PriceFilter;
    ArrayList<String> DeliveryPriceFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);

        /////////////////////////////////////////  Cuisine filter  //////////////////////////////

        CuisineCheckBoxes= new ArrayList<CheckBox>();

        CuisineFilter= new ArrayList<String>();
        PriceFilter=new ArrayList<String>();
        DeliveryPriceFilter= new ArrayList<String>();

        final CheckBox[] c = {new CheckBox(this)};
        FilterBtn = findViewById(R.id.Filterbtn);

        FilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                //intent.putExtra("CuisineList",CuisineCheckBoxes);
                //intent.putExtras(bundle);
                ////////////////////////////////////////////////////////////////////
                c[0] = findViewById(R.id.CuisineCheckbox1);
                if(c[0].isChecked()) {
                    CuisineCheckBoxes.add(c[0]);
                    CuisineFilter.add(c[0].getText().toString());
                }
                c[0] = findViewById(R.id.CuisineCheckbox2);
                if(c[0].isChecked()) {
                    CuisineCheckBoxes.add(c[0]);
                    CuisineFilter.add(c[0].getText().toString());

                }
                c[0] = findViewById(R.id.CuisineCheckbox3);
                if(c[0].isChecked()) {
                    CuisineCheckBoxes.add(c[0]);
                    CuisineFilter.add(c[0].getText().toString());

                }


                c[0] = findViewById(R.id.CuisineCheckbox4);
                if(c[0].isChecked()) {
                    CuisineCheckBoxes.add(c[0]);
                    CuisineFilter.add(c[0].getText().toString());

                }
                ////////////////////////////////////////////////////////

                /////////////////////////////// Price filter /////////////////////////////

                c[0]= findViewById(R.id.priceCheckbox1);
                if(c[0].isChecked())
                {
                    PriceFilter.add(c[0].getText().toString());
                }


                c[0]= findViewById(R.id.priceCheckbox2);
                if(c[0].isChecked())
                {
                    PriceFilter.add(c[0].getText().toString());
                }


                c[0]= findViewById(R.id.priceCheckbox3);
                if(c[0].isChecked())
                {
                    PriceFilter.add(c[0].getText().toString());
                }


                ////////////////////////////////////////////////////////////////////////

                //////////////////////////// Delivery Price Filetr ////////////////////

                c[0]= findViewById(R.id.OfferCheckbox1);
                if(c[0].isChecked())
                {
                    DeliveryPriceFilter.add(c[0].getText().toString());
                }


                c[0]= findViewById(R.id.OfferCheckbox2);
                if(c[0].isChecked())
                {
                    DeliveryPriceFilter.add(c[0].getText().toString());
                }

                c[0]= findViewById(R.id.OfferCheckbox3);
                if(c[0].isChecked())
                {
                    DeliveryPriceFilter.add(c[0].getText().toString());
                }


                c[0]= findViewById(R.id.OfferCheckbox4);
                if(c[0].isChecked())
                {
                    DeliveryPriceFilter.add(c[0].getText().toString());
                }

                /////////////////////////////////////////////////////////////////////



                Intent intent = new Intent (SearchFilter.this, chome.class);
                intent.putExtra("from","filterscreen");
                intent.putStringArrayListExtra("CuisineList", CuisineFilter);
                intent.putStringArrayListExtra("PriceList", PriceFilter);
                intent.putStringArrayListExtra("DeliveryPriceList", DeliveryPriceFilter);



                startActivity(intent);
            }

        });
//////////////////////////////////////////////////////////////////////////////////////////////


    }
}
