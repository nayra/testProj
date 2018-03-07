package com.nayra.gowhite.book_now;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

import com.nayra.gowhite.R;
import com.nayra.gowhite.databinding.FragmentCleaningDetailsBinding;

/**
 * Created by nayrael-sayed on 2/14/18.
 */

public class CleaningDetailsFragment extends Fragment {
    private FragmentCleaningDetailsBinding binding;

    private int selected_repetition = 1, selected_duration = 2, selected_number_cleaner = 1;
    private boolean isNeedMaterials = false;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        // final View view = inflater.inflate(R.layout.fragment_cleaning_details , container, false);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cleaning_details, container, false);

        initUI();
        return binding.getRoot();
    }

    private void initUI() {
        final String[] repetition = getResources().getStringArray(R.array.Repetition);
        final ArrayAdapter<String> repetitionAdapter = new ArrayAdapter<>(getActivity(), R.layout.row_spinner, repetition);
        repetitionAdapter.setDropDownViewResource(R.layout.row_spinner);
        binding.spRepetition.setAdapter(repetitionAdapter);
        binding.spRepetition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int i, final long l) {
                selected_repetition = i + 1;
            }

            @Override
            public void onNothingSelected(final AdapterView<?> adapterView) {
                selected_repetition = 1;
            }
        });

        final String[] durationsArr = getActivity().getResources().getStringArray(R.array.durations);
        final ArrayAdapter<String> durationAdapter = new ArrayAdapter<>(getActivity(), R.layout.row_spinner, durationsArr);
        durationAdapter.setDropDownViewResource(R.layout.row_spinner);
        binding.spDuration.setAdapter(durationAdapter);
        binding.spDuration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int i, final long l) {
                selected_duration = i + 2;
                ((BookNowActivity) getActivity()).calcPrice(selected_duration, selected_number_cleaner, durationsArr[i]);
            }

            @Override
            public void onNothingSelected(final AdapterView<?> adapterView) {
                selected_duration = 2;
            }
        });


        final String[] cleanerArr = getActivity().getResources().getStringArray(R.array.number_cleaners);
        final ArrayAdapter<String> cleanerAdapter = new ArrayAdapter<>(getActivity(), R.layout.row_spinner, cleanerArr);
        cleanerAdapter.setDropDownViewResource(R.layout.row_spinner);
        binding.spCleaners.setAdapter(cleanerAdapter);
        binding.spCleaners.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int i, final long l) {
                selected_number_cleaner = i + 1;
                ((BookNowActivity) getActivity()).calcPrice(selected_duration, selected_number_cleaner, durationsArr[selected_duration - 2]);
            }

            @Override
            public void onNothingSelected(final AdapterView<?> adapterView) {
                selected_number_cleaner = 1;
            }
        });


        binding.swMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
                isNeedMaterials = b;
            }
        });
    }


    public void getCleaningDetails() {
        String instruction = binding.etCleaningInstruction.getText().toString();
        BookNowActivity.appointmentDetails.setType(selected_repetition);
        BookNowActivity.appointmentDetails.setDuration(selected_duration);
        BookNowActivity.appointmentDetails.setAmount(selected_number_cleaner);
        BookNowActivity.appointmentDetails.setWantCleaningMatrial(isNeedMaterials);
        BookNowActivity.appointmentDetails.setCleaningInstructions(instruction);
    }
}
