package com.example.healthcareapp.cart;

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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.healthcareapp.DataBase;
import com.example.healthcareapp.R;
import com.example.healthcareapp.labtest.LabTestActivity;
import com.example.healthcareapp.labtest.LabTestOrderActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartLabActivity extends AppCompatActivity {
HashMap<String,String> item;
ArrayList list;

SimpleAdapter sa;
DatePickerDialog datePickerDialog;
TimePickerDialog timePickerDialog;
TextView tv;
    ListView lv ;
Button btnBack,btnCheckout,btnsetDate,btnsetTime;
private String[][ ] packages = {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_lab);
        btnBack = findViewById(R.id.button_cartlab_back);
        btnCheckout = findViewById(R.id.button_cartlab_checkout);
        btnsetDate = findViewById(R.id.button_cartlab_SL_Date);
        btnsetTime = findViewById(R.id.button_cartlab_SL_Time);
        tv = findViewById(R.id.textView_cartlab_totalcost);
        lv = findViewById(R.id.listview_cartlab);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");

        DataBase db =  new DataBase(getApplicationContext(),"health_care_db",null,1);
        ArrayList dbdata = db.getCart(username,"lab test");
        Toast.makeText(getApplicationContext(),""+dbdata,Toast.LENGTH_SHORT).show();
        float totalamount =0;

        packages = new String[dbdata.size()][5];
        for(int i=0;i< dbdata.size();i++){
            String arrdata = dbdata.get(i).toString();
            String[] splitdata = arrdata.split(java.util.regex.Pattern.quote("$"));
            packages[i][0] = splitdata[0];
            packages[i][4] = "Cost: "+splitdata[1]+" $";
            totalamount= totalamount + Float.parseFloat(splitdata[1]);
        }
        tv.setText("Total cost:"+totalamount);
   // list view
        list = new ArrayList();
        for(int i=0;i< packages.length;i++){
            item = new HashMap<String,String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5",packages[i][4]);
            list.add(item);
        }
        sa = new SimpleAdapter(this,
                list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.textview_line_a,R.id.textview_line_b,R.id.textview_line_c,R.id.textview_line_d,R.id.textview_line_e});
        lv.setAdapter(sa);



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartLabActivity.this, LabTestActivity.class));
            }
        });
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(CartLabActivity.this, LabTestOrderActivity.class);
                it.putExtra("price",tv.getText());
                it.putExtra("date",btnsetDate.getText());
                it.putExtra("time",btnsetTime.getText());
                startActivity(it);
            }
        });

        //date picker
        initDatePicker();
        btnsetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        //time picker
        initTimePicker();
        btnsetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });


    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month+=1;
                btnsetDate.setText(dayOfMonth+"/"+month+"/"+year);
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
                btnsetTime.setText(hourOfDay+":"+minute);
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