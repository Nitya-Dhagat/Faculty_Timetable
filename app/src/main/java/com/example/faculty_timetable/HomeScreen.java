package com.example.faculty_timetable;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
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
    private TabLayout daysofweektabs;
    private TextView welcome_message;
    private String teacher_id;
    private ViewPagerAdapter pager_adapter;
    private ViewPager2 viewpager2;

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
        daysofweektabs = findViewById(R.id.tabLayout_homeScreen_tabs);
        welcome_message = findViewById(R.id.textView_HomeScreen_welcome);
        pager_adapter = new ViewPagerAdapter(this);
        viewpager2 = findViewById(R.id.viewpager_homeScreen);

        // Assuming you have a way to determine the selected faculty ID
        //selectedFacultyId = "vipuldabhi.it@ddu.ac.in"; // Replace with actual faculty ID
        selectedFacultyId = sharedprefs.getString("username",mUser.getEmail());

        loadEntries.loadTimetableEntries(dbHelper);
        //showFacultySchedule(selectedFacultyId);
        dbHelper = new DatabaseHelper(this);
        String teacher_id=selectedFacultyId;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor1 = db.query("Teachers",null,"teacher_id=?",new String[]{String.valueOf(teacher_id)},null,null,null,null);
        cursor1.moveToNext();
        String name = cursor1.getString(cursor1.getColumnIndexOrThrow("name"));
        welcome_message.setText("Welcome,\n"+name);
        Cursor cursor2 = db.query("DaysOfWeek",null,null,null,null,null,null,null);
        while (cursor2.moveToNext()){
            daysofweektabs.addTab(daysofweektabs.newTab().setText(cursor2.getString(cursor2.getColumnIndexOrThrow("day_name"))));
        }

        viewpager2.setAdapter(pager_adapter);
        daysofweektabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                daysofweektabs.getTabAt(position).select();
            }
        });

        signout = (Button) findViewById(R.id.button);
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

        String selection = "teacher_id = ? & day_id = ?";
        String[] selectionArgs = {String.valueOf(facultyId)};

        Cursor cursor = db.query("TimetableEntries", null, selection, selectionArgs, null, null, null);

        List<TimetableEntry> timetableEntries = new ArrayList<>();
        while (cursor.moveToNext()) {
            TimetableEntry entry = new TimetableEntry();
            // Retrieve teacher, time slot, and day of week from their respective tables
            teacher_id = cursor.getString(cursor.getColumnIndexOrThrow("teacher_id"));
            entry.setTeacherId(cursor.getString(cursor.getColumnIndexOrThrow("teacher_id")));
            entry.setTimeSlotId(cursor.getInt(cursor.getColumnIndexOrThrow("timeslot_id")));
            entry.setDayOfWeekId(cursor.getInt(cursor.getColumnIndexOrThrow("day_id")));
            entry.setEntryId(cursor.getInt(cursor.getColumnIndexOrThrow("entry_id")));
            entry.setSubject(cursor.getString(cursor.getColumnIndexOrThrow("subject")));
            entry.setRoom(cursor.getString(cursor.getColumnIndexOrThrow("room")));
            entry.setSemester(cursor.getInt(cursor.getColumnIndexOrThrow("semester")));
            entry.setDivision(cursor.getString(cursor.getColumnIndexOrThrow("division")));
            timetableEntries.add(entry);
        }

        cursor.close();
        adapter.setData(timetableEntries);
    }
}