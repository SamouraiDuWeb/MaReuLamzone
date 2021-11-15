package com.example.mareulamzone.ui.adapters;

import static android.app.PendingIntent.getActivity;
import static androidx.core.content.ContextCompat.startActivity;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareulamzone.R;
import com.example.mareulamzone.di.DI;
import com.example.mareulamzone.model.Meeting;
import com.example.mareulamzone.service.MeetingApiService;
import com.example.mareulamzone.ui.Activities.DetailMeetingsActivity;

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

        String nameMeeting = meeting.getSubject() + " - " + strDate + " - " + meeting.getRoom().getName();

        List<String> currentMeetingUsersEmail = meeting.getUsers();

        String u_Mail = String.join(", ", currentMeetingUsersEmail);
        holder.meetingUsers.setText(u_Mail);

        holder.name.setText(nameMeeting);

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(v.getContext(), DetailMeetingsActivity.class);
            intent.putExtra("currentMeeting", meeting.getId());
            startActivity(v.getContext(), intent, null);
        });

        holder.mDeleteButton.setOnClickListener(v -> {
            mApiService.removeMeeting(meeting);
            notifyItemRemoved(holder.getAbsoluteAdapterPosition());
            notifyItemRangeChanged(holder.getAbsoluteAdapterPosition(), getItemCount());
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, meetingUsers;
        ImageView image;
        ImageButton mDeleteButton;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.item_list_name);
            image = view.findViewById(R.id.item_list_color);
            mDeleteButton = view.findViewById(R.id.item_list_delete_button);
            meetingUsers = view.findViewById(R.id.item_list_user);
        }
    }

}