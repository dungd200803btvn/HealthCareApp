package com.example.healthcareapp.findDoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.healthcareapp.R;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailActivity extends AppCompatActivity {
TextView tv1;
Button btn;
private  String[][] doctors1 = {
        {"Doctor Name: Le Chi Dung","Hospital Address: Hanoi","Exp: 5 years","Mobile: 0335620803","600"},
        {"Doctor Name: Nguyen Van Phu An","Hospital Address: Nghe An","Exp: 3 years","Mobile: 0347779999","789"},
        {"Doctor Name: Nguyen Duc Anh","Hospital Address: Ninh Binh","Exp: 4 years","Mobile: 0335612345","700"},
        {"Doctor Name: Nguyen Anh Tuan","Hospital Address: Thanh Hoa","Exp: 1 years","Mobile: 0123456789","400"},
        {"Doctor Name: Dam Thanh Bach","Hospital Address: Cao Bang","Exp: 2 years","Mobile: 0334447777","450"}
};
    private  String[][] doctors2 = {
            {"Doctor Name: Pham Thanh Lap","Hospital Address: Hanoi","Exp: 5 years","Mobile: 0335620804","600"},
            {"Doctor Name: Pham Duc Minh","Hospital Address: Nghe An","Exp: 3 years","Mobile: 0347779998","789"},
            {"Doctor Name: Nguyen Duc Hoang","Hospital Address: Ninh Binh","Exp: 4 years","Mobile: 0335612346","700"},
            {"Doctor Name: Nguyen Anh Thu","Hospital Address: Thanh Hoa","Exp: 1 years","Mobile: 0123456787","400"},
            {"Doctor Name: Dam Hoang Giang","Hospital Address: Cao Bang","Exp: 2 years","Mobile: 0334447770","450"}
    };
    private  String[][] doctors3 = {
            {"Doctor Name: Chu Thi Ly","Hospital Address: Hanoi","Exp: 5 years","Mobile: 0335620803","600"},
            {"Doctor Name: Le Hong Thai","Hospital Address: Nghe An","Exp: 3 years","Mobile: 0347779999","789"},
            {"Doctor Name: Nguyen Duc Truong","Hospital Address: Ninh Binh","Exp: 4 years","Mobile: 0335612345","700"},
            {"Doctor Name: Nguyen Van Minh","Hospital Address: Thanh Hoa","Exp: 1 years","Mobile: 0123456789","400"},
            {"Doctor Name: Pham Quoc Minh","Hospital Address: Cao Bang","Exp: 2 years","Mobile: 0334447777","450"}
    };
    private   String[][] doctors4 = {
            {"Doctor Name: Le Ngoc Huyen","Hospital Address: Hanoi","Exp: 5 years","Mobile: 0335620803","600"},
            {"Doctor Name: Nguyen Trong Cuong","Hospital Address: Nghe An","Exp: 3 years","Mobile: 0347779999","789"},
            {"Doctor Name: Nguyen Huy Hoang","Hospital Address: Ninh Binh","Exp: 4 years","Mobile: 0335612345","700"},
            {"Doctor Name: Le Hoang Anh Tuan","Hospital Address: Thanh Hoa","Exp: 1 years","Mobile: 0123456789","400"},
            {"Doctor Name: Le Ngoc Thao","Hospital Address: Cao Bang","Exp: 2 years","Mobile: 0334447777","450"}
    };
    private  String[][] doctors5 = {
            {"Doctor Name: Pham Duc Long","Hospital Address: Hanoi","Exp: 5 years","Mobile: 0335620803","600"},
            {"Doctor Name: Nguyen Van Thanh Dat","Hospital Address: Nghe An","Exp: 3 years","Mobile: 0347779999","789"},
            {"Doctor Name: Nguyen Duc Phu","Hospital Address: Ninh Binh","Exp: 4 years","Mobile: 0335612345","700"},
            {"Doctor Name: Nguyen Hong Quang","Hospital Address: Thanh Hoa","Exp: 1 years","Mobile: 0123456789","400"},
            {"Doctor Name: Nguyen Hong Phuong","Hospital Address: Cao Bang","Exp: 2 years","Mobile: 0334447777","450"}
    };
    String[][] doctors_detail  ={};
    HashMap<String,String> item;
  ArrayList list ;

    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        tv1 = findViewById(R.id.textView_Doctor_detail_title);
        btn = findViewById(R.id.button_doctor_detail);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
       tv1.setText(title);

        if(title.compareTo("Family Physican")==0){
            doctors_detail = doctors1;
        }
        else if(title.compareTo("Dietcian")==0){
            doctors_detail = doctors2;
        }
        else if(title.compareTo("Dentist")==0){
            doctors_detail = doctors3;
        }
       else if(title.compareTo("Surgeon")==0){
            doctors_detail = doctors4;
        }
        else{
            doctors_detail = doctors5;
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailActivity.this, Find_Doctor_Activity.class));
            }
        });
        list = new ArrayList();
        for(int i=0;i<doctors_detail.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",doctors_detail[i][0]); // chua toan bo ten
            item.put("line2",doctors_detail[i][1]); // chua toan bo address
            item.put("line3",doctors_detail[i][2]);
            item.put("line4",doctors_detail[i][3]);
            item.put("line5","Cost"+doctors_detail[i][4]+"$");
            list.add(item);
        }
        sa = new SimpleAdapter(this,
                list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.textview_line_a,R.id.textview_line_b,R.id.textview_line_c,R.id.textview_line_d,R.id.textview_line_e});
        ListView lv ;
        lv = findViewById(R.id.listview_doctor_detail);
        lv.setAdapter(sa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it = new Intent(DoctorDetailActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctors_detail[i][0]);
                it.putExtra("text3",doctors_detail[i][1]);
                it.putExtra("text4",doctors_detail[i][3]);
                it.putExtra("text5",doctors_detail[i][4]);
                startActivity(it);
            }
        });

    }
}