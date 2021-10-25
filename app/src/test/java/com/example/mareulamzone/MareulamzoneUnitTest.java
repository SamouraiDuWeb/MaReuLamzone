package com.example.mareulamzone;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.mareulamzone.di.DI;
import com.example.mareulamzone.model.MeetingRoom;
import com.example.mareulamzone.service.LamzoneGenerator;
import com.example.mareulamzone.service.MeetingApiService;

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
    }

    @Test
    public void getMeetingRoomsWithSuccess() {
        List<MeetingRoom> rooms = service.getMeetingRooms();
        List<MeetingRoom> expectedRooms = LamzoneGenerator.MEETINGROOMS;
        assertTrue(expectedRooms.containsAll(rooms));

    }
}