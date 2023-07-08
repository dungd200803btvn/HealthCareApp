package com.example.healthcareapp.findDoctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.healthcareapp.HomeActivity;
import com.example.healthcareapp.R;

public class Find_Doctor_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);
        CardView exit = findViewById(R.id.cardFD_back);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Find_Doctor_Activity.this, HomeActivity.class));
            }
        });

        CardView family = findViewById(R.id.cardFD_Family_Physical);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Find_Doctor_Activity.this, DoctorDetailActivity.class);
                it.putExtra("title","Family Physican");
                startActivity(it);
            }
        });

        CardView dietcian = findViewById(R.id.cardFD_Dietcian);
        dietcian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Find_Doctor_Activity.this, DoctorDetailActivity.class);
                it.putExtra("title","Dietcian");
                startActivity(it);
            }
        });

        CardView dentist = findViewById(R.id.cardFD_Dentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Find_Doctor_Activity.this, DoctorDetailActivity.class);
                it.putExtra("title","Dentist");
                startActivity(it);
            }
        });
        CardView surgeon = findViewById(R.id.cardFD_Surgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Find_Doctor_Activity.this, DoctorDetailActivity.class);
                it.putExtra("title","Surgeon");
                startActivity(it);
            }
        });
        CardView cardiologists = findViewById(R.id.cardFD_Cardiologists);
        cardiologists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Find_Doctor_Activity.this, DoctorDetailActivity.class);
                it.putExtra("title","Cardiologists");
                startActivity(it);
            }
        });
    }
}