package com.example.faculty_timetable;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity {

    Button signout;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    RecyclerView recyclerView;
    DatabaseReference database;
    private RecyclerView facultyScheduleRecyclerView;
    private TimetableEntryAdapter adapter;
    private DatabaseHelper dbHelper;
    private int selectedFacultyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_screen);

        //foxandroid firebsae to recycler view

        dbHelper = new DatabaseHelper(this);
        facultyScheduleRecyclerView = findViewById(R.id.recycler_homeScreen);
        adapter = new TimetableEntryAdapter(this);
        facultyScheduleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        facultyScheduleRecyclerView.setAdapter(adapter);

        // Assuming you have a way to determine the selected faculty ID
        selectedFacultyId = 1; // Replace with actual faculty ID

       Teacher teacher = new Teacher();
        teacher.setTeacherId(1);
        teacher.setName("John Doe");
        teacher.setDepartment("Information Technology");
        teacher.setExperience(5);
        teacher.setQualifications("Phd in Information Technology");
        teacher.setRole("HOD");
        teacher.setTeachingLoad(5);

// Create a TimeSlot object
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setTimeSlotId(2);
        timeSlot.setStartTime("10:00");
        timeSlot.setEndTime("11:00");

// Create a DayOfWeek object
        DayOfWeek dayOfWeek = new DayOfWeek();
        dayOfWeek.setDayId(1);
        dayOfWeek.setDayName("Monday");

// Create a TimetableEntry object
        TimetableEntry entry = new TimetableEntry();
        entry.setTeacher(teacher);
        entry.setTimeSlot(timeSlot);
        entry.setDayOfWeek(dayOfWeek);
        entry.setSubject("Math");
        entry.setRoom("A101");
        entry.setSemester(3);
        entry.setDivision("A");

// Insert the entry into the database
        dbHelper.insertTimetableEntry(entry);
        showFacultySchedule(1);

        signout = (Button) findViewById(R.id.button);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth = FirebaseAuth.getInstance();
                mUser = mAuth.getCurrentUser();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeScreen.this,LoginScreen.class));
                finish();
            }
        });

    }

    private void showFacultySchedule(int facultyId) {
        dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = "teacher_id = ?";
        String[] selectionArgs = {String.valueOf(facultyId)};

        Cursor cursor = db.query("TimetableEntries", null, selection, selectionArgs, null, null, null);

        List<TimetableEntry> timetableEntries = new ArrayList<>();
        while (cursor.moveToNext()) {
            TimetableEntry entry = new TimetableEntry();
            // Retrieve teacher, time slot, and day of week from their respective tables
            entry.setTeacher(retrieveTeacher(cursor.getInt(cursor.getColumnIndexOrThrow("teacher_id"))));
            entry.setTimeSlot(retrieveTimeSlot(cursor.getInt(cursor.getColumnIndexOrThrow("timeslot_id"))));
            entry.setDayOfWeek(retrieveDayOfWeek(cursor.getInt(cursor.getColumnIndexOrThrow("day_id"))));

            entry.setEntryId(cursor.getInt(cursor.getColumnIndexOrThrow("entry_id")));
            entry.setSubject(cursor.getString(cursor.getColumnIndexOrThrow("subject")));
            entry.setRoom(cursor.getString(cursor.getColumnIndexOrThrow("room")));
            entry.setSemester(cursor.getInt(cursor.getColumnIndexOrThrow("semester")));
            entry.setDivision(cursor.getString(cursor.getColumnIndexOrThrow("division")));

            timetableEntries.add(entry);
        }

        cursor.close();
        db.close();

        adapter.setData(timetableEntries);
    }

    private Teacher retrieveTeacher(int teacherId) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = "teacher_id = ?";
        String[] selectionArgs = {String.valueOf(teacherId)};

        Cursor cursor = db.query("Teachers", null, selection, selectionArgs, null, null, null);

        Teacher teacher = null;
        while (cursor.moveToNext()) {
            teacher = new Teacher();
            teacher.setTeacherId(cursor.getInt(cursor.getColumnIndexOrThrow("teacher_id")));
            teacher.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            teacher.setDepartment(cursor.getString(cursor.getColumnIndexOrThrow("department")));
            teacher.setRole(cursor.getString(cursor.getColumnIndexOrThrow("role")));
            teacher.setQualifications(cursor.getString(cursor.getColumnIndexOrThrow("qualifications")));
            teacher.setExperience(cursor.getInt(cursor.getColumnIndexOrThrow("experience")));
            teacher.setTeachingLoad(cursor.getInt(cursor.getColumnIndexOrThrow("teaching_load")));
        }

        cursor.close();
        db.close();

        return teacher;
    }

    private TimeSlot retrieveTimeSlot(int timeSlotId) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = "timeslot_id = ?";
        String[] selectionArgs = {String.valueOf(timeSlotId)};

        Cursor cursor = db.query("TimeSlots", null, selection, selectionArgs, null, null, null);

        TimeSlot timeSlot = null;
        while (cursor.moveToNext()) {
            timeSlot = new TimeSlot();
            timeSlot.setTimeSlotId(cursor.getInt(cursor.getColumnIndexOrThrow("timeslot_id")));
            timeSlot.setStartTime(cursor.getString(cursor.getColumnIndexOrThrow("start_time")));
            timeSlot.setEndTime(cursor.getString(cursor.getColumnIndexOrThrow("end_time")));
        }

        cursor.close();
        db.close();

        return timeSlot;
    }

    private DayOfWeek retrieveDayOfWeek(int dayId) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = "day_id = ?";
        String[] selectionArgs = {String.valueOf(dayId)};

        Cursor cursor = db.query("DaysOfWeek", null, selection, selectionArgs, null, null, null);

        DayOfWeek dayOfWeek = null;
        while (cursor.moveToNext()) {
            dayOfWeek = new DayOfWeek();
            dayOfWeek.setDayId(cursor.getInt(cursor.getColumnIndexOrThrow("day_id")));
            dayOfWeek.setDayName(cursor.getString(cursor.getColumnIndexOrThrow("day_name")));
        }

        cursor.close();
        db.close();

        return dayOfWeek;
    }
}