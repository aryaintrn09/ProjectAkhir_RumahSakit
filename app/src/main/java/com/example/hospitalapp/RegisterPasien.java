package com.example.hospitalapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterPasien extends AppCompatActivity {
    EditText edtFullName, edtGender, edtAge, edtAddress, edtComplaint;
    Button btnRegister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pasien);

        edtFullName = findViewById(R.id.edtFullName);
        edtGender = findViewById(R.id.edtGender);
        edtAge = findViewById(R.id.edtAge);
        edtAddress = findViewById(R.id.edtAddress);
        edtComplaint = findViewById(R.id.edtComplaint);
        btnRegister = findViewById(R.id.btnRegister);

        db = new DatabaseHelper(this);

        btnRegister.setOnClickListener(view -> {
            String fullName = edtFullName.getText().toString();
            String gender = edtGender.getText().toString();
            String ageStr = edtAge.getText().toString();
            String address = edtAddress.getText().toString();
            String complaint = edtComplaint.getText().toString();

            try {
                int age = Integer.parseInt(ageStr);
                if (db.insertPasien(fullName, gender, age, address, complaint)) {
                    Toast.makeText(RegisterPasien.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterPasien.this, QueueListActivity.class));
                    finish();
                } else {
                    Toast.makeText(RegisterPasien.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(RegisterPasien.this, "Invalid age format", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}