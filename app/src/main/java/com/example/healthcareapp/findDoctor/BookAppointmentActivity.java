package com.example.healthcareapp.findDoctor;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.healthcareapp.DataBase;
import com.example.healthcareapp.HomeActivity;
import com.example.healthcareapp.R;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
 EditText ed1,ed2,ed3,ed4;
 TextView tv1;
 Button btnDate,btnTime,btnReg,btnBack;
 private DatePickerDialog datePickerDialog;
 private TimePickerDialog timePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        tv1 = findViewById(R.id.textView_BookApp_tittle);
        ed1 = findViewById(R.id.editText_BookApp_Name);
        ed2 = findViewById(R.id.editText_BookApp_Address);
        ed3 = findViewById(R.id.editText_BookApp_ContactNumber);
        ed4 = findViewById(R.id.editText_BookApp_Fees);
        btnDate = findViewById(R.id.button_BA_SL_Date);
        btnTime = findViewById(R.id.button_BA_SL_Time);
        btnReg = findViewById(R.id.button_BookApp_register);
        btnBack = findViewById(R.id.button_BookApp__back);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String name = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contactnumber = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        tv1.setText(title);
        ed1.setText(name);
        ed2.setText(address);
        ed3.setText(contactnumber);
        ed4.setText("Cost: "+fees+"$");
        //date picker
        initDatePicker();
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        //time picker
        initTimePicker();
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmentActivity.this, Find_Doctor_Activity.class));
            }
        });
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBase db =  new DataBase(getApplicationContext(),"health_care_db",null,1);
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                if(db.checkAppointmentExists(username,title+"=>" +  name,address,contactnumber,btnDate.getText().toString(),btnTime.getText().toString())==1){
                    Toast.makeText(getApplicationContext(),"Appointment is already",Toast.LENGTH_SHORT).show();
                }else{
                    db.addOrder(username,title+"=>" +  name,address,contactnumber,0,btnDate.getText().toString(),btnTime.getText().toString(),Float.parseFloat(fees),"appointment");
                    Toast.makeText(getApplicationContext(),"Appointment is done sucsessfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BookAppointmentActivity.this, HomeActivity.class));
                }
            }
        });

    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month+=1;
                btnDate.setText(dayOfMonth+"/"+month+"/"+year);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis()+86400000);
    }

    private void initTimePicker(){
       TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
           @Override
           public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                btnTime.setText(hourOfDay+":"+minute);
           }
       };
        Calendar calendar = Calendar.getInstance();
        int hrs = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this,style,timeSetListener,hrs,minute,true);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis()+86400000);
    }
}