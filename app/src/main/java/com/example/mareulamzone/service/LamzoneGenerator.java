package com.example.mareulamzone.service;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mareulamzone.R;
import com.example.mareulamzone.model.Meeting;
import com.example.mareulamzone.model.MeetingRoom;
import com.example.mareulamzone.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class LamzoneGenerator {

    public static List<MeetingRoom> MEETINGROOMS = Arrays.asList(
            new MeetingRoom(1, "Salle 01", R.color.salle_01),
            new MeetingRoom(2, "Salle 02", R.color.salle_02),
            new MeetingRoom(3, "Salle 03", R.color.salle_03),
            new MeetingRoom(4, "Salle 04", R.color.salle_04),
            new MeetingRoom(5, "Salle 05", R.color.salle_05),
            new MeetingRoom(6, "Salle 06", R.color.salle_06),
            new MeetingRoom(7, "Salle 07", R.color.salle_07),
            new MeetingRoom(8, "Salle 08", R.color.salle_08),
            new MeetingRoom(9, "Salle 09", R.color.salle_09),
            new MeetingRoom(10, "Salle 10", R.color.salle_10)
    );

    public static List<User> USERS = Arrays.asList(
            new User(1, "Zorita Keith", "congue.a@pedemalesuadavel.org"),
            new User (2, "Oren Stanton", "semper.auctor@posuereenimnisl.edu"),
            new User (3, "Lucius Knight", "lectus@fermentumrisusat.edu"),
            new User (4, "Orlando Baldwin", "arcu.vestibulum@ametconsectetuer.net"),
            new User (5, "Josiah Lambert", "curabitur.vel@dolorfusce.co.uk"),
            new User (6, "Sebastian Henry", "consectetuer.euismod@donec.org"),
            new User (7, "Maite Patton", "nibh.phasellus.nulla@orciadipiscingnon.edu"),
            new User (8, "September Hays", "velit.egestas@molestietellus.co.uk"),
            new User (9, "Rana Massey", "fusce@scelerisque.co.uk"),
            new User (10, "Price Steele", "mattis.semper@mollisnon.ca")
    );

    public static List<User> MEETINGS = Arrays.asList();

    static List<Meeting> generateMeetings() {
        return new ArrayList<>();
    }

    static List<MeetingRoom> generateMeetingRooms() {
        return new ArrayList<>(MEETINGROOMS);
    }

    static List<User> generateUsers() { return new ArrayList<>(USERS); }
}
