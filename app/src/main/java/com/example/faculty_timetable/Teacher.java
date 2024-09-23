package com.example.faculty_timetable;

public class Teacher {
    private int teacherId;
    private String name;
    private String department;
    private String role;
    private String qualifications;
    private int experience;
    private int teachingLoad;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getTeachingLoad() {
        return teachingLoad;
    }

    public void setTeachingLoad(int teachingLoad) {
        this.teachingLoad = teachingLoad;
    }
}