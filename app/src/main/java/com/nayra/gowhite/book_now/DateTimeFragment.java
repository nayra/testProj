package com.nayra.gowhite.book_now;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.nayra.gowhite.R;
import com.nayra.gowhite.databinding.FragmentDateTimeBinding;
import com.nayra.gowhite.utils.Utils;
import com.prolificinteractive.materialcalendarview.CalendarMode;

import java.util.Calendar;
/**
 * Created by nayrael-sayed on 2/14/18.
 */

public class DateTimeFragment extends Fragment {

    private FragmentDateTimeBinding binding;
    private String selected_time = "08:00";

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = bindingView(inflater, container);
        initView();
        return view;
    }

    private void initView() {
        final Calendar calendar = Calendar.getInstance();

        binding.calendarView.setDateSelected(calendar, true);
        binding.calendarView.state().edit()
                .setMinimumDate(calendar)
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();


        final String[] times = getActivity().getResources().getStringArray(R.array.times);
        binding.spTimes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_time = times[i];
            }

            @Override
            public void onNothingSelected(final AdapterView<?> adapterView) {

            }
        });
    }

    private View bindingView(final LayoutInflater inflater, final ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_date_time, container, false);
        return binding.getRoot();
    }


    public void getDateAndTimeDetails() {
        final long milliseconds = binding.calendarView.getSelectedDate().getCalendar().getTimeInMillis();
        String date = Utils.getDate(milliseconds, "dd-MM-yyyy");
        date += " " + selected_time;
        BookNowActivity.appointmentDetails.setStartDate(date);
    }

}
