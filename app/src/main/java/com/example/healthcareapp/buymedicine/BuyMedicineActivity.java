package com.example.healthcareapp.buymedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.healthcareapp.cart.CartMedicineActivity;
import com.example.healthcareapp.HomeActivity;
import com.example.healthcareapp.R;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {
private String[][] packages = {
        { "Paracetamol (acetaminophen)","","","","15"},
        { "Ibuprofen","","","","43 "},
        { "Aspirin","","","","20 "},
        { "Simvastatin","","","","50 "},
        { "Metformin","","","","65 "},
        { "Atorvastatin","","","","13 "},
        { "Omeprazole","","","","67 "},
        { "Metoprolol","","","","23 "},
        { "Amoxicillin","","","","26 "},
        { "Fluoxetine","","","","34 "},
};
private String[] packages_details = {
  "Uses: Pain relief and fever reduction.\n" +
          "Common brand names: Tylenol, Panadol.\n" +
          "Notes: Considered safe when used as directed.",
        "Uses: Nonsteroidal anti-inflammatory drug (NSAID) used for pain relief, fever reduction, and inflammation reduction.\n" +
                "Common brand names: Advil, Motrin.\n" +
                "Notes: Should be used cautiously, especially for long-term use.",
        "Uses: NSAID used for pain relief, fever reduction, and blood-thinning properties.\n" +
                "Common brand names: Bayer, Ecotrin.\n" +
                "Notes: Avoid giving aspirin to children or teenagers due to the risk of Reye's syndrome.",
        "Uses: Cholesterol-lowering medication to reduce the risk of heart disease and stroke.\n" +
                "Common brand names: Zocor.\n" +
                "Notes: Usually prescribed along with a healthy diet and exercise.",
        "Uses: Oral medication for managing type 2 diabetes.\n" +
                "Common brand names: Glucophage, Fortamet.\n" +
                "Notes: Helps control blood sugar levels and may also have other health benefits.",
        "Uses: Cholesterol-lowering medication to reduce the risk of heart disease and stroke.\n" +
                "Common brand names: Lipitor.\n" +
                "Notes: Often prescribed alongside lifestyle changes, such as diet and exercise.",
        "Uses: Proton pump inhibitor (PPI) used to reduce stomach acid and treat conditions like acid reflux and ulcers.\n" +
                "Common brand names: Prilosec, Losec.\n" +
                "Notes: Should be taken as directed and not for prolonged periods without medical supervision.",
        "Uses: Beta-blocker used to treat high blood pressure, angina, and heart-related conditions.\n" +
                "Common brand names: Lopressor, Toprol XL.\n" +
                "Notes: Should not be stopped suddenly without consulting a healthcare professional.",
        "Uses: Antibiotic used to treat bacterial infections, such as respiratory infections and urinary tract infections.\n" +
                "Common brand names: Amoxil, Trimox.\n" +
                "Notes: Should be taken for the prescribed duration to ensure the infection is fully treated.",
        "Uses: Selective serotonin reuptake inhibitor (SSRI) used to treat depression, anxiety disorders, and certain other mental health conditions.\n" +
                "Common brand names: Prozac, Sarafem.\n" +
                "Notes: May take several weeks to start showing its full effects, and should not be discontinued abruptly."
};
    HashMap<String,String> item;
    ArrayList list ;
    SimpleAdapter sa;
    Button btnGotoCart,btnBack;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);
        btnBack  = findViewById(R.id.button_BuyMedicine_back);
        btnGotoCart  = findViewById(R.id.button_BuyMedicine_gotocart);
        listView = findViewById(R.id.listview_BuyMedicine);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
            }
        });
        btnGotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, CartMedicineActivity.class));
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
                Intent it = new Intent(BuyMedicineActivity.this, BuyMedicineDetailActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",packages_details[i]);
                it.putExtra("text3",packages[i][4]);

                startActivity(it);
            }
        });
    }
}