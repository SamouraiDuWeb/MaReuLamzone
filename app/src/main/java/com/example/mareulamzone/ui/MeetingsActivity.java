package com.example.mareulamzone.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareulamzone.R;
import com.example.mareulamzone.di.DI;
import com.example.mareulamzone.model.Meeting;
import com.example.mareulamzone.service.MeetingApiService;
import com.example.mareulamzone.ui.adapters.MyMeetingsRecyclerViewAdapter;
import com.example.mareulamzone.ui.fragments.MeetingsFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MeetingsActivity extends AppCompatActivity {

    private FloatingActionButton fbtnAddMeeting;
    private MeetingApiService mApiService = DI.getMeetingApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fbtnAddMeeting = findViewById(R.id.fbtn_add_meeting);
        fbtnAddMeeting.setOnClickListener(v -> {
            Intent intent = new Intent(MeetingsActivity.this, AddMeetingActivity.class);
            startActivity(intent);
        });
    }
}