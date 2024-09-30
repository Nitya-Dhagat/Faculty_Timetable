package com.example.faculty_timetable;


import static com.example.faculty_timetable.retrieveDetails.retrieveTimeSlot;

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
        String sem = String.valueOf(entry.getSemester());
        holder.textViewScheduleItemSemester.setText("Sem "+sem);
        holder.textViewScheduleItemDivison.setText(entry.getDivision());
        holder.textViewScheduleItemClassroom.setText(entry.getRoom());
        //holder.textViewScheduleItemTime.setText(entry.getTimeSlot().getStartTime() + "-" + entry.getTimeSlot().getEndTime());
        holder.textViewScheduleItemTime.setText(retrieveTimeSlot(context, entry.getTimeSlotId()));
        System.out.println("timeslot = "+retrieveTimeSlot(context, entry.getTimeSlotId())+"id = "+entry.getEntryId());
}

    @Override
    public int getItemCount() {
        return timetableEntries.size();
    }
}

