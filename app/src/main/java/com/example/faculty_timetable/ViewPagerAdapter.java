package com.example.faculty_timetable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.faculty_timetable.fragments.FridayFragment;
import com.example.faculty_timetable.fragments.MondayFragment;
import com.example.faculty_timetable.fragments.SaturdayFragment;
import com.example.faculty_timetable.fragments.ThrusdayFragment;
import com.example.faculty_timetable.fragments.TuesdayFragment;
import com.example.faculty_timetable.fragments.WednesdayFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new MondayFragment();
            case 1:
                return new TuesdayFragment();
            case 2:
                return new WednesdayFragment();
            case 3:
                return new ThrusdayFragment();
            case 4:
                return new FridayFragment();
            case 5:
                return new SaturdayFragment();
            default:
                return new MondayFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
