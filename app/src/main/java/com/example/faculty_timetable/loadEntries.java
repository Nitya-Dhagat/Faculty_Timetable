package com.example.faculty_timetable;

import androidx.annotation.NonNull;

public class loadEntries {

    public static void loadTimetableEntries(@NonNull DatabaseHelper dbHelper) {
        loadVD(dbHelper);
    }

    public static void loadVD(DatabaseHelper dbHelper){
        Teacher teacher = new Teacher();
        DayOfWeek dayOfWeek = new DayOfWeek();
        TimeSlot timeSlot = new TimeSlot();
        TimetableEntry entry = new TimetableEntry();

        teacher.setTeacherId("vipuldabhi.it@ddu.ac.in");
        teacher.setName("Dr. Vipul K Dabhi");
        teacher.setDepartment("Information Technology");
        teacher.setExperience(23);
        teacher.setQualifications("PhD$M.E in Computer Engineering");
        teacher.setRole("Professor & Head");
        teacher.setTeachingLoad(11);

        // Create a DayOfWeek object
        dayOfWeek.setDayId(1);
        dayOfWeek.setDayName("Monday");

        // Create a TimeSlot object
        timeSlot.setTimeSlotId(9);
        timeSlot.setStartTime("4:30");
        timeSlot.setEndTime("5:30");

        // Create a TimetableEntry object
        entry.setEntryId(1);
        entry.setTeacherId(teacher.getTeacherId());
        entry.setTimeSlotId(timeSlot.getTimeSlotId());
        entry.setDayOfWeekId(dayOfWeek.getDayId());
        entry.setSubject("Distributed Computing");
        entry.setRoom("Room no. 2");
        entry.setSemester(7);
        entry.setDivision("I");

        dbHelper.insertTimetableEntry(entry);

        dayOfWeek.setDayId(2);
        dayOfWeek.setDayName("Tuesday");

        // Create a TimeSlot object
        timeSlot.setTimeSlotId(6);
        timeSlot.setStartTime("1:30");
        timeSlot.setEndTime("2:30");

        // Create a TimetableEntry object
        entry.setEntryId(2);
        entry.setTeacherId(teacher.getTeacherId());
        entry.setTimeSlotId(timeSlot.getTimeSlotId());
        entry.setDayOfWeekId(dayOfWeek.getDayId());
        entry.setSubject("DevOps");
        entry.setRoom("SW4");
        entry.setSemester(7);
        entry.setDivision("H2");

        dbHelper.insertTimetableEntry(entry);

        dayOfWeek.setDayId(3);
        dayOfWeek.setDayName("Wednesday");

        // Create a TimeSlot object
        timeSlot.setTimeSlotId(9);
        timeSlot.setStartTime("4:30");
        timeSlot.setEndTime("5:30");

        // Create a TimetableEntry object
        entry.setEntryId(3);
        entry.setTeacherId(teacher.getTeacherId());
        entry.setTimeSlotId(timeSlot.getTimeSlotId());
        entry.setDayOfWeekId(dayOfWeek.getDayId());
        entry.setSubject("Distributed Computing");
        entry.setRoom("Room no. 33");
        entry.setSemester(7);
        entry.setDivision("H");

        dbHelper.insertTimetableEntry(entry);

        dayOfWeek.setDayId(4);
        dayOfWeek.setDayName("Thursday");

        // Create a TimeSlot object
        timeSlot.setTimeSlotId(7);
        timeSlot.setStartTime("2:30");
        timeSlot.setEndTime("3:30");

        // Create a TimetableEntry object
        entry.setEntryId(4);
        entry.setTeacherId(teacher.getTeacherId());
        entry.setTimeSlotId(timeSlot.getTimeSlotId());
        entry.setDayOfWeekId(dayOfWeek.getDayId());
        entry.setSubject("Distributed Computing");
        entry.setRoom("Room no. 2");
        entry.setSemester(7);
        entry.setDivision("I");

        dbHelper.insertTimetableEntry(entry);

        dayOfWeek.setDayId(5);
        dayOfWeek.setDayName("Friday");

        // Create a TimeSlot object
        timeSlot.setTimeSlotId(3);
        timeSlot.setStartTime("11:45");
        timeSlot.setEndTime("12:45");

        // Create a TimetableEntry object
        entry.setEntryId(5);
        entry.setTeacherId(teacher.getTeacherId());
        entry.setTimeSlotId(timeSlot.getTimeSlotId());
        entry.setDayOfWeekId(dayOfWeek.getDayId());
        entry.setSubject("Distributed Computing");
        entry.setRoom("Room no. 33");
        entry.setSemester(7);
        entry.setDivision("H");

        dbHelper.insertTimetableEntry(entry);

        // Create a TimeSlot object
        timeSlot.setTimeSlotId(11);
        timeSlot.setStartTime("1:30");
        timeSlot.setEndTime("3:30");

        // Create a TimetableEntry object
        entry.setEntryId(6);
        entry.setTeacherId(teacher.getTeacherId());
        entry.setTimeSlotId(timeSlot.getTimeSlotId());
        entry.setDayOfWeekId(dayOfWeek.getDayId());
        entry.setSubject("Distributed Computing");
        entry.setRoom("SW2");
        entry.setSemester(7);
        entry.setDivision("H4");

        dbHelper.insertTimetableEntry(entry);

        dayOfWeek.setDayId(6);
        dayOfWeek.setDayName("Saturday");

        // Create a TimeSlot object
        timeSlot.setTimeSlotId(10);
        timeSlot.setStartTime("8:30");
        timeSlot.setEndTime("11:45");

        // Create a TimetableEntry object
        entry.setEntryId(7);
        entry.setTeacherId(teacher.getTeacherId());
        entry.setTimeSlotId(timeSlot.getTimeSlotId());
        entry.setDayOfWeekId(dayOfWeek.getDayId());
        entry.setSubject("Project-II");
        entry.setRoom("SW2");
        entry.setSemester(7);
        entry.setDivision("H1");

        dbHelper.insertTimetableEntry(entry);
    } // entryId : 1 to 7

}
