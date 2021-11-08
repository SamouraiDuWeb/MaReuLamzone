package com.example.mareulamzone.ui.Activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mareulamzone.R;
import com.example.mareulamzone.di.DI;
import com.example.mareulamzone.model.Meeting;
import com.example.mareulamzone.service.MeetingApiService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DetailMeetingsActivity extends AppCompatActivity {

    private MeetingApiService apiService = DI.getMeetingApiService();

    private ImageView itemListColor;
    private TextView tvDetailMeetingRoom;
    private TextView tvDetailMeetingSubject;
    private TextView tvDetailMeetingDate;
    private TextView tvDetailMeetingDuration;
    private Meeting meeting;
    private TextView tvDetailMeetingUsers;

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

        String pattern = "yyyy-MM-dd-hh-mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(meeting.getDate());
        tvDetailMeetingDate.setText(date);

        List<String> currentMeetingUsersEmail = meeting.getUsers();

        StringBuilder u_Mail = new StringBuilder();
        for (String u_mail: currentMeetingUsersEmail){
            if (u_mail.equals("")){
                u_Mail.append(u_mail);
            } else {
                u_Mail.append(", ");
                u_Mail.append(u_mail);
            }
        }
        tvDetailMeetingUsers.setText(u_Mail);

    }

    private void initView() {
        itemListColor = findViewById(R.id.item_list_color);
        tvDetailMeetingRoom = findViewById(R.id.tv_detail_meeting_room);
        tvDetailMeetingSubject = findViewById(R.id.tv_detail_meeting_subject);
        tvDetailMeetingDate = findViewById(R.id.tv_detail_meeting_date);
        tvDetailMeetingDuration = findViewById(R.id.tv_detail_meeting_duration);
        tvDetailMeetingUsers = findViewById(R.id.tv_detail_users);
    }
}
