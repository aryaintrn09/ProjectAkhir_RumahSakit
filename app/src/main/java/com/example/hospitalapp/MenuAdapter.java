package com.example.hospitalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private Context context;
    private List<String> menuItems;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    public MenuAdapter(Context context, List<String> menuItems, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.menuItems = menuItems;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu_button, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        String item = menuItems.get(position);
        holder.btnMenuItem.setText(item);
        holder.btnMenuItem.setOnClickListener(v -> onItemClickListener.onItemClick(item));

        // Set image based on item
        switch (item) {
            case "Doctor Schedule":
                holder.btnMenuItem.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.doctor, 0, 0);
                break;
            case "Register Patient":
                holder.btnMenuItem.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.edit, 0, 0);
                break;
            case "Queue List":
                holder.btnMenuItem.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.queue, 0, 0);
                break;
            case "Cashier":
                holder.btnMenuItem.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.cashmachine, 0, 0);
                break;
            case "Logout":
                holder.btnMenuItem.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.exit, 0, 0);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        Button btnMenuItem;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            btnMenuItem = itemView.findViewById(R.id.btnMenuItem);
        }
    }
}