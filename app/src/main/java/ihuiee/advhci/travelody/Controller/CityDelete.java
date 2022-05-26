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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ihuiee.advhci.travelody.DB.AppDatabase;
import ihuiee.advhci.travelody.DB.Cities;
import ihuiee.advhci.travelody.DB.Hotels;
import ihuiee.advhci.travelody.R;

public class CityDelete extends Fragment {

    List<String> countriesList = new ArrayList<>();
    List<String> citiesList = new ArrayList<>();
    String selectedCountry;
    String selectedCity;
    String defaultSelection = "CHOOSE";
    Button submitButton;
    Spinner country;
    Spinner city;

    public CityDelete() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_delete, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();
        country=view.findViewById(R.id.cityDeleteDropdown);
        ArrayAdapter countryAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());
        city=view.findViewById(R.id.CityDeleteName);
        ArrayAdapter cityAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());
        submitButton=view.findViewById(R.id.CityDeleteButton);

        cityAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        city.setAdapter(cityAdapter);


        countriesList.addAll(db.countriesDao().getCountryNames());
        countriesList.sort(String::compareToIgnoreCase);
        countriesList.add(0,defaultSelection);

        countryAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        country.setAdapter(countryAdapter);
        countryAdapter.addAll(countriesList);
        countryAdapter.notifyDataSetChanged();
        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCountry = parent.getItemAtPosition(position).toString();
                citiesList.clear();
                citiesList.addAll(db.citiesDao().getCitiesOfCountry(db.countriesDao().getCountryIdByName(selectedCountry)));
                citiesList.sort(String::compareToIgnoreCase);
                citiesList.add(0, defaultSelection);
                cityAdapter.clear();
                cityAdapter.addAll(citiesList);
                cityAdapter.notifyDataSetChanged();
                city.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });



        submitButton.setOnClickListener(view1 -> {
            if (!selectedCity.equals(defaultSelection) && !selectedCountry.equals(defaultSelection)){
               try{
                   Cities cityObj = db.citiesDao().getCityById(db.citiesDao().getCitiesIdByName(selectedCity));
                   db.citiesDao().deleteCity(cityObj);
                   Toast.makeText(requireActivity().getApplicationContext(),"City Deleted",Toast.LENGTH_LONG).show();
                   cityAdapter.clear();
                   citiesList.clear();
                   countryAdapter.clear();
                   countriesList.clear();
                   countriesList.addAll(db.countriesDao().getCountryNames());
                   countriesList.sort(String::compareToIgnoreCase);
                   countriesList.add(0,defaultSelection);
                   countryAdapter.addAll(countriesList);
                   countryAdapter.notifyDataSetChanged();
                   country.setSelection(0);
                   citiesList.add(0, defaultSelection);
                   cityAdapter.addAll(citiesList);
                   city.setSelection(0);
               }catch (Exception e){
                   Toast.makeText(requireActivity().getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
               }
            }else
                Toast.makeText(requireActivity().getApplicationContext(),"All fields are required",Toast.LENGTH_LONG).show();
        });

    }
}