package com.app.oshadhi.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PersonalDataUpdateActivity extends AppCompatActivity {
    DataBaseHelper db;
    EditText old_name, old_height, old_weight;
    Button save_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data_update);
        db = new DataBaseHelper(this);
        old_name  = findViewById(R.id.usr_name);
        old_height = findViewById(R.id.usr_height);
        old_weight = findViewById(R.id.usr_weight);
        save_btn = findViewById(R.id.edit);
        viewData();

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editData();
            }
        });
    }
    private  void editData(){
        String new_name = old_name.getText().toString().trim();
        String new_height = old_height.getText().toString();
        String new_weight = old_weight.getText().toString();

        if(!"".equals(new_height)  && !"".equals(new_weight)) {
            float height_val = Float.parseFloat(new_height);
            float weight_val = Float.parseFloat(new_weight);
            db.UpdateUser(new_name, height_val, weight_val);
            Toast.makeText(PersonalDataUpdateActivity.this, "Personal Data Updated Successfully", Toast.LENGTH_SHORT).show();
            Intent move = new Intent(PersonalDataUpdateActivity.this,PersonalDataViewActivity.class);
            startActivity(move);

        }

    }

    private void viewData(){
        Cursor res = db.viewUser("oshadhi");
        if(res.getCount() > 0 ){
            while(res.moveToNext()){
                old_name.setText(res.getString(0));
                old_height.setText(res.getString(1));
                old_weight.setText(res.getString(2));
            }

        }
    }
}
