package com.example.mareulamzone.service;

import com.example.mareulamzone.model.Meeting;
import com.example.mareulamzone.model.MeetingRoom;
import com.example.mareulamzone.model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = LamzoneGenerator.generateMeetings();
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
        for(int i = 0; i < meetings.size(); i++){
            if(meetings.get(i).getId() == id){
                return meetings.get(i);
            }
        }
        return null;
    }

    public List<String> getAllEmails() {
        ArrayList<String> emails = new ArrayList<>();
        for(int i = 0; i < users.size(); i++){
            emails.add(users.get(i).getEmail());
        }
        return emails;
    }

    public User getUser(String email){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getEmail().equals(email)){
                return users.get(i);
            }
        }
        return null;
    }

    @Override
    public void createMeeting(int id, String name, Date date, MeetingRoom room, String subject, ArrayList<User> users, int duration) {

        Meeting meeting = new Meeting(id, name, date, duration, room, subject, users);
        meetings.add(meeting);
    }
}
