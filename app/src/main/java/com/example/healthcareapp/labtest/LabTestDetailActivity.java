package com.example.healthcareapp.labtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthcareapp.DataBase;
import com.example.healthcareapp.R;

public class LabTestDetailActivity extends AppCompatActivity {
  EditText ed;
  TextView tv1,tv2;
  Button btnAddtoCart,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_detail);
        ed = findViewById(R.id.editText_LabDetail_multiline);
        tv1 = findViewById(R.id.textView_LabDetail_labtest);
        tv2 = findViewById(R.id.textView_LabDetail);
        btnAddtoCart = findViewById(R.id.button_LabDetail_gotocart);
        btnBack = findViewById(R.id.button_LabDetail_back);
        ed.setKeyListener(null);
        Intent it = getIntent();
        tv1.setText(it.getStringExtra("text1"));
        tv2.setText("Total Cost: "+it.getStringExtra( "text3")+" $");
        ed.setText(it.getStringExtra("text2"));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestDetailActivity.this,LabTestActivity.class));
            }
        });
        btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = tv1.getText().toString();
                Float price = Float.parseFloat(it.getStringExtra("text3").toString());
                DataBase db =  new DataBase(getApplicationContext(),"health_care_db",null,1);
                if(db.checkCart(username,product)==1){
                    Toast.makeText(getApplicationContext(),"Product already added",Toast.LENGTH_SHORT).show();
                }else{
                    db.addtoCart(username,product,price,"lab test");
                    Toast.makeText(getApplicationContext(),"Record inserted to cart suscessfull!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailActivity.this,LabTestActivity.class));

                }
            }
        });
    }
}