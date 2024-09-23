package com.example.faculty_timetable;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class TimetableEntryViewHolder extends RecyclerView.ViewHolder {

    TextView textViewScheduleItemSubject;
    TextView textViewScheduleItemSemester;
    TextView textViewScheduleItemClassroom;
    TextView textViewScheduleItemTime;

    public TimetableEntryViewHolder(View itemView) {
        super(itemView);

        textViewScheduleItemSubject = itemView.findViewById(R.id.textView_scheduleItem_subject);
        textViewScheduleItemSemester = itemView.findViewById(R.id.textView_scheduleItem_semster);
        textViewScheduleItemClassroom = itemView.findViewById(R.id.textView_scheduleItem_classroom);
        textViewScheduleItemTime = itemView.findViewById(R.id.textView_scheduleItem_time);
    }
}