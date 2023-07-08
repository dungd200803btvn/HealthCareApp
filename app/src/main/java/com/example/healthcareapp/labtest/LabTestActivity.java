package com.example.healthcareapp.labtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.healthcareapp.cart.CartLabActivity;
import com.example.healthcareapp.HomeActivity;
import com.example.healthcareapp.R;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {
private String [][] packages = {
        {"Package 1: Full body checkup","","","","999"},
        {"Package 2: Blood Glucose Fasting","","","","299"},
        {"Package 3: Covid Antibody - IgC","","","","899"},
        {"Package 4: Thyroid Check","","","","399"},
        {"Package 5: Imunity Check","","","","799"}
};
private String[] package_details = {
        "Blood glucose fasting\n"+
                "HbA1C\n"+
                "Iron studies\n"+
                "Kidney Function Test\n"+
                "LDH Lactate Dehydrogenase,Serum\n"+
                "Lipid Profiles\n"+
                "Liver function test",

        "Blood Glucose Fasting",
        "Covid Antibody - IgC",
        "Thyroid Profile-Total (T3,T4 & TSK Ultra-sensitive)",

        "Complete Hemogram\n"+
                "CRP (C Reactive Protein) Quantitative,Serum\n"+
                "Iron studies\n"+
                "Kidney Function Test\n"+
                "Vitamin D Total-25 Hydroxy\n"+
                "Lipid Profiles\n"+
                "Liver function test"
};
    HashMap<String,String> item;
    ArrayList list ;
    SimpleAdapter sa;
    Button btnGotoCart,btnBack;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);
        btnBack =findViewById(R.id.button_LT_back);
        btnGotoCart =findViewById(R.id.button_LT_gotocart);
        listView =findViewById(R.id.listview_LT);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
            }
        });

        list = new ArrayList();
        for(int i=0;i<packages.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost "+packages[i][4]+"$");
            list.add(item);
        }

        sa = new SimpleAdapter(this,
                list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.textview_line_a,R.id.textview_line_b,R.id.textview_line_c,R.id.textview_line_d,R.id.textview_line_e});
        listView.setAdapter(sa);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it = new Intent(LabTestActivity.this, LabTestDetailActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);

                startActivity(it);
            }
        });
        btnGotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, CartLabActivity.class));
            }
        });
    }
}