package com.example.mareulamzone.service;

import com.example.mareulamzone.model.Meeting;
import com.example.mareulamzone.model.MeetingRoom;
import com.example.mareulamzone.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = new ArrayList<>();
    private List<MeetingRoom> rooms = LamzoneGenerator.generateMeetingRooms();
    private List<User> users = LamzoneGenerator.generateUsers();

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public List<MeetingRoom> getMeetingRooms() {
        return rooms;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void removeMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    public Meeting getMeeting(int id) {
        for (int i = 0; i < meetings.size(); i++) {
            if (meetings.get(i).getId() == id) {
                return meetings.get(i);
            }
        }
        return null;
    }

    @Override
    public void createMeeting(int id, Date date, MeetingRoom room, String subject, List<String> users, String duration) {

        Meeting meeting = new Meeting(id, date, duration, room, subject, users);
        meetings.add(meeting);
    }

    public List<Meeting> filterMeetingRoomIdList(List<Integer> ids) {
        ArrayList<Meeting> meetingWithFilterRooms = new ArrayList<>();
        for (Meeting meeting : meetings) {
            for (int id : ids) {
                if (meeting.getRoom().getId() == id)
                    meetingWithFilterRooms.add(meeting);
            }
        }
        return meetingWithFilterRooms;
    }

    public List<Meeting> filterMeetingDate(int year, int month, int day){
        ArrayList<Meeting> meetingWithFilterDate = new ArrayList<>();
        int year2, month2, day2;
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        for(int i = 0; i < meetings.size(); i++){
            cal.setTime(meetings.get(i).getDate());
            year2 = cal.get(Calendar.YEAR);
            month2 = cal.get(Calendar.MONTH);
            day2 = cal.get(Calendar.DAY_OF_MONTH);
            if(year == year2 && month == month2 && day == day2){
                meetingWithFilterDate.add(meetings.get(i));

            }
        }
        return meetingWithFilterDate;
    }
}
