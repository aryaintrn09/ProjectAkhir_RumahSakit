package com.example.hospitalapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class QueueListActivity extends AppCompatActivity implements PatientAdapter.OnItemClickListener {

    RecyclerView recyclerViewQueueList;
    DatabaseHelper databaseHelper;
    PatientAdapter patientAdapter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_list);

        recyclerViewQueueList = findViewById(R.id.recyclerViewQueueList);
        recyclerViewQueueList.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = new DatabaseHelper(this);
        cursor = databaseHelper.getAllPatients();

        patientAdapter = new PatientAdapter(this, cursor, this);
        recyclerViewQueueList.setAdapter(patientAdapter);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_view_item_spacing);
        recyclerViewQueueList.addItemDecoration(new SpacingItemDecoration(spacingInPixels));

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cursor = databaseHelper.getAllPatients();
        patientAdapter.swapCursor(cursor);
    }

    @Override
    public void onEditClick(int position) {
        if (cursor.moveToPosition(position)) {
            Intent intent = new Intent(this, EditPatientActivity.class);
            intent.putExtra("id", cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            intent.putExtra("fullName", cursor.getString(cursor.getColumnIndexOrThrow("fullName")));
            intent.putExtra("gender", cursor.getString(cursor.getColumnIndexOrThrow("gender")));
            intent.putExtra("age", cursor.getInt(cursor.getColumnIndexOrThrow("age")));
            intent.putExtra("address", cursor.getString(cursor.getColumnIndexOrThrow("address")));
            intent.putExtra("complaint", cursor.getString(cursor.getColumnIndexOrThrow("complaint")));
            startActivity(intent);
        }
    }

    @Override
    public void onDeleteClick(int position) {
        if (cursor.moveToPosition(position)) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));

            new AlertDialog.Builder(this)
                    .setTitle("Delete Confirmation")
                    .setMessage("Are you sure you want to delete this patient?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        databaseHelper.deletePatient(id);
                        cursor = databaseHelper.getAllPatients();
                        patientAdapter.swapCursor(cursor);
                        patientAdapter.notifyItemRemoved(position);
                        patientAdapter.notifyItemRangeChanged(position, cursor.getCount());
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
    }

    private static class SpacingItemDecoration extends RecyclerView.ItemDecoration {
        private final int spacing;

        public SpacingItemDecoration(int spacing) {
            this.spacing = spacing;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            outRect.top = spacing;
            outRect.left = spacing;
            outRect.right = spacing;
            outRect.bottom = spacing;
        }
    }
}