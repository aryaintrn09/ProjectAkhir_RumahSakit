package com.example.hospitalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CashierHistoryAdapter extends ArrayAdapter<CashierHistoryItem> {
    public CashierHistoryAdapter(Context context, List<CashierHistoryItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CashierHistoryItem item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_cashier_history, parent, false);
        }

        TextView tvPatientName = convertView.findViewById(R.id.tvPatientName);
        TextView tvDoctorName = convertView.findViewById(R.id.tvDoctorName);
        TextView tvBillAmount = convertView.findViewById(R.id.tvBillAmount);
        TextView tvPaymentMethod = convertView.findViewById(R.id.tvPaymentMethod);

        tvPatientName.setText(item.getPatientName());
        tvDoctorName.setText(item.getDoctorName());
        tvBillAmount.setText(String.valueOf(item.getBillAmount()));
        tvPaymentMethod.setText(item.getPaymentMethod());

        return convertView;
    }
}