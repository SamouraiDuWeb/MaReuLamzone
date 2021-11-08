package com.example.mareulamzone.ui.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareulamzone.R;
import com.example.mareulamzone.di.DI;
import com.example.mareulamzone.model.Meeting;
import com.example.mareulamzone.service.DummyMeetingApiService;
import com.example.mareulamzone.service.MeetingApiService;
import com.example.mareulamzone.ui.adapters.MyMeetingsRecyclerViewAdapter;
import com.example.mareulamzone.ui.dialog.FilterByRoomDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MeetingsFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private MeetingApiService mApiService = DI.getMeetingApiService();
    private List<Meeting> meetings = new ArrayList<>();
    private int filterActive = 0;
    private FilterByRoomDialog fbr = new FilterByRoomDialog();
    RecyclerView recyclerView;


    public MeetingsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_meetings_list, container, false);
        recyclerView = view.findViewById(R.id.list);
        MeetingApiService mMeetingApiService = DI.getMeetingApiService();
        ArrayList listTest = new ArrayList<String>();
        listTest.add("jean");
        listTest.add("random");

//        mMeetingApiService.createMeeting(mMeetingApiService.getMeetings().size() + 1,
//                new Date(Calendar.getInstance().getTimeInMillis()),
//                mMeetingApiService.getMeetingRooms().get(1),
//                "sujet test",
//                listTest, "30 minutes");

        initFilter(0);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set the adapter

        List<Meeting> mMeetings = mApiService.getMeetings();

        System.out.println("////////////////MEETINGS///////////////" + mMeetings);

        Context context = this.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        MyMeetingsRecyclerViewAdapter mAdapter = new MyMeetingsRecyclerViewAdapter(context, mMeetings);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_filter, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        switch (item.getItemId()){
            case R.id.filter_by_date:
                filterActive = 1;
                filterByDateDialog();
                break;
            case R.id.filter_by_room:
                filterActive = 2;
                fbr.show(fm, "Filter by Room");
                break;
            case R.id.reinitialize_filter:
                filterActive = 0;
                initFilter(filterActive);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void filterByDateDialog() {
        Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year1, int month1, int dayOfMonth1) {
                meetings = mApiService.filterMeetingDate(year1, month1, dayOfMonth1);
                filterActive = 1;
                initFilter(filterActive);
            }}, year, month, day);
        datePickerDialog.show();
    }

    private void initFilter(int filterActive) {
        if(filterActive == 2) {
            meetings = mApiService.filterMeetingRoomIdList(fbr.selectedItems);
            MyMeetingsRecyclerViewAdapter mAdapter2 = new MyMeetingsRecyclerViewAdapter(getContext(), meetings);
            recyclerView.setAdapter(mAdapter2);
        }else if(filterActive == 0){
            MyMeetingsRecyclerViewAdapter mAdapter = new MyMeetingsRecyclerViewAdapter(getContext(), mApiService.getMeetings());
            recyclerView.setAdapter(mAdapter);
        }else if(filterActive == 1){
            MyMeetingsRecyclerViewAdapter mAdapter1 = new MyMeetingsRecyclerViewAdapter(getContext(), meetings);
            recyclerView.setAdapter(mAdapter1);
        }
    }

}