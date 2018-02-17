package com.nayra.gowhite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nayra.gowhite.R;
import com.nayra.gowhite.model.City;
import com.nayra.gowhite.utils.SharedPrefsUtil;

import java.util.ArrayList;

/**
 * Created by nayrael-sayed on 2/17/18.
 */

public class SpinnerCityCustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<City> cities = new ArrayList<>();
    private LayoutInflater layoutInflater;

    private int selected_lang_index = 0;

    public SpinnerCityCustomAdapter(Context applicationContext, ArrayList<City> cities) {
        this.context = applicationContext;
        this.cities = cities;
        layoutInflater = (LayoutInflater.from(applicationContext));

        selected_lang_index = SharedPrefsUtil.getInteger(SharedPrefsUtil.SELECTED_LANGUAGE_INDEX);
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.row_spinner, null);

        TextView names = view.findViewById(R.id.textView);

        if (selected_lang_index == 0)
            names.setText(cities.get(i).getEn_name());
        else {
            names.setText(cities.get(i).getAr_name());
        }

        return view;
    }
}
