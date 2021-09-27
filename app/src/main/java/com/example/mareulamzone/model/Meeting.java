package com.example.mareulamzone.model;

import java.util.Date;
import java.util.List;

public class Meeting {

    private int id;
    private String name;
    private Date date;
    private int duration;
    private MeetingRoom room;
    private String subject;
    private List<User> users;

    public Meeting(int id, String name, Date date, int duration, MeetingRoom room, String subject, List<User> users) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date dateStart) {
        this.date = dateStart;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
