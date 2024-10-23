package com.example.faculty_timetable.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.faculty_timetable.DatabaseHelper;
import com.example.faculty_timetable.R;
import com.example.faculty_timetable.TimetableEntry;
import com.example.faculty_timetable.TimetableEntryAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class TuesdayFragment extends Fragment {

    private DatabaseHelper dbHelper;
    private String selectedFacultyId;
    private SharedPreferences sharedprefs;
    private TimetableEntryAdapter adapter;
    private RecyclerView facultyScheduleRecyclerView;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    private String teacher_id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for getContext() fragment
        View view = inflater.inflate(R.layout.fragment_tuesday, container, false);
        dbHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        sharedprefs = requireActivity().getPreferences(Context.MODE_PRIVATE);
        facultyScheduleRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_fragment_tuesday);
        adapter = new TimetableEntryAdapter(getContext());
        facultyScheduleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        facultyScheduleRecyclerView.setAdapter(adapter);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        selectedFacultyId = sharedprefs.getString("username",mUser.getEmail());

        String selection = "teacher_id = ? AND day_id = ?";
        String[] selectionArgs = {String.valueOf(selectedFacultyId),String.valueOf(2)};

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
        return view;
    }
}