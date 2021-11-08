package com.example.mareulamzone;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static com.example.mareulamzone.service.LamzoneGenerator.USERS;
import static com.example.mareulamzone.utils.RecyclerViewItemCountAssertion.withItemCount;

import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

import com.example.mareulamzone.di.DI;
import com.example.mareulamzone.model.Meeting;
import com.example.mareulamzone.service.MeetingApiService;
import com.example.mareulamzone.ui.Activities.MeetingsActivity;
import com.example.mareulamzone.utils.DeleteViewAction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private MeetingApiService mApiService;

    @Before
    public void setUp() {

        mApiService = DI.getMeetingApiService();
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.mareulamzone", appContext.getPackageName());
    }

    @Test
    public void mMeetingList_shouldBeEmpty() {
        onView(withId(R.id.list))
                .check(matches(hasMinimumChildCount(0)));
    }

    @Test
    public void myMeetingList_deleteAction_shouldRemoveItem() {
        Date testDate = new Date(System.currentTimeMillis());

        List<String> list = new ArrayList<>();
        for (int i = 0; i < USERS.size(); i++) {
            list.add(USERS.get(i).getEmail());
        }

        mApiService.createMeeting(mApiService.getMeetings().size() + 1,
                testDate,
                mApiService.getMeetingRooms().get(3),
                "Test meeting" + mApiService.getMeetings().size(),
                list,
                "30 minutes");

        int size = mApiService.getMeetings().size();
        onView(withId(R.id.list)).check(withItemCount(size));
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        onView(withId(R.id.list)).check(withItemCount(size - 1));
    }

    @Test
    public void addNewMeetingWithSuccess() {
        int size = mApiService.getMeetings().size();
        onView(withId(R.id.fbtn_add_meeting)).perform(click());
        onView(withId(R.id.sp_meeting_room)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.et_subject_meeting)).perform(click()).perform(typeText("TestSubject"));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(scrollTo(), PickerActions.setDate(2021,11, 18));
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(scrollTo(), PickerActions.setTime(12, 50));
        onView(withId(R.id.sp_meeting_duration)).perform(scrollTo(), click());
        onData(anything()).atPosition(2).perform(click());
        onView(withId(R.id.sp_add_user_to_meeting)).perform(scrollTo(), click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.btn_add_user)).perform(scrollTo(), click());
        onView(withId(R.id.sp_add_user_to_meeting)).perform(scrollTo(), click());
        onData(anything()).atPosition(2).perform(click());
        onView(withId(R.id.btn_add_user)).perform(scrollTo(), click());
        onView(withId(R.id.btn_add_meeting)).perform(scrollTo(), click());
        onView(withId(R.id.container)).check(withItemCount(size + 1));
    }

    @Test
    public void filterRoomWithSuccess() {
        Date testDate = new Date(System.currentTimeMillis());

        List<String> list = new ArrayList<>();
        for (int i = 0; i < USERS.size(); i++) {
            list.add(USERS.get(i).getEmail());
        }

        mApiService.createMeeting(mApiService.getMeetings().size() + 1,
                testDate,
                mApiService.getMeetingRooms().get(2),
                "Test meeting" + mApiService.getMeetings().size(),
                list,
                "30 minutes");

        onView(withId(R.id.filter_meeting)).perform(click());
        onView(ViewMatchers.withText("Par Salle")).perform(click());
        onView(withText("Salle 02")).check(matches(isNotChecked())).perform(click());
        onView(withText("OK")).perform(click());
        onView(withId(R.id.list)).check(withItemCount(1));
    }

    @Test
    public void filterDateWithSuccess() {
        int year = 1970, month = 0, dayOfMonth = 19;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth, 0,0);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < USERS.size(); i++) {
            list.add(USERS.get(i).getEmail());
        }

        mApiService.createMeeting(mApiService.getMeetings().size() + 1,
                calendar.getTime(),
                mApiService.getMeetingRooms().get(2),
                "Test meeting" + mApiService.getMeetings().size(),
                list,
                "30 minutes");

        onView(withId(R.id.filter_meeting)).perform(click());
        onView(ViewMatchers.withText("Par Date")).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1970,1, 19));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.container)).check(withItemCount(1));
    }

    @Test
    public void detailsActivityLaunchedWithSuccess() {
        onView(withId(R.id.container)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.tv_detail_meeting_subject)).check(matches(isDisplayed()));
    }

}