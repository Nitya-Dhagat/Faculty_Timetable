package com.example.faculty_timetable;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity {

    Button signout;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    RecyclerView recyclerView;
    private RecyclerView facultyScheduleRecyclerView;
    private TimetableEntryAdapter adapter;
    private DatabaseHelper dbHelper;
    private String selectedFacultyId;
    private SharedPreferences sharedprefs;

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
        sharedprefs = getPreferences(Context.MODE_PRIVATE);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        // Assuming you have a way to determine the selected faculty ID
        //selectedFacultyId = "vipuldabhi.it@ddu.ac.in"; // Replace with actual faculty ID
        selectedFacultyId = sharedprefs.getString("username",mUser.getEmail());

        loadEntries.loadTimetableEntries(dbHelper);
        showFacultySchedule(selectedFacultyId);

        signout = (Button) findViewById(R.id.button);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                SharedPreferences.Editor editor = sharedprefs.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(HomeScreen.this,LoginScreen.class));
                finish();
            }
        });

    }

    private void showFacultySchedule(String facultyId) {
        dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = "teacher_id = ?";
        String[] selectionArgs = {String.valueOf(facultyId)};

        Cursor cursor = db.query("TimetableEntries", null, selection, selectionArgs, null, null, null);

        List<TimetableEntry> timetableEntries = new ArrayList<>();
        while (cursor.moveToNext()) {
            TimetableEntry entry = new TimetableEntry();
            // Retrieve teacher, time slot, and day of week from their respective tables
            entry.setTeacherId(cursor.getString(cursor.getColumnIndexOrThrow("teacher_id")));
            entry.setTimeSlotId(cursor.getInt(cursor.getColumnIndexOrThrow("timeslot_id")));
            entry.setDayOfWeekId(cursor.getInt(cursor.getColumnIndexOrThrow("day_id")));
//            entry.setTeacher(retrieveTeacher(cursor.getInt(cursor.getColumnIndexOrThrow("teacher_id"))));
//            entry.setTimeSlot(retrieveTimeSlot(cursor.getInt(cursor.getColumnIndexOrThrow("timeslot_id"))));
//            entry.setDayOfWeekId(retrieveDayOfWeek(cursor.getInt(cursor.getColumnIndexOrThrow("day_id"))));
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



}