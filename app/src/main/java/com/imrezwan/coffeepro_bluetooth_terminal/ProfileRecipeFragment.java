package com.imrezwan.coffeepro_bluetooth_terminal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ProfileRecipeFragment extends Fragment {
    String[] totalExtractionOptionsShow = { "200 ml", "210 ml", "220 ml", "230 ml","240 ml" };
    String[] totalExtractionOptionsValue = { "200", "210", "220", "230","240" };


    String[] temperatureShow = { "88c", "89c", "90c", "91c","92c" };
    String[] temperatureValue = { "88", "89", "90", "91","92" };

    public ProfileRecipeFragment() {
        // Required empty public constructor
    }


    public static ProfileRecipeFragment newInstance(String param1, String param2) {
        ProfileRecipeFragment fragment = new ProfileRecipeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_recipe, container, false);
        Spinner spinTotalExtraction = view.findViewById(R.id.spinner_total_extraction);
        Spinner spinTemperature = view.findViewById(R.id.spinner_temperature);
        spinTotalExtraction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getContext(),totalExtractionOptionsValue[position],Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinTemperature.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),temperatureValue[i],Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter totalExtractionAdapter = new ArrayAdapter(getContext(),R.layout.simple_spinner_item,
                totalExtractionOptionsShow);
        totalExtractionAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinTotalExtraction.setAdapter(totalExtractionAdapter);

        ArrayAdapter temperatureAdapter = new ArrayAdapter(getContext(),R.layout.simple_spinner_item,
                temperatureShow);
        temperatureAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinTemperature.setAdapter(temperatureAdapter);
        return view;
    }
}