package com.example.healthcareapp;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetailActivity extends AppCompatActivity {
 String[][] order_details ;
HashMap<String,String> item;
SimpleAdapter sa;
ListView lv;
Button btn;
ArrayList list;
    ArrayList<String> dbdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        btn = findViewById(R.id.button_orderdetail);
        lv = findViewById(R.id.listview_orderdetail);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailActivity.this, HomeActivity.class));
            }
        });

        DataBase db =  new DataBase(getApplicationContext(),"health_care_db",null,1);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        dbdata= new ArrayList<>();
       dbdata = db.gerOrderData(username);
        order_details = new String[dbdata.size()][];

        for(int i=0;i<order_details.length;i++){
            order_details[i] = new String[5];
            String data = dbdata.get(i);
            String[] split = data.split(java.util.regex.Pattern.quote("$"));

        order_details[i][0] = split[0];
            order_details[i][1] = split[1];
            if(split[7].compareTo("medicine")==0){
                order_details[i][3] = "Del"+ split[4];
            }else{
                order_details[i][3] = "Del"+ split[4]+ " "+split[5];
            }
            order_details[i][2] = "Rs "+split[6];
            order_details[i][4] = split[7];
        }

        list = new ArrayList();
        for(int i=0;i<order_details.length;i++){
            item = new HashMap<>();
            item.put("line1",order_details[i][0]);
            item.put("line2",order_details[i][1]);
            item.put("line3",order_details[i][2]);
            item.put("line4",order_details[i][3]);
            item.put("line5",order_details[i][4]);
            list.add(item);
        }
        sa = new SimpleAdapter(this,
                list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.textview_line_a,R.id.textview_line_b,R.id.textview_line_c,R.id.textview_line_d,R.id.textview_line_e});

        lv.setAdapter(sa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showCFDialog(position);

            }


        });


    }
    private void showCFDialog(int position) {
        DataBase db =  new DataBase(getApplicationContext(),"health_care_db",null,1);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation")
                .setMessage("Are you sure you want to remove this order?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Remove the item from the list
                         list.remove(position);
                        HashMap<String, String> item = (HashMap<String, String>) list.get(0);
                        String fullname = item.get("line1");
                        String address = item.get("line2");
                        String otype = item.get("line5");
                        db.removeOrder(fullname,address,otype);
                       sa.notifyDataSetChanged();

                        // Show a toast message
                        Toast.makeText(OrderDetailActivity.this, "Item removed: " , Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

}