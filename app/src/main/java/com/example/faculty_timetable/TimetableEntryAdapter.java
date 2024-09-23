package com.example.faculty_timetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TimetableEntryAdapter extends RecyclerView.Adapter<TimetableEntryViewHolder> {

    private List<TimetableEntry> timetableEntries;
    private Context context;

    public TimetableEntryAdapter(Context context) {
        this.context = context;
        timetableEntries = new ArrayList<>();
    }

    public void setData(List<TimetableEntry> entries) {
        timetableEntries.clear();
        timetableEntries.addAll(entries);
        notifyDataSetChanged();
    }

    @Override
    public TimetableEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.schedule_item, parent, false);
        return new TimetableEntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TimetableEntryViewHolder holder, int position) {
        TimetableEntry entry = timetableEntries.get(position);
        holder.textViewScheduleItemSubject.setText(entry.getSubject());
        holder.textViewScheduleItemSemester.setText(entry.getSemester());
        holder.textViewScheduleItemClassroom.setText(entry.getRoom());
        holder.textViewScheduleItemTime.setText(entry.getTimeSlot().getStartTime() + "-" + entry.getTimeSlot().getEndTime());
//        holder.textViewScheduleItemSubject.setText("App Development");
//        holder.textViewScheduleItemSemester.setText("Sem V");
//        holder.textViewScheduleItemClassroom.setText("Classroom no. 32");
//        holder.textViewScheduleItemTime.setText("10.30 - 11.30");
    }

    @Override
    public int getItemCount() {
        return timetableEntries.size();
    }
}

