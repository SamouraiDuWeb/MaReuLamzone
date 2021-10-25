package com.example.mareulamzone.ui.Activities;

import static com.example.mareulamzone.service.LamzoneGenerator.USERS;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareulamzone.R;
import com.example.mareulamzone.di.DI;
import com.example.mareulamzone.model.MeetingRoom;
import com.example.mareulamzone.model.User;
import com.example.mareulamzone.service.MeetingApiService;
import com.example.mareulamzone.ui.adapters.UserRecyclerViewAdapter;

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
    String meetingDuration;

    long dateInMillis = 0;
    int year1 = -1, month1, dayOfMonth1, hourOfDay1 = -1, minute1;
    private DatePicker dpEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_meeting_activity);

        mMeetingApiService = DI.getMeetingApiService();
        listParticipant = new ArrayList<>();
        listEmail = new ArrayList<>();

        initView();

        //init Date Picker
        initDatePicker();

        //initDurationSpinner
        initDurationSpinner();

        //init spinner meeting room
        initSpinnerRoom();

        //init add user to meeting
        initAddUserToMeeting();

        btnAddMeeting.setOnClickListener(v -> {
            Toast.makeText(AddMeetingActivity.this, "Checking...", Toast.LENGTH_SHORT);
            System.out.println("Checking...");
            // check user entries and add event to list
            if (checkUserEntries()) {
                mMeetingApiService.createMeeting(mMeetingApiService.getMeetings().size() + 1,
                        submitDate,
                        meetingRoomChoose,
                        etSubjectMeeting.getText().toString(),
                        listEmail,
                        meetingDuration);

                System.out.println("////////////////////////////////" + mMeetingApiService.getMeetings());

                Toast.makeText(AddMeetingActivity.this, "Meeting sauvegardé", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddMeetingActivity.this, MeetingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initDurationSpinner() {

        List<String> durationList = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, durationList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMeetingRoom.setAdapter(adapter);
        spMeetingRoom.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        meetingDuration = "30 minutes";
                        break;
                    case 1:
                        meetingDuration = "1 heure";
                        break;
                    case 2:
                        meetingDuration = "2 heures";
                    default:
                        meetingDuration = "30 minutes";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                meetingDuration = "30 minutes";
            }
        });
    }

    private void addUserToList(User userToAdd) {

        listEmail.add(userToAdd.getEmail());
        System.out.println("**********" + listEmail);
        UserRecyclerViewAdapter adapterRv = new UserRecyclerViewAdapter(listEmail);
        rvParticipant.setAdapter(adapterRv);
        rvParticipant.setLayoutManager(new LinearLayoutManager(this));
        Toast.makeText(this, "Sauvegardé !", Toast.LENGTH_SHORT).show();
    }

    private void initAddUserToMeeting() {

        List<String> list = new ArrayList<>();

        for (int i = 0; i < USERS.size(); i++) {

            list.add(USERS.get(i).getEmail());
        }

        System.out.println("/////////////////////////" + list);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spAddUserToMeeting.setAdapter(adapter);
        spAddUserToMeeting.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                userToAdd = mMeetingApiService.getUsers().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addUserToList(userToAdd);
            }
        });

    }

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
                meetingRoomChoose = mMeetingApiService.getMeetingRooms().get(0);
            }
        });
    }

    private boolean checkUserEntries() {

        //check spinner meetingroom
        //check edittext subject
        //check timepicker
        //check spinner duration
        //check email user to meeting
        if (meetingRoomChoose == null) {
            Toast.makeText(AddMeetingActivity.this, "Error meetingroom", Toast.LENGTH_LONG).show();
            return false;
        } else if (etSubjectMeeting == null) {
            Toast.makeText(AddMeetingActivity.this, "Error subject", Toast.LENGTH_LONG).show();
            return false;
        } else if (submitDate == null) {
            Toast.makeText(AddMeetingActivity.this, "Error date", Toast.LENGTH_LONG).show();
            return false;
        } else if (spMeetingDuration == null) {
            Toast.makeText(AddMeetingActivity.this, "Error duration", Toast.LENGTH_LONG).show();
            return false;
        } else if (listEmail == null) {
            Toast.makeText(AddMeetingActivity.this, "Error list email", Toast.LENGTH_LONG).show();
            return false;
        } else {
             return true;
        }
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
