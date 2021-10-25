package com.example.mareulamzone.ui.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mareulamzone.R;
import com.example.mareulamzone.di.DI;
import com.example.mareulamzone.service.MeetingApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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