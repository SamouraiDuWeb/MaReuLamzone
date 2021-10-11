package com.example.mareulamzone.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareulamzone.R;
import com.example.mareulamzone.di.DI;
import com.example.mareulamzone.model.Meeting;
import com.example.mareulamzone.service.DummyMeetingApiService;
import com.example.mareulamzone.service.MeetingApiService;
import com.example.mareulamzone.ui.adapters.MyMeetingsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MeetingsFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private MeetingApiService mApiService = DI.getMeetingApiService();


    public MeetingsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_meetings_list, container, false);

        MeetingApiService mMeetingApiService = DI.getMeetingApiService();
        ArrayList listTest = new ArrayList<String>();
        listTest.add("jean");
        listTest.add("random");

        mMeetingApiService.createMeeting(mMeetingApiService.getMeetings().size() + 1,
                "sujet test",
                new Date(Calendar.getInstance().getTimeInMillis()),
                mMeetingApiService.getMeetingRooms().get(1),
                "sujet test",
                listTest, "30 minutes");

        // Set the adapter
        List<Meeting> mMeetings = mApiService.getMeetings();

        System.out.println("////////////////MEETINGS///////////////" + mMeetings);

        Context context = this.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        MyMeetingsRecyclerViewAdapter mAdapter = new MyMeetingsRecyclerViewAdapter(mMeetings);
        recyclerView.setAdapter(mAdapter);

        return view;
    }
}