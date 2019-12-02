package com.example.spiseapp3.ui.RestaurantFiles.RestaurantProfileFiles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.spiseapp3.R;

public class RestaurantProfileEdit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] users = { "Pakistani", "Fast Food", "Burgers", "Pizza", "Italian", "Chinese", "Thai", "Asian", "Desserts" };
    EditText e1;
    EditText e2;
    EditText e3;
    EditText e4;
    EditText e5;
    EditText e6;
    int value;
    Spinner spin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile_edit);

       spin = (Spinner) findViewById(R.id.res_cuisine_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
        spin.setSelection(3);
        e1=findViewById(R.id.editText);
        e2=findViewById(R.id.editText2);
        e3=findViewById(R.id.editText3);
        e4=findViewById(R.id.editText4);
        e5=findViewById(R.id.res_edit_Address);
        e6=findViewById(R.id.res_city_input);



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        String g1=e1.getText().toString();
        savedInstanceState.putString("e1", g1);
        savedInstanceState.putString("e2", e2.getText().toString());
        savedInstanceState.putString("e3", e3.getText().toString());
        savedInstanceState.putString("e4", e4.getText().toString());
        savedInstanceState.putString("e5", e5.getText().toString());
        savedInstanceState.putString("e6", e6.getText().toString());
        value=spin.getSelectedItemPosition();
        savedInstanceState.putInt("value", value);

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        e1.setText(savedInstanceState.getString("e1"));
        e2.setText(savedInstanceState.getString("e2"));
        e3.setText(savedInstanceState.getString("e3"));
        e4.setText(savedInstanceState.getString("e4"));
        e5.setText(savedInstanceState.getString("e5"));
        e6.setText(savedInstanceState.getString("e6"));
        spin.setSelection(savedInstanceState.getInt("value"));
    }
}
