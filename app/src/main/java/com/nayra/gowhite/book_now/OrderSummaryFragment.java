package com.nayra.gowhite.book_now;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nayra.gowhite.R;
import com.nayra.gowhite.databinding.FragmentOrderSummaryBinding;
import com.nayra.gowhite.model.Appointment;
import com.nayra.gowhite.model.UserInfo;
import com.nayra.gowhite.utils.SharedPrefsUtil;

/**
 * Created by nayrael-sayed on 2/17/18.
 */

public class OrderSummaryFragment extends Fragment {
    private FragmentOrderSummaryBinding binding;
    private Appointment appointment;
    private UserInfo userInfo;

    public static final String appointmentStr = "appointment";
    public static final String userInfoStr = "userInfo";

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);

        appointment = args.getParcelable(appointmentStr);
        userInfo = args.getParcelable(userInfoStr);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_summary, container, false);

        displayData();

        return binding.getRoot();
    }

    private void displayData() {
        String[] repetition = getActivity().getResources().getStringArray(R.array.Repetition);
        int type = appointment.getType();
        binding.tvRepetition.setText(repetition[type - 1]);

        String fullDate = appointment.getStartDate();
        String[] date_time = fullDate.split(" ");

        if (date_time.length >= 1)
            binding.tvDate.setText(date_time[0]);
        if (date_time.length >= 2)
            binding.tvTime.setText(date_time[1]);


        binding.tvDuration.setText(String.valueOf(appointment.getDuration()));

        binding.tvAmount.setText(String.valueOf(appointment.getAmount()));

        binding.tvPrice.setText("PRICE");

        binding.tvName.setText(userInfo.getName());

        binding.tvAmount.setText(userInfo.getAddress());

        binding.tvCity.setText(SharedPrefsUtil.getString(SharedPrefsUtil.SELECTED_CITY_NAME));
        binding.tvArea.setText(SharedPrefsUtil.getString(SharedPrefsUtil.SELECTED_AREA_NAME));

    }
}
