package com.example.mareulamzone;

import static com.example.mareulamzone.service.LamzoneGenerator.USERS;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.mareulamzone.di.DI;
import com.example.mareulamzone.model.Meeting;
import com.example.mareulamzone.model.MeetingRoom;
import com.example.mareulamzone.model.User;
import com.example.mareulamzone.service.LamzoneGenerator;
import com.example.mareulamzone.service.MeetingApiService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MareulamzoneUnitTest {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();

        Date testDate = new Date(System.currentTimeMillis());

        List<String> list = new ArrayList<>();
        for (int i = 0; i < USERS.size(); i++) {
            list.add(USERS.get(i).getEmail());
        }

        service.createMeeting(service.getMeetings().size() + 1,
                testDate,
                service.getMeetingRooms().get(2),
                "Test meeting" + service.getMeetings().size(),
                list,
                "30 minutes");

        service.createMeeting(service.getMeetings().size() + 1,
                testDate,
                service.getMeetingRooms().get(3),
                "Test meeting2" + service.getMeetings().size(),
                list,
                "30 minutes");
    }

    @Test
    public void getMeetingsWithSuccess() {

        List<Meeting> meetings = service.getMeetings();
        assertEquals(2, meetings.size());
    }

    @Test
    public void getMeetingRoomsWithSuccess() {

        List<MeetingRoom> rooms = service.getMeetingRooms();
        List<MeetingRoom> expectedRooms = LamzoneGenerator.MEETINGROOMS;
        assertTrue(expectedRooms.containsAll(rooms));
    }

    @Test
    public void getUsers() {
        List<User> users = service.getUsers();
        List<User> expectedUsers = LamzoneGenerator.USERS;
        assertTrue(expectedUsers.containsAll(users));
    }

    @Test
    public void removeMeeting() {
        List<Meeting> meetings = service.getMeetings();
        Meeting meeting = service.getMeetings().get(0);
        assertTrue(meetings.contains(meeting));
        service.removeMeeting(meeting);
        assertFalse(meetings.contains(meeting));
    }

    @Test
    public void getMeetingWithSuccess() {
        Meeting meeting = service.getMeetings().get(0);
        Meeting meeting1 = service.getMeeting(meeting.getId());
        assertEquals(meeting, meeting1);
    }

    @Test
    public void createMeeting() {

        Date testDate3 = new Date(System.currentTimeMillis());
        List<String> list3 = new ArrayList<>();
        for (int i = 0; i < USERS.size(); i++) {
            list3.add(USERS.get(i).getEmail());
        }

        service.createMeeting(22,
                testDate3,
                service.getMeetingRooms().get(3),
                "Test meeting2" + service.getMeetings().size(),
                list3,
                "30 minutes");

        assertTrue(service.getMeetings().contains(service.getMeeting(22)));
    }

    @Test
    public void filterMeetingRoomIdWithSuccess() {

        List <Integer> roomList = Collections.singletonList(service.getMeetingRooms().get(0).getId());
        List<Meeting> meetings = service.filterMeetingRoomIdList(roomList);
        for(Meeting meeting : meetings){
            for (MeetingRoom room : service.getMeetingRooms()){
                if (room == service.getMeetingRooms().get(0)){
                    assertEquals(meeting.getRoom(), room);
                }else{
                    assertNotEquals(meeting.getRoom(), room);
                }
            }
        }
    }

    @Test
    public void filterMeetingDateWithSuccess() {
        int year = 1970, month = 0, dayOfMonth = 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth, 0,0);
        List<String> list3 = new ArrayList<>();
        for (int i = 0; i < USERS.size(); i++) {
            list3.add(USERS.get(i).getEmail());
        }

        service.createMeeting(23,
                calendar.getTime(),
                service.getMeetingRooms().get(3),
                "Test meeting3" + service.getMeetings().size(),
                list3,
                "30 minutes");

        List<Meeting> meetings = service.filterMeetingDate(year, month, dayOfMonth);
        assertTrue(meetings.contains(service.getMeeting(23)));
    }
}