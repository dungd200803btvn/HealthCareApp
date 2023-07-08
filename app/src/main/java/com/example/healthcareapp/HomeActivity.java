package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.healthcareapp.buymedicine.BuyMedicineActivity;
import com.example.healthcareapp.findDoctor.Find_Doctor_Activity;
import com.example.healthcareapp.healthArticles.HealthArticlesActivity;
import com.example.healthcareapp.labtest.LabTestActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("username","").toString();
        Toast.makeText(getApplicationContext(),"Welcome "+name,Toast.LENGTH_SHORT).show();

        CardView exit = findViewById(R.id.card_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });

        CardView find_doctor = findViewById(R.id.cardfind_doctor);
        find_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, Find_Doctor_Activity.class));
            }
        });

        CardView lab_test = findViewById(R.id.cardtest);
        lab_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LabTestActivity.class));
            }
        });
        CardView order_detail = findViewById(R.id.card_order_detail);
        order_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, OrderDetailActivity.class));
            }
        });

        CardView buy_medicine = findViewById(R.id.cardbuymedicine);
        buy_medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, BuyMedicineActivity.class));
            }
        });

        CardView health_article = findViewById(R.id.cardhealh_doctor);
        health_article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HealthArticlesActivity.class));
            }
        });
    }
}