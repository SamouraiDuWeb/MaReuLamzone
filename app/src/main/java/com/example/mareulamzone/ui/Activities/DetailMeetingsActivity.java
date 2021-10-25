package com.example.mareulamzone.ui.Activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mareulamzone.R;
import com.example.mareulamzone.di.DI;
import com.example.mareulamzone.model.Meeting;
import com.example.mareulamzone.service.MeetingApiService;

public class DetailMeetingsActivity extends AppCompatActivity {

    private MeetingApiService apiService = DI.getMeetingApiService();

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
        int id = (int) extras.get("currentMeeting");

        initInfos(id);
    }

    private void initInfos(int id) {

        meeting = apiService.getMeeting(id);

        tvDetailMeetingRoom.setText(meeting.getRoom().getName());
        tvDetailMeetingSubject.setText(meeting.getSubject());
        tvDetailMeetingDuration.setText(meeting.getDuration());

        tvDetailMeetingDate.setText(meeting.getDate().toString());
    }

    private void initView() {
        itemListColor = findViewById(R.id.item_list_color);
        tvDetailMeetingRoom = findViewById(R.id.tv_detail_meeting_room);
        tvDetailMeetingSubject = findViewById(R.id.tv_detail_meeting_subject);
        tvDetailMeetingDate = findViewById(R.id.tv_detail_meeting_date);
        tvDetailMeetingDuration = findViewById(R.id.tv_detail_meeting_duration);
    }
}
