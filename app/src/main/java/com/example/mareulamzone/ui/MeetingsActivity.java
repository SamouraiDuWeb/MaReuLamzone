package com.example.mareulamzone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mareulamzone.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MeetingsActivity extends AppCompatActivity {

    private FloatingActionButton fbtnAddMeeting;

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