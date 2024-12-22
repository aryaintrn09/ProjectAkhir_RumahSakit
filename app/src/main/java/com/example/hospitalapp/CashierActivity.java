package com.example.hospitalapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class CashierActivity extends AppCompatActivity {
    private List<CashierHistoryItem> historyItems;
    private CashierHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier);

        ListView lvCashierHistory = findViewById(R.id.lvCashierHistory);
        EditText edtPatientName = findViewById(R.id.edtPatientName);
        EditText edtDoctorName = findViewById(R.id.edtDoctorName);
        EditText edtBillAmount = findViewById(R.id.edtBillAmount);
        Spinner spinnerPaymentMethod = findViewById(R.id.spinnerPaymentMethod);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        historyItems = new ArrayList<>();


        adapter = new CashierHistoryAdapter(this, historyItems);
        lvCashierHistory.setAdapter(adapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientName = edtPatientName.getText().toString();
                String doctorName = edtDoctorName.getText().toString();
                double billAmount = Double.parseDouble(edtBillAmount.getText().toString());
                String paymentMethod = spinnerPaymentMethod.getSelectedItem().toString();

                CashierHistoryItem newItem = new CashierHistoryItem(patientName, doctorName, billAmount, paymentMethod);
                historyItems.add(newItem);
                adapter.notifyDataSetChanged();
            }
        });
    }
}