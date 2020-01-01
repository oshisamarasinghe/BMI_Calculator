package com.app.oshadhi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity  extends AppCompatActivity {
    DataBaseHelper db;
    Button bmi_calculate, p_data, p_update, p_view;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bmi_calculate = findViewById(R.id.bmi);
        p_data = findViewById(R.id.personal_data);
        // p_update = findViewById(R.id.update_data);
        p_view = findViewById(R.id.view_data);

        bmi_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BMIIntent = new Intent(MainActivity.this, BMICalculationActivity.class);
                startActivity(BMIIntent);
            }
        });
        p_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PDataIntent = new Intent(MainActivity.this, PersonalDataHandleActivity.class);
                startActivity(PDataIntent);
            }
        });
//        p_update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent PUpdateIntent = new Intent(MainActivity.this, PersonalDataUpdateActivity.class);
//                startActivity(PUpdateIntent);
//            }
//        });

        p_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PViewIntent = new Intent(MainActivity.this, PersonalDataViewActivity.class);
                startActivity(PViewIntent);
            }
        });

    }

}
