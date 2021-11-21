package com.example.mareulamzone.model;

import java.util.Date;
import java.util.List;

public class Meeting {

    private int id;
    private Date date;
    private String duration;
    private MeetingRoom room;
    private String subject;
    private List<String> users;

    public Meeting(int id, Date date, String duration, MeetingRoom room, String subject, List<String> users) {
        this.id = id;
        this.date = date;
        this.duration = duration;
        this.room = room;
        this.subject = subject;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date dateStart) {
        this.date = dateStart;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public MeetingRoom getRoom() {
        return room;
    }

    public void setRoom(MeetingRoom room) {
        this.room = room;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
