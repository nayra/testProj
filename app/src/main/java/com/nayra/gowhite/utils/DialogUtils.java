package com.nayra.gowhite.utils;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.nayra.gowhite.R;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

/**
 * Created by nayrael-sayed on 2/15/18.
 */

public class DialogUtils {
    private static int current_selected_country_index = 0;
    private static int current_selected_language_index = 0;

    private static int new_selected_country_index = 0;
    private static int new_selected_language_index = 0;

    private static int new_selected_country_id = 0;

    // private static ArrayList<Country> countries = new ArrayList<>();
    public static void showChooseLanguageAndAreaDialog(final Context context) {
        final String[] countries = context.getResources().getStringArray(R.array.countries);

        // countries = GetCountriesViewModel.getInstance().getCountries();

        //final SpinnerCountryCustomAdapter adapter = new SpinnerCountryCustomAdapter(context, countries);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.row_spinner, countries);
        adapter.setDropDownViewResource(R.layout.row_spinner);

        current_selected_country_index = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_COUNTRY_INDEX);
        current_selected_language_index = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_LANGUAGE_INDEX);

        final DialogPlus dialog = DialogPlus.newDialog(context)
                .setContentHolder(new ViewHolder(R.layout.dialog_change_language_and_area))
                .create();

        final Spinner countriesSpinner = (Spinner) dialog.findViewById(R.id.spCountries);
        countriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int i, final long l) {
                new_selected_country_index = i;
                new_selected_country_id = 1;//countries.get(i).getCountryID();
            }

            @Override
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
        countriesSpinner.setAdapter(adapter);
        countriesSpinner.setSelection(current_selected_country_index);


        final String[] languages = context.getResources().getStringArray(R.array.languages);
        final ArrayAdapter<String> langAdapter = new ArrayAdapter<>(context, R.layout.row_spinner, languages);
        langAdapter.setDropDownViewResource(R.layout.row_spinner);

        final Spinner languagesSpinner = (Spinner) dialog.findViewById(R.id.spLanguages);
        languagesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int i, final long l) {
                new_selected_language_index = i;
            }

            @Override
            public void onNothingSelected(final AdapterView<?> adapterView) {

            }
        });
        languagesSpinner.setAdapter(langAdapter);
        languagesSpinner.setSelection(current_selected_language_index);

        final Button changeButton = (Button) dialog.findViewById(R.id.btnChange);
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                SharedPrefsUtil.setInteger(SharedPrefsUtil.SELECTED_COUNTRY_INDEX, new_selected_country_index);
                SharedPrefsUtil.setInteger(SharedPrefsUtil.SELECTED_COUNTRY_ID, new_selected_country_id);


                LanguageUtil.changeLanguage(new_selected_language_index, context);

                dialog.dismiss();

            }
        });
        dialog.show();
    }
}
