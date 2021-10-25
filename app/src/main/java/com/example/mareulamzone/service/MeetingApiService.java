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

    void createMeeting(int id, Date date, MeetingRoom room, String subject, List<String> users, String duration);

    List<Meeting> filterMeetingRoomIdList(List<Integer> ids);

    List<Meeting> filterMeetingRoomId(long id);

    List<Meeting> filterMeetingDate(int year, int month, int day);
}