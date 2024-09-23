package com.example.faculty_timetable;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "timetable.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TIMETABLE_ENTRIES_TABLE = "CREATE TABLE IF NOT EXISTS TimetableEntries (" +
                "entry_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "teacher_id INTEGER REFERENCES Teachers(teacher_id)," +
                "timeslot_id INTEGER REFERENCES TimeSlots(timeslot_id)," +
                "day_id INTEGER REFERENCES DaysOfWeek(day_id)," +
                "subject TEXT," +
                "room TEXT," +
                "semester INTEGER," +
                "division TEXT)";

        String CREATE_TEACHERS_TABLE = "CREATE TABLE IF NOT EXISTS Teachers (" +
                "teacher_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "department TEXT," +
                "role TEXT," +
                "qualifications TEXT," +
                "experience INTEGER," +
                "teaching_load INTEGER)";

        String CREATE_TIMESLOTS_TABLE = "CREATE TABLE IF NOT EXISTS TimeSlots (" +
                "timeslot_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "start_time TEXT," +
                "end_time TEXT)";

        String CREATE_DAYSOFWEEK_TABLE = "CREATE TABLE IF NOT EXISTS DaysOfWeek (" +
                "day_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "day_name TEXT)";

        db.execSQL(CREATE_TIMETABLE_ENTRIES_TABLE);
        db.execSQL(CREATE_TEACHERS_TABLE);
        db.execSQL(CREATE_TIMESLOTS_TABLE);
        db.execSQL(CREATE_DAYSOFWEEK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database schema changes if necessary
    }

    public long insertTimetableEntry(TimetableEntry entry) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues faculty_values = new ContentValues();
        faculty_values.put("teacher_id", entry.getTeacher().getTeacherId());
        faculty_values.put("name",entry.getTeacher().getName());
        faculty_values.put("department",entry.getTeacher().getDepartment());
        faculty_values.put("role",entry.getTeacher().getRole());
        faculty_values.put("qualifications",entry.getTeacher().getQualifications());
        faculty_values.put("experience",entry.getTeacher().getExperience());
        faculty_values.put("teaching_load",entry.getTeacher().getTeachingLoad());
        long newRowId = db.insert("Teachers", null, faculty_values);

        ContentValues timeslot_values = new ContentValues();
        timeslot_values.put("timeslot_id", entry.getTimeSlot().getTimeSlotId());
        timeslot_values.put("start_time", entry.getTimeSlot().getTimeSlotId());
        timeslot_values.put("end_time", entry.getTimeSlot().getTimeSlotId());
        newRowId = db.insert("TimeSlots", null, timeslot_values);


        ContentValues days_values = new ContentValues();
        days_values.put("day_id",entry.getDayOfWeek().getDayId());
        days_values.put("day_name",entry.getDayOfWeek().getDayName());
        newRowId = db.insert("DaysOfWeek", null, days_values);

        ContentValues values = new ContentValues();
        values.put("entry_id",entry.getEntryId());
        values.put("teacher_id", entry.getTeacher().getTeacherId());
        values.put("timeslot_id", entry.getTimeSlot().getTimeSlotId());
        values.put("day_id", entry.getDayOfWeek().getDayId());
        values.put("subject", entry.getSubject());
        values.put("room", entry.getRoom());
        values.put("semester", entry.getSemester());
        values.put("division", entry.getDivision());
        newRowId = db.insert("TimetableEntries", null, values);
        db.close();

        return newRowId;
    }
}