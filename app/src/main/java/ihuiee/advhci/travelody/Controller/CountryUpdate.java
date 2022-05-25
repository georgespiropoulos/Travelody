package ihuiee.advhci.travelody.Controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ihuiee.advhci.travelody.DB.AppDatabase;
import ihuiee.advhci.travelody.DB.Cities;
import ihuiee.advhci.travelody.DB.Countries;
import ihuiee.advhci.travelody.R;

public class CountryUpdate extends Fragment {


    Button countryUpdateBtn;
    EditText countryName;
    String selectedCountry;
    List<String> countriesList = new ArrayList<>();
    String defaultSelection = "CHOOSE";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();

        countryUpdateBtn = (Button) view.findViewById(R.id.CountryUpdateButton);
        countryName = (EditText) view.findViewById(R.id.CountryUpdateNameEdit);
        Spinner country=view.findViewById(R.id.CountryUpdateChoose);
        ArrayAdapter countryAdapter = new ArrayAdapter<Integer>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());


        countriesList.addAll(db.countriesDao().getCountryNames());
        countriesList.sort(String::compareToIgnoreCase);
        countriesList.stream();

        countriesList.add(0,defaultSelection);

        countryAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        country.setAdapter(countryAdapter);
        countryAdapter.addAll(countriesList);
        countryAdapter.notifyDataSetChanged();
        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCountry = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        countryUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(countryName.getText().toString()) && !selectedCountry.equals(defaultSelection)){
                    try {
                        Countries country1 = new Countries();
                        country1.setNameOfCountry(countryName.getText().toString());
                        country1.setIdOfCountry(db.countriesDao().getCountryIdByName(selectedCountry));
                        db.countriesDao().updateCountry(country1);
                        countryName.setText("");
                        countryAdapter.clear();
                        countriesList.clear();
                        countriesList.addAll(db.countriesDao().getCountryNames());
                        countriesList.sort(String::compareToIgnoreCase);
                        countriesList.add(0,defaultSelection);
                        countryAdapter.addAll(countriesList);
                        countryAdapter.notifyDataSetChanged();
                        country.setSelection(0);
                        Toast.makeText(getActivity().getApplicationContext(), "Country updated", Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        countryName.setText("");
                        country.setSelection(0);
                        Toast.makeText(getActivity().getApplicationContext(), "Country already exists", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "All fields are required", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}