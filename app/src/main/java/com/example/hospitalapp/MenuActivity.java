package com.example.hospitalapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    Button btnDoctorSchedule, btnQueueList, btnUserProfile, btnAdminQueueList, btnCashier, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnDoctorSchedule = findViewById(R.id.btnDoctorSchedule);
        btnQueueList = findViewById(R.id.btnQueueList);
        btnUserProfile = findViewById(R.id.btnUserProfile);
        btnAdminQueueList = findViewById(R.id.btnAdminQueueList);
        btnCashier = findViewById(R.id.btnCashier);
        btnLogout = findViewById(R.id.btnLogout);

        btnDoctorSchedule.setOnClickListener(view -> {
            startActivity(new Intent(MenuActivity.this, DoctorScheduleActivity.class));
        });

        btnQueueList.setOnClickListener(view -> {
            startActivity(new Intent(MenuActivity.this, RegisterPasien.class));
        });

        btnUserProfile.setOnClickListener(view -> {
            startActivity(new Intent(MenuActivity.this, UserProfileActivity.class));
        });

        btnAdminQueueList.setOnClickListener(view -> {
            startActivity(new Intent(MenuActivity.this, QueueListActivity.class));
        });

        btnCashier.setOnClickListener(view -> {
            startActivity(new Intent(MenuActivity.this, CashierActivity.class));
        });

        btnLogout.setOnClickListener(view -> {
            startActivity(new Intent(MenuActivity.this, LoginActivity.class));
            finish();
        });
    }
}