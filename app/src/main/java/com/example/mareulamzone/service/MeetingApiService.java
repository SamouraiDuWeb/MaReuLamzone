package com.example.mareulamzone.service;

import com.example.mareulamzone.model.Meeting;
import com.example.mareulamzone.model.MeetingRoom;
import com.example.mareulamzone.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeetings();

    List<MeetingRoom> getMeetingRooms();

    List<User> getUsers();

    void removeMeeting(Meeting meeting);

    Meeting getMeeting(int id);

    User getUser(String email);

    List<String> getAllEmails();

    void createMeeting(int id, String name, Date date, MeetingRoom room, String subject, ArrayList<User> users, int duration);
}