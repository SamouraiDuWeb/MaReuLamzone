package com.example.mareulamzone.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mareulamzone.R;
import com.example.mareulamzone.model.Meeting;

public class DetailMeetingsActivity extends AppCompatActivity {

    private ImageView itemListColor;
    private TextView tvDetailMeetingRoom;
    private TextView tvDetailMeetingSubject;
    private TextView tvDetailMeetingDate;
    private TextView tvDetailMeetingDuration;
    private Meeting meeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_meetings_activity);
        initView();
        Bundle extras = getIntent().getExtras();
        meeting = (Meeting) extras.get("currentMeeting");
        initInfos(meeting);
    }

    private void initInfos(Meeting meeting) {

        tvDetailMeetingRoom.setText(meeting.getRoom().toString());
        tvDetailMeetingSubject.setText(meeting.getSubject());
        tvDetailMeetingDate.setText(meeting.getDate().toString());
        tvDetailMeetingDuration.setText(meeting.getDuration());

    }

    private void initView() {
        itemListColor = findViewById(R.id.item_list_color);
        tvDetailMeetingRoom = findViewById(R.id.tv_detail_meeting_room);
        tvDetailMeetingSubject = findViewById(R.id.tv_detail_meeting_subject);
        tvDetailMeetingDate = findViewById(R.id.tv_detail_meeting_date);
        tvDetailMeetingDuration = findViewById(R.id.tv_detail_meeting_duration);
    }
}
