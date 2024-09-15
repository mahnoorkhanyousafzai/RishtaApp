package com.example.rishtaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private Context context;
    private String[] actionNames;
    private int[] actionIcons;
    private FragmentManager fragmentManager;

    public CardAdapter(Context context, String[] actionNames, int[] actionIcons, FragmentManager fragmentManager) {
        this.context = context;
        this.actionNames = actionNames;
        this.actionIcons = actionIcons;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.actionName.setText(actionNames[position]);
        holder.actionIcon.setImageResource(actionIcons[position]);

        holder.itemView.setOnClickListener(v -> {
            // Pass click event to load the corresponding fragment
            if (context instanceof DashboardActivity) {
                ((DashboardActivity) context).loadFragment(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return actionNames.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView actionName;
        ImageView actionIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            actionIcon = itemView.findViewById(R.id.card_icon);
            actionName = itemView.findViewById(R.id.card_text);

        }
    }
}