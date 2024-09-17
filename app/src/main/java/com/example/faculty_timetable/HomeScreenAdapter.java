package com.example.faculty_timetable;

import android.content.Context;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeScreenAdapter extends RecyclerView.Adapter<HomeScreenAdapter.myViewHolder> {

    ArrayList<Faculty> facultyArrayList;
    Context context;

    public HomeScreenAdapter(ArrayList<Faculty> facultyArrayList, Context context) {
        this.facultyArrayList = facultyArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.schedule_item,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Faculty faculty = facultyArrayList.get(position);
        holder.subject.setText(faculty.getSubjects_taken().toString());
        holder.semester.setText(faculty.getSemesters().toString());
        holder.classroom.setText(faculty.getClassroom_numbers().toString());
        holder.time.setText(faculty.getClass_time().toString());
    }

    @Override
    public int getItemCount() {
        return facultyArrayList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView subject,semester, classroom, time;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            subject = itemView.findViewById(R.id.textView_scheduleItem_subject);
            semester = itemView.findViewById(R.id.textView_scheduleItem_semster);
            classroom = itemView.findViewById(R.id.textView_scheduleItem_classroom);
            time = itemView.findViewById(R.id.textView_scheduleItem_time);

        }
    }

}
