package com.example.faculty_timetable;

import androidx.annotation.NonNull;

public class loadEntries {

    private static Teacher teacher;
    private static DayOfWeek dayOfWeek;
    private static TimeSlot timeSlot;
    private static TimetableEntry entry;

    public static void loadTimetableEntries(@NonNull DatabaseHelper dbHelper) {
        loadData(dbHelper);
        loadVD(dbHelper);
    }

    public static void loadData(DatabaseHelper dbHelper){
        dayOfWeek = new DayOfWeek();
        dayOfWeek.setDayName("Monday");
        dbHelper.insertDay(dayOfWeek);
        dayOfWeek.setDayName("Tuesday");
        dbHelper.insertDay(dayOfWeek);
        dayOfWeek.setDayName("Wednesday");
        dbHelper.insertDay(dayOfWeek);
        dayOfWeek.setDayName("Thrusday");
        dbHelper.insertDay(dayOfWeek);
        dayOfWeek.setDayName("Friday");
        dbHelper.insertDay(dayOfWeek);
        dayOfWeek.setDayName("Saturday");
        dbHelper.insertDay(dayOfWeek);

        timeSlot = new TimeSlot();
        timeSlot.setStartTime("8:30");
        timeSlot.setEndTime("9:30");
        dbHelper.insertTimeslots(timeSlot);
        timeSlot.setStartTime("9:30");
        timeSlot.setEndTime("10:30");
        dbHelper.insertTimeslots(timeSlot);
        timeSlot.setStartTime("10:45");
        timeSlot.setEndTime("11:45");
        dbHelper.insertTimeslots(timeSlot);
        timeSlot.setStartTime("11:45");
        timeSlot.setEndTime("12:45");
        dbHelper.insertTimeslots(timeSlot);
        timeSlot.setStartTime("12:45");
        timeSlot.setEndTime("1:30");
        dbHelper.insertTimeslots(timeSlot);
        timeSlot.setStartTime("1:30");
        timeSlot.setEndTime("2:30");
        dbHelper.insertTimeslots(timeSlot);
        timeSlot.setStartTime("2:30");
        timeSlot.setEndTime("3:30");
        dbHelper.insertTimeslots(timeSlot);
        timeSlot.setStartTime("3:30");
        timeSlot.setEndTime("4:30");
        dbHelper.insertTimeslots(timeSlot);
        timeSlot.setStartTime("4:30");
        timeSlot.setEndTime("5:30");
        dbHelper.insertTimeslots(timeSlot);
        timeSlot.setStartTime("8:30");
        timeSlot.setEndTime("11:45");
        dbHelper.insertTimeslots(timeSlot);
        timeSlot.setStartTime("1:30");
        timeSlot.setEndTime("3:30");
        dbHelper.insertTimeslots(timeSlot);
    }

    public static void loadVD(DatabaseHelper dbHelper){
        teacher = new Teacher();
        entry = new TimetableEntry();

        teacher.setTeacherId("vipuldabhi.it@ddu.ac.in");
        teacher.setName("Dr. Vipul K Dabhi");
        teacher.setDepartment("Information Technology");
        teacher.setExperience(23);
        teacher.setQualifications("PhD$M.E in Computer Engineering");
        teacher.setRole("Professor & Head");
        teacher.setTeachingLoad(11);
        dbHelper.insertFaculty(teacher);

        // Create a DayOfWeek object
        dayOfWeek.setDayId(1);
        // Create a TimeSlot object
        timeSlot.setTimeSlotId(9);
        // Create a TimetableEntry object
        entry.setTeacherId(teacher.getTeacherId());
        entry.setTimeSlotId(timeSlot.getTimeSlotId());
        entry.setDayOfWeekId(dayOfWeek.getDayId());
        entry.setSubject("Distributed Computing");
        entry.setRoom("Room no. 2");
        entry.setSemester(7);
        entry.setDivision("I");
        dbHelper.insertTimetableEntry(entry);

        dayOfWeek.setDayId(2);
        // Create a TimeSlot object
        timeSlot.setTimeSlotId(6);
        // Create a TimetableEntry object
        entry.setTeacherId(teacher.getTeacherId());
        entry.setTimeSlotId(timeSlot.getTimeSlotId());
        entry.setDayOfWeekId(dayOfWeek.getDayId());
        entry.setSubject("DevOps");
        entry.setRoom("SW4");
        entry.setSemester(7);
        entry.setDivision("H2");
        dbHelper.insertTimetableEntry(entry);

        dayOfWeek.setDayId(3);
        // Create a TimeSlot object
        timeSlot.setTimeSlotId(9);
        // Create a TimetableEntry object
        entry.setTeacherId(teacher.getTeacherId());
        entry.setTimeSlotId(timeSlot.getTimeSlotId());
        entry.setDayOfWeekId(dayOfWeek.getDayId());
        entry.setSubject("Distributed Computing");
        entry.setRoom("Room no. 33");
        entry.setSemester(7);
        entry.setDivision("H");
        dbHelper.insertTimetableEntry(entry);

        dayOfWeek.setDayId(4);
        // Create a TimeSlot object
        timeSlot.setTimeSlotId(7);
        // Create a TimetableEntry object
        entry.setTeacherId(teacher.getTeacherId());
        entry.setTimeSlotId(timeSlot.getTimeSlotId());
        entry.setDayOfWeekId(dayOfWeek.getDayId());
        entry.setSubject("Distributed Computing");
        entry.setRoom("Room no. 2");
        entry.setSemester(7);
        entry.setDivision("I");
        dbHelper.insertTimetableEntry(entry);

        dayOfWeek.setDayId(5);
        // Create a TimeSlot object
        timeSlot.setTimeSlotId(3);
        // Create a TimetableEntry object
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
        // Create a TimetableEntry object
        entry.setTeacherId(teacher.getTeacherId());
        entry.setTimeSlotId(timeSlot.getTimeSlotId());
        entry.setDayOfWeekId(dayOfWeek.getDayId());
        entry.setSubject("Distributed Computing");
        entry.setRoom("SW2");
        entry.setSemester(7);
        entry.setDivision("H4");
        dbHelper.insertTimetableEntry(entry);

        dayOfWeek.setDayId(6);
        // Create a TimeSlot object
        timeSlot.setTimeSlotId(10);
        // Create a TimetableEntry object
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
