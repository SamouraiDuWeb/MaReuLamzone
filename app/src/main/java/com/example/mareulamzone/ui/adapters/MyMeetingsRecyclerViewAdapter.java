package com.example.mareulamzone.ui.adapters;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareulamzone.R;
import com.example.mareulamzone.di.DI;
import com.example.mareulamzone.model.Meeting;
import com.example.mareulamzone.service.MeetingApiService;
import com.example.mareulamzone.ui.DetailMeetingsActivity;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyMeetingsRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingsRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;
    private final LayoutInflater mInflater;
    private MeetingApiService mApiService;
    public MyMeetingsRecyclerViewAdapter(Context context, List<Meeting> items) {

        this.mInflater = LayoutInflater.from(context);
        mMeetings = items;
    }

    @NonNull
    @Override
    public MyMeetingsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater
                .inflate(R.layout.fragment_meetings, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyMeetingsRecyclerViewAdapter.ViewHolder holder, int position) {

        mApiService = DI.getMeetingApiService();
        final Meeting meeting = mMeetings.get(holder.getAbsoluteAdapterPosition());

        Date date = meeting.getDate();
        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.FRANCE);
        String strDate = dateFormat.format(date);

        String nameMeeting = meeting.getName() + " - " + strDate + " - " + meeting.getSubject();

        List currentMeetingUsersEmail = meeting.getUsers();

        holder.name.setText(nameMeeting);
        holder.meeting_users.setText(currentMeetingUsersEmail.toString());

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(v.getContext(), DetailMeetingsActivity.class);
            intent.putExtra("currentMeeting", (Parcelable) meeting);
            startActivity(v.getContext(), intent, null);
        });

        holder.mDeleteButton.setOnClickListener(v -> {
            mApiService.removeMeeting(meeting);
            notifyItemRemoved(holder.getAbsoluteAdapterPosition());
            notifyItemRangeChanged(holder.getAbsoluteAdapterPosition(),getItemCount());
        });
        }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, meeting_users;
        ImageView image;
        ImageButton mDeleteButton;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.item_list_name);
            image = view.findViewById(R.id.item_list_color);
            mDeleteButton = view.findViewById(R.id.item_list_delete_button);
            meeting_users = view.findViewById(R.id.item_list_user);
        }
    }

}