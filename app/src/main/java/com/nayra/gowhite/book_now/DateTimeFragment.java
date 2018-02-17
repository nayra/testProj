package com.nayra.gowhite.book_now;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;

import com.nayra.gowhite.R;
import com.nayra.gowhite.databinding.FragmentDateTimeBinding;
import com.nayra.gowhite.utils.Utils;

import java.util.Calendar;
/**
 * Created by nayrael-sayed on 2/14/18.
 */

public class DateTimeFragment extends Fragment {

    private FragmentDateTimeBinding binding;
    private String selected_time = "08:00", selected_date = "";

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = bindingView(inflater, container);
        initView();
        return view;
    }

    private void initView() {
        final Calendar calendar = Calendar.getInstance();

        selected_date = Utils.getDate(calendar.getTimeInMillis(), "dd-MM-yyyy");

        binding.datePicker.setMinDate(calendar.getTimeInMillis());
        binding.datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                String y = String.valueOf(year);
                month += 1;

                String m;
                if (month < 10) {
                    m = "0" + String.valueOf(month);
                } else {
                    m = String.valueOf(month);
                }

                String d;

                if (day < 10)
                    d = "0" + String.valueOf(day);
                else {
                    d = String.valueOf(day);
                }
                selected_date = d + "-" + m + "-" + y;

                Log.d("DateTimeFragment", selected_date);
            }
        });
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
        // final long milliseconds = 0;// binding.calendarView.getSelectedDate().getCalendar().getTimeInMillis();
        String date = selected_date;
        date += " " + selected_time;
        BookNowActivity.appointmentDetails.setStartDate(date);
    }

}
