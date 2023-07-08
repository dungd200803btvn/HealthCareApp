package com.example.healthcareapp;
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

public class LoginActivity extends AppCompatActivity {
 EditText edName,edPass;
 Button btnLogin;
 TextView tv1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edName = findViewById(R.id.editText_Name);
        edPass = findViewById(R.id.editText_pass);
        btnLogin = findViewById(R.id.buttonLogin);
        tv1 = findViewById(R.id.textView_register);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edName.getText().toString();
                String pass = edPass.getText().toString();
                DataBase db =  new DataBase(getApplicationContext(),"health_care_db",null,1);
                if(name.length()==0 || pass.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill detail data",Toast.LENGTH_SHORT).show();
                }else{
                    if(db.login(name,pass)==1){
                        Toast.makeText(getApplicationContext(),"Success Login",Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",name);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    }else{
                        Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
            tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                }
            });





    }
}