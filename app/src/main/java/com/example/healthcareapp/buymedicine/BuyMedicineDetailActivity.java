package com.example.healthcareapp.buymedicine;

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

public class BuyMedicineDetailActivity extends AppCompatActivity {
TextView tv1,tv2;
EditText ed;
Button btnAddtoCart,btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_detail);
        tv1 = findViewById(R.id.textView_BuyMedicineDetail_labtest);
        tv2 = findViewById(R.id.textView_BuyMedicineDetail);
        ed = findViewById(R.id.editText_BuyMedicineDetail_multiline);
        ed.setKeyListener(null);
        btnback = findViewById(R.id.button_BuyMedicineDetail_back);
        btnAddtoCart = findViewById(R.id.button_BuyMedicineDetail_gotocart);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineDetailActivity.this, BuyMedicineActivity.class));
            }
        });
        Intent it = getIntent();
        tv1.setText(it.getStringExtra("text1"));
        ed.setText(it.getStringExtra("text2"));
        tv2.setText( "Total Cost:"+  it.getStringExtra("text3")+"$");

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
                    db.addtoCart(username,product,price,"medicine");
                    Toast.makeText(getApplicationContext(),"Record inserted to cart suscessfull!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailActivity.this,BuyMedicineActivity.class));

                }
            }
        });
    }
}