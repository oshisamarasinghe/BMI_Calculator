package com.app.oshadhi.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PersonalDataHandleActivity extends AppCompatActivity {
    DataBaseHelper db;
    EditText name, height, weight;
    Button save_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data_handle);
        db = new DataBaseHelper(this);
        name  = findViewById(R.id.usr_name);
        height = findViewById(R.id.usr_height);
        weight = findViewById(R.id.usr_weight);
        save_btn = findViewById(R.id.save);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = name.getText().toString().trim();
                String usr_height = height.getText().toString();
                String usr_weight = weight.getText().toString();

                if(!"".equals(usr_height)  && !"".equals(usr_weight)){
                    float height_val  = Float.parseFloat(usr_height);
                    float weight_val = Float.parseFloat(usr_weight);
                    Long result = db.addUser(user_name,height_val,weight_val);

                    if (result > 0) {
                        Toast.makeText(PersonalDataHandleActivity.this, "Successfully Saved", Toast.LENGTH_SHORT).show();
                        Intent move = new Intent(PersonalDataHandleActivity.this, MainActivity.class);
                        startActivity(move);
                    }
                    else {

                        Toast.makeText(PersonalDataHandleActivity.this, "Save Unsuccessful!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }
}
