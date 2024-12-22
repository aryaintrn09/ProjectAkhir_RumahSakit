package com.example.hospitalapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    RecyclerView recyclerViewMenu;
    List<String> menuItems = Arrays.asList("Doctor Schedule", "Register Patient", "User Profile", "Queue List", "Cashier", "Logout");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        recyclerViewMenu = findViewById(R.id.recyclerViewMenu);
        recyclerViewMenu.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns

        MenuAdapter adapter = new MenuAdapter(this, menuItems, item -> {
            switch (item) {
                case "Doctor Schedule":
                    startActivity(new Intent(MenuActivity.this, DoctorScheduleActivity.class));
                    break;
                case "Register Patient":
                    startActivity(new Intent(MenuActivity.this, RegisterPasien.class));
                    break;
                case "Queue List":
                    startActivity(new Intent(MenuActivity.this, QueueListActivity.class));
                    break;
                case "Cashier":
                    startActivity(new Intent(MenuActivity.this, CashierActivity.class));
                    break;
                case "Logout":
                    startActivity(new Intent(MenuActivity.this, LoginActivity.class));
                    finish();
                    break;
            }
        });

        recyclerViewMenu.setAdapter(adapter);
    }
}