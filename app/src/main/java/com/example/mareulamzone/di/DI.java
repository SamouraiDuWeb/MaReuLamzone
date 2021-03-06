package com.example.mareulamzone.di;

import com.example.mareulamzone.service.DummyMeetingApiService;
import com.example.mareulamzone.service.MeetingApiService;

public class DI {

    private static MeetingApiService service = new DummyMeetingApiService();

    public static MeetingApiService getMeetingApiService() {
        return service;
    }

    public static MeetingApiService getNewInstanceApiService() {
        return new DummyMeetingApiService();
    }
}
