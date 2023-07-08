package com.example.healthcareapp.labtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthcareapp.DataBase;
import com.example.healthcareapp.HomeActivity;
import com.example.healthcareapp.R;

public class LabTestOrderActivity extends AppCompatActivity {
  EditText edname,edAddress,edPin,edContact;
  Button btnOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_order);
        edname = findViewById(R.id.editText_labOrder_Name);
        edAddress = findViewById(R.id.editText_labOrder_Address);
        edPin = findViewById(R.id.editText_labOrder_Pincode);
        edContact = findViewById(R.id.editText_labOrder_Contact);
        btnOrder = findViewById(R.id.button_labOrder);

        Intent it = getIntent();
        String[] price = it.getStringExtra("price").split(java.util.regex.Pattern.quote(":"));
        String date = it.getStringExtra("date");
        String time = it.getStringExtra("time");


        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                DataBase db =  new DataBase(getApplicationContext(),"health_care_db",null,1);
                 db.addOrder(username,edname.getText().toString(),edAddress.getText().toString(),edContact.getText().toString(),Integer.parseInt(edPin.getText().toString()),date,time,Float.parseFloat(price[1]),"lab test" );
                db.removeCart(username,"lab test");
                Toast.makeText(getApplicationContext(),"Your order is suscessfully",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LabTestOrderActivity.this, HomeActivity.class));
            }
        });

    }
}