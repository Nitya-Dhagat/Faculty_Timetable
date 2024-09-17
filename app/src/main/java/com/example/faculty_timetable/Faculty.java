package com.example.faculty_timetable;

import android.text.format.Time;

import java.util.List;

public class Faculty {
    String subjects_taken;
    String classroom_numbers;
    String semesters;
    Time class_time;

    public String getSubjects_taken() {
        return subjects_taken;
    }

    public String getClassroom_numbers() {
        return classroom_numbers;
    }

    public String getSemesters() {
        return semesters;
    }

    public Time getClass_time() {
        return class_time;
    }
}
