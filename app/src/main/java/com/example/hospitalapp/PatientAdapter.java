package com.example.hospitalapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {

    private Context context;
    private Cursor cursor;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    public PatientAdapter(Context context, Cursor cursor, OnItemClickListener listener) {
        this.context = context;
        this.cursor = cursor;
        this.listener = listener;
    }

    @Override
    public PatientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_patient, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PatientViewHolder holder, int position) {
        if (cursor.moveToPosition(position)) {
            String fullName = cursor.getString(cursor.getColumnIndexOrThrow("fullName"));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow("gender"));
            int age = cursor.getInt(cursor.getColumnIndexOrThrow("age"));
            String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
            String complaint = cursor.getString(cursor.getColumnIndexOrThrow("complaint"));

            holder.tvFullName.setText("Nama: " + fullName);
            holder.tvGender.setText("Gender: " + gender);
            holder.tvAge.setText("Age: " + age);
            holder.tvAddress.setText("Address: " + address);
            holder.tvComplaint.setText("Complaint: " + complaint);
        }
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class PatientViewHolder extends RecyclerView.ViewHolder {
        TextView tvFullName, tvGender, tvAge, tvAddress, tvComplaint;

        public PatientViewHolder(View itemView) {
            super(itemView);
            tvFullName = itemView.findViewById(R.id.tvFullName);
            tvGender = itemView.findViewById(R.id.tvGender);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvComplaint = itemView.findViewById(R.id.tvComplaint);

            itemView.findViewById(R.id.btnEdit).setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onEditClick(position);
                    }
                }
            });

            itemView.findViewById(R.id.btnDelete).setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onDeleteClick(position);
                    }
                }
            });
        }
    }

    public void swapCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }
        cursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}