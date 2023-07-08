package com.example.healthcareapp.healthArticles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.healthcareapp.R;

public class HealthArticlesDetailActivity extends AppCompatActivity {
TextView tv;
ImageView img;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles_detail);
        tv = findViewById(R.id.textView_HAD_title);
        img = findViewById(R.id.imageViewHAD);
        btn = findViewById(R.id.button_HAD);
        Intent it = getIntent();
        tv.setText(it.getStringExtra("text1"));
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            int resId = bundle.getInt("text2");
            img.setImageResource(resId);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticlesDetailActivity.this, HealthArticlesActivity.class));
            }
        });

    }
}