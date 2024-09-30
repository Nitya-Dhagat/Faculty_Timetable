package com.example.faculty_timetable;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class retrieveDetails {
    public  Teacher retrieveTeacher(Context context, int teacherId) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = "teacher_id = ?";
        String[] selectionArgs = {String.valueOf(teacherId)};

        Cursor cursor = db.query("Teachers", null, selection, selectionArgs, null, null, null);

        Teacher teacher = null;
        while (cursor.moveToNext()) {
            teacher = new Teacher();
            teacher.setTeacherId(cursor.getString(cursor.getColumnIndexOrThrow("teacher_id")));
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

    public static String retrieveTimeSlot(Context context,int timeSlotId) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = "timeslot_id = ?";
        String[] selectionArgs = {String.valueOf(timeSlotId)};
        String time="";

        Cursor cursor = db.query("TimeSlots", null, selection, selectionArgs, null, null, null);

        TimeSlot timeSlot = null;
        while (cursor.moveToNext()) {
            timeSlot = new TimeSlot();
//            timeSlot.setTimeSlotId(cursor.getInt(cursor.getColumnIndexOrThrow("timeslot_id")));
//            timeSlot.setStartTime(cursor.getString(cursor.getColumnIndexOrThrow("start_time")));
//            timeSlot.setEndTime(cursor.getString(cursor.getColumnIndexOrThrow("end_time")));
            time = timeSlot.getStartTime()+"-" + timeSlot.getEndTime();
        }
        Log.e("timeslot",time);
        cursor.close();
        db.close();

        return time;
    }

    public static DayOfWeek retrieveDayOfWeek(Context context,int dayId) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
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
