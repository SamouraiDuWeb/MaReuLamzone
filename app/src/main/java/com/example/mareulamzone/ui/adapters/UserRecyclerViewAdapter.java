package com.example.mareulamzone.ui.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mareulamzone.R;

import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder>{

    private final List<String> mUsers;

    // RecyclerView recyclerView;
    public UserRecyclerViewAdapter(List<String> users) {
        this.mUsers = users;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_users, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final String userEmail = mUsers.get(holder.getAbsoluteAdapterPosition());
        holder.textView.setText(userEmail);
        holder.imageView.setOnClickListener(view -> {
            mUsers.remove(userEmail);
            notifyItemRemoved(holder.getAbsoluteAdapterPosition());
            notifyItemRangeChanged(holder.getAbsoluteAdapterPosition(),getItemCount());
        });
    }



    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.tv_email_user);
            this.imageView = itemView.findViewById(R.id.iv_remove_user);
        }
    }
}
