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

/**
 * Created by nayrael-sayed on 2/17/18.
 */

public class OrderSummaryFragment extends Fragment {
    private FragmentOrderSummaryBinding binding;
    private Appointment appointment;

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_summary, container, false);

//        initUI();
        return binding.getRoot();
    }
}
