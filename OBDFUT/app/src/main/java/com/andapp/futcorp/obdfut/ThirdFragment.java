package com.andapp.futcorp.obdfut;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {

    EditText totalKm;


    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DBHelper dbHelper = new DBHelper(getActivity().getApplicationContext());
        loadSpinnerData();
        totalKm = (EditText) getActivity().findViewById(R.id.editText);
        totalKm.setText(String.valueOf(dbHelper.getTotalKm()));

    }

    private void loadSpinnerData() {
        Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinner);
        DBHelper dbHelper = new DBHelper(getActivity().getApplicationContext());
        List<String> models = dbHelper.getAllModels();

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_item, models);*/
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.my_spinner, models);

//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.my_spinner);

        spinner.setAdapter(adapter);
    }
}
