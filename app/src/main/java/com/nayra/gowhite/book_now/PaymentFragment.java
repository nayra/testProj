package com.nayra.gowhite.book_now;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.nayra.gowhite.R;
import com.nayra.gowhite.databinding.FragmentPaymentBinding;
import com.nayra.gowhite.utils.ErrorUtils;

/**
 * Created by nayrael-sayed on 2/17/18.
 */

public class PaymentFragment extends Fragment {
    private FragmentPaymentBinding binding;
    private boolean isCard = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment, container, false);

        initUI();
        return binding.getRoot();
    }

    private void initUI() {
        binding.btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyVoucherCode();
            }
        });

        binding.swPaymentMethod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isCard = b;
            }
        });
    }

    private void applyVoucherCode() {
        final String code = binding.etVoucherCode.getText().toString();
        if (code.isEmpty()) {
            ErrorUtils.setEditTextError(getActivity(), binding.etVoucherCode);
        }
    }

    public void getPaymentDetails() {
        if (isCard)
            BookNowActivity.appointmentDetails.setPayment_method(1);
        else
            BookNowActivity.appointmentDetails.setPayment_method(0);
    }
}
