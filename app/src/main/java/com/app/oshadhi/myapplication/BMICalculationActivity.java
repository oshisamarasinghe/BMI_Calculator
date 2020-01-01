package com.app.oshadhi.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BMICalculationActivity extends AppCompatActivity {
    Button calculate, update;
    DataBaseHelper db;
    TextView result,height, weight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculation);
        db = new DataBaseHelper(this);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        calculate = findViewById(R.id.calculate);
        result = findViewById(R.id.result);
        update =findViewById(R.id.update_val);
        setValues();

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PUpdateIntent = new Intent(BMICalculationActivity.this, PersonalDataUpdateActivity.class);
                startActivity(PUpdateIntent);
                finish();
            }
        });
    }
    private void setValues(){
            Cursor res = db.viewUser("oshadhi");
            if(res.getCount() > 0 ){
                while(res.moveToNext()){
                    height.setText(res.getString(1));
                    weight.setText(res.getString(2));
                }
        }
    }

    private void calculateBMI(){
        String height_str = height.getText().toString();
        String weight_str = weight.getText().toString();

        if(!"".equals(height_str)  && !"".equals(weight_str)){
            float height_val  = Float.parseFloat(height_str)/100;
            float weight_val = Float.parseFloat(weight_str);

            float bmi = weight_val /(height_val* height_val);

            displayBMI(bmi);
        }
    }
    private  void displayBMI(float bmi){
        String bmi_lbl ="";

        if(Float.compare(bmi,15f)<=0){
            bmi_lbl = "very severely underweight";
        }else if (Float.compare(bmi,15f)>0 && Float.compare(bmi,16f)<=0){
            bmi_lbl = "severely underweight";
        }else if (Float.compare(bmi,16f)>0 && Float.compare(bmi,18.5f)<=0){
            bmi_lbl = "underweight";
        }else if (Float.compare(bmi,18.5f)>0 && Float.compare(bmi,25f)<=0){
            bmi_lbl = "Normal";
        }else if (Float.compare(bmi,25f)>0 && Float.compare(bmi,35f)<=0){
            bmi_lbl = "overweight";
        }else if (Float.compare(bmi,35f)>0 && Float.compare(bmi,40f)<=0){
            bmi_lbl = "severely overweight";
        }else if (Float.compare(bmi,40f)>0){
            bmi_lbl = "obsessive";
        }
        bmi_lbl = bmi + "\n" + bmi_lbl;
        result.setText(bmi_lbl);

    }

}
