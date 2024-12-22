// CashierActivity.java
package com.example.hospitalapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier);

        dbHelper = new DatabaseHelper(this);
        historyItems = new ArrayList<>();
        loadHistoryItems();

        ListView lvCashierHistory = findViewById(R.id.lvCashierHistory);
        EditText edtPatientName = findViewById(R.id.edtPatientName);
        EditText edtDoctorName = findViewById(R.id.edtDoctorName);
        EditText edtBillAmount = findViewById(R.id.edtBillAmount);
        Spinner spinnerPaymentMethod = findViewById(R.id.spinnerPaymentMethod);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        adapter = new CashierHistoryAdapter(this, historyItems);
        lvCashierHistory.setAdapter(adapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String patientName = edtPatientName.getText().toString();
                    String doctorName = edtDoctorName.getText().toString();
                    double billAmount = Double.parseDouble(edtBillAmount.getText().toString());
                    String paymentMethod = spinnerPaymentMethod.getSelectedItem().toString();

                    CashierHistoryItem newItem = new CashierHistoryItem(patientName, doctorName, billAmount, paymentMethod);
                    historyItems.add(newItem);
                    saveHistoryItem(newItem);
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.e("CashierActivity", "Error saving history item", e);
                }
            }
        });
    }

    private void loadHistoryItems() {
        Cursor cursor = null;
        try {
            cursor = dbHelper.getAllCashierHistory();
            while (cursor.moveToNext()) {
                int patientNameIndex = cursor.getColumnIndex("patient_name");
                int doctorNameIndex = cursor.getColumnIndex("doctor_name");
                int billAmountIndex = cursor.getColumnIndex("bill_amount");
                int paymentMethodIndex = cursor.getColumnIndex("payment_method");

                if (patientNameIndex >= 0 && doctorNameIndex >= 0 && billAmountIndex >= 0 && paymentMethodIndex >= 0) {
                    String patientName = cursor.getString(patientNameIndex);
                    String doctorName = cursor.getString(doctorNameIndex);
                    double billAmount = cursor.getDouble(billAmountIndex);
                    String paymentMethod = cursor.getString(paymentMethodIndex);

                    CashierHistoryItem item = new CashierHistoryItem(patientName, doctorName, billAmount, paymentMethod);
                    historyItems.add(item);
                }
            }
        } catch (Exception e) {
            Log.e("CashierActivity", "Error loading history items", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private void saveHistoryItem(CashierHistoryItem item) {
        try {
            dbHelper.insertCashierHistory(item.getPatientName(), item.getDoctorName(), item.getBillAmount(), item.getPaymentMethod());
        } catch (Exception e) {
            Log.e("CashierActivity", "Error saving history item to database", e);
        }
    }
}