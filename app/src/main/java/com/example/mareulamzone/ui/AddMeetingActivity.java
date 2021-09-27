package com.example.mareulamzone.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareulamzone.R;
import com.example.mareulamzone.di.DI;
import com.example.mareulamzone.model.MeetingRoom;
import com.example.mareulamzone.model.User;
import com.example.mareulamzone.service.MeetingApiService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddMeetingActivity extends AppCompatActivity {

    private Spinner spMeetingRoom;
    private EditText etSubjectMeeting;
    private TimePicker tpEvent;
    private Spinner spMeetingDuration;
    private Spinner spAddUserToMeeting;
    private Button btnAddUser;
    private RecyclerView rvParticipant;
    private Button btnAddMeeting;

    MeetingApiService mMeetingApiService;
    MeetingRoom meetingRoomChoose;
    User userToAdd;
    List<String> listEmail;

    Date submitDate;
    ArrayList<User> listParticipant = new ArrayList<>();

    long dateInMillis = 0;
    int year1 = -1, month1, dayOfMonth1, hourOfDay1 = -1, minute1;
    private DatePicker dpEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_meeting_activity);

        mMeetingApiService = DI.getMeetingApiService();

        initView();

        //init Date Picker
        initDatePicker();

        //init spinner meeting room
        initSpinnerRoom();

        //init add user to meeting
        //initAddUserToMeeting();

        btnAddMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check user entries and add event to list
                if (checkUserEntries()) {
                    //meetingApiService.createMeeting


                }
            }
        });
    }

//    private void initAddUserToMeeting() {
//
//        List<User> list = new ArrayList<>();
//        for (User user : mMeetingApiService.getUsers()) {
//            list.add(user);
//        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, listEmail);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spAddUserToMeeting.setAdapter(adapter);
//        spAddUserToMeeting.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                userToAdd = mMeetingApiService.getUsers().get(position);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//    }

//    private void checkIfUserValid(ArrayList<User> users) {
//
//        for(String i : mMeetingApiService.getAllEmails()){
//            if(listEmail.getEmail().contains(i) && !users.contains(mMeetingApiService.getUser(i))){
//                users.add(mMeetingApiService.getUser(i));
//            }
//        }
//    }


    private void initDatePicker() {

        tpEvent.setIs24HourView(true);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        dpEvent.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                year1 = year;
                month1 = month;
                dayOfMonth1 = dayOfMonth;
                if (hourOfDay1 != -1) {
                    submitDate = getDate(year1, month1, dayOfMonth1, hourOfDay1, minute1);
                }
            }
        });
        tpEvent.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                hourOfDay1 = hourOfDay;
                minute1 = minute;
                if (year1 != -1) {
                    submitDate = getDate(year1, month1, dayOfMonth1, hourOfDay1, minute1);
                }
            }
        });
    }

    private Date getDate(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth, hourOfDay, minute);
        dateInMillis = calendar.getTimeInMillis();
        return new Date(dateInMillis);
    }

    private void initSpinnerRoom() {

        List<String> list = new ArrayList<>();
        for (MeetingRoom room : mMeetingApiService.getMeetingRooms()) {
            list.add(room.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMeetingRoom.setAdapter(adapter);
        spMeetingRoom.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                meetingRoomChoose = mMeetingApiService.getMeetingRooms().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private boolean checkUserEntries() {

        //check spinner meetingroom
        //check edittext subject
        //check timepicker
        //check spinner duration
        //check email user to meeting

        return false;
    }

    private void initView() {
        spMeetingRoom = findViewById(R.id.sp_meeting_room);
        etSubjectMeeting = findViewById(R.id.et_subject_meeting);
        tpEvent = findViewById(R.id.tp_event);
        spMeetingDuration = findViewById(R.id.sp_meeting_duration);
        spAddUserToMeeting = findViewById(R.id.sp_add_user_to_meeting);
        btnAddUser = findViewById(R.id.btn_add_user);
        rvParticipant = findViewById(R.id.rv_participant);
        btnAddMeeting = findViewById(R.id.btn_add_meeting);
        dpEvent = findViewById(R.id.dp_event);
    }
}
