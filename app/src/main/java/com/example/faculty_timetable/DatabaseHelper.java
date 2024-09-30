package com.example.faculty_timetable;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
                "teacher_id TEXT REFERENCES Teachers(teacher_id)," +
                "timeslot_id INTEGER REFERENCES TimeSlots(timeslot_id)," +
                "day_id INTEGER REFERENCES DaysOfWeek(day_id)," +
                "subject TEXT," +
                "room TEXT," +
                "semester INTEGER," +
                "division TEXT)";

        String CREATE_TEACHERS_TABLE = "CREATE TABLE IF NOT EXISTS Teachers (" +
                "teacher_id TEXT PRIMARY KEY ," +
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
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }

    public long insertFaculty(Teacher teacher){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues faculty = new ContentValues();
        faculty.put("teacher_id",teacher.getTeacherId());
        faculty.put("name",teacher.getName());
        faculty.put("department",teacher.getDepartment());
        faculty.put("role",teacher.getRole());
        faculty.put("qualifications",teacher.getQualifications());
        faculty.put("experience",teacher.getExperience());
        faculty.put("teaching_load",teacher.getTeachingLoad());
        long newRowId = db.insert("Teachers",null,faculty);
        db.close();
        return newRowId;
    }

    public long insertTimeslots(TimeSlot timeslot){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues slots = new ContentValues();
        slots.put("timeslot_Id",timeslot.getTimeSlotId());
        slots.put("start_time",timeslot.getStartTime());
        slots.put("end_time",timeslot.getEndTime());
        long newRowId = db.insert("TimeSlots",null,slots);
        db.close();
        return newRowId;
    }

    public long insertDay(DayOfWeek dow){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues days = new ContentValues();
        days.put("day_id",dow.getDayId());
        days.put("day_name",dow.getDayName());
        long newRowId = db.insert("DaysOfWeek",null,days);
        db.close();
        return newRowId;
    }

    public long insertTimetableEntry(TimetableEntry entry) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues faculty_values = new ContentValues();
        faculty_values.put("entry_id",entry.getEntryId());
        faculty_values.put("teacher_id", entry.getTeacherId());
        faculty_values.put("timeslot_id",entry.getTimeSlotId());
        faculty_values.put("day_id",entry.getDayOfWeekId());
        faculty_values.put("subject",entry.getSubject());
        faculty_values.put("room",entry.getRoom());
        faculty_values.put("semester",entry.getSemester());
        faculty_values.put("division",entry.getDivision());
        long newRowId = db.insert("TimetableEntries", null, faculty_values);

        db.close();

        return newRowId;
    }
}