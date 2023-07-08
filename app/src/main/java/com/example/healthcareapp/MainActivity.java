package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth.getInstance().createUserWithEmailAndPassword("ledung200803@gmail.com","dung2003");
    }
}