package com.app.oshadhi.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PersonalDataViewActivity extends AppCompatActivity {
    DataBaseHelper db;
    TextView name, height, weight;
    Button update_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data_view);
        db = new DataBaseHelper(this);
        name  = findViewById(R.id.name);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        update_btn = findViewById(R.id.update);
        viewData();

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PUpdateIntent = new Intent(PersonalDataViewActivity.this, PersonalDataUpdateActivity.class);
                startActivity(PUpdateIntent);
                finish();
            }
        });
    }
    private void viewData(){
        Cursor res = db.viewUser("oshadhi");
        if(res.getCount() > 0 ){
            while(res.moveToNext()){
                name.setText(res.getString(0));
                height.setText(res.getString(1));
                weight.setText(res.getString(2));
            }

        }
    }


}
