package com.myapplicationdev.android.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText etName;
EditText etGPA;
RadioGroup rgGender;
RadioButton rgMale;
Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.rg);
        btnSave = findViewById(R.id.buttonSave);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = etName.getText().toString();
                float GPA = Float.parseFloat(etGPA.getText().toString());
                int genderId = rgGender.getCheckedRadioButtonId();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putString("name", strName);
                prefEdit.putFloat("gpa", GPA);
                prefEdit.putInt("genderId", genderId);
                prefEdit.commit();
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();

        String strName = etName.getText().toString();
        Float GPA2 = Float.parseFloat(etGPA.getText().toString());
        int genderId = rgGender.getCheckedRadioButtonId();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefEdit = prefs.edit();

       prefEdit.putString("name", strName);
        prefEdit.putFloat("gpa", GPA2);
        prefEdit.putInt("genderId", genderId);

        prefEdit.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String msg = prefs.getString("name","");
        float GPA = prefs.getFloat("gpa",0);
        int genderId = prefs.getInt("genderId",R.id.radioButtonMale);


        etName.setText(msg);
        etGPA.setText(GPA + "");
        rgGender.check(genderId);

    }
}
