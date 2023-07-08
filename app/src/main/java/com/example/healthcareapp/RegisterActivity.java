package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edName,edPass,edEmail,edCfPass;
    Button btnReg;
    TextView text_already_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edName = findViewById(R.id.editText_Reg_Name);
        edEmail = findViewById(R.id.editText_Reg_Email);
        edPass = findViewById(R.id.editTexReg_Pass);
        edCfPass =findViewById(R.id.editText_Reg_Confirmpass);
        btnReg = findViewById(R.id.button_BookApp_register);
        text_already_account = findViewById(R.id.textView_exist_account);


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edName.getText().toString();
                String email = edEmail.getText().toString();
                String pass = edPass.getText().toString();
                String cfpass = edCfPass.getText().toString();
                DataBase db =  new DataBase(getApplicationContext(),"health_care_db",null,1);
                if(name.length()==0 || pass.length()==0 || email.length()==0 || cfpass.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill detail data",Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.compareTo(cfpass)==0){
                         if(isValid(pass)){
                             db.register(name,email,pass);
                             Toast.makeText(getApplicationContext(),"Register successfull,account inserted",Toast.LENGTH_SHORT).show();
                             startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                         }else{
                             Toast.makeText(getApplicationContext(),"Password need have at least 8 character,have digit,letter and symbol letter",Toast.LENGTH_SHORT).show();
                         }
                    }else{
                        Toast.makeText(getApplicationContext(),"Pass and Confirm pass didn't match",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });








        text_already_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }
    public static Boolean isValid(String pass){
        int f1=0,f2=0,f3=0;
     if(pass.length()<8){
         return false;
     }else{
         for(int i=0;i< pass.length();i++){
         if(Character.isLetter(pass.charAt(i))){
             f1=1;
         }
         }

         for(int i=0;i< pass.length();i++){
             if(Character.isDigit(pass.charAt(i))){
                 f2=1;
             }
         }

         for(int i=0;i< pass.length();i++){
             char c = pass.charAt(i);
             if(c>=33 && c<=46 || c==64){
                 f3=1;
             }
         }
         if(f1==1 && f2==1 && f3==1){
             return true;
         }
         return false;
     }
    }

}
