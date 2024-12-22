package com.example.hospitalapp;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EditPatientActivity extends AppCompatActivity {

    private EditText etFullName, etGender, etAge, etAddress, etComplaint;
    private DatabaseHelper databaseHelper;
    private int patientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient);

        etFullName = findViewById(R.id.etFullName);
        etGender = findViewById(R.id.etGender);
        etAge = findViewById(R.id.etAge);
        etAddress = findViewById(R.id.etAddress);
        etComplaint = findViewById(R.id.etComplaint);
        Button btnSave = findViewById(R.id.btnSave);

        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        patientId = intent.getIntExtra("id", -1);
        etFullName.setText(intent.getStringExtra("fullName"));
        etGender.setText(intent.getStringExtra("gender"));
        etAge.setText(String.valueOf(intent.getIntExtra("age", 0)));
        etAddress.setText(intent.getStringExtra("address"));
        etComplaint.setText(intent.getStringExtra("complaint"));

        btnSave.setOnClickListener(v -> showConfirmationDialog());

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Edit Confirmation")
                .setMessage("Are you sure the data is correct?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    updatePatient();
                    finish();
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void updatePatient() {
        String fullName = etFullName.getText().toString();
        String gender = etGender.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());
        String address = etAddress.getText().toString();
        String complaint = etComplaint.getText().toString();

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fullName", fullName);
        values.put("gender", gender);
        values.put("age", age);
        values.put("address", address);
        values.put("complaint", complaint);

        db.update("user_details", values, "id = ?", new String[]{String.valueOf(patientId)});
        db.close();
    }
}