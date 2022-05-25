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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ihuiee.advhci.travelody.DB.AppDatabase;
import ihuiee.advhci.travelody.DB.Cities;
import ihuiee.advhci.travelody.DB.Hotels;
import ihuiee.advhci.travelody.R;

public class CityUpdate extends Fragment {

    List<String> countriesList = new ArrayList<>();
    List<String> citiesList = new ArrayList<>();
    String selectedCountry;
    String selectedCity;
    String defaultSelection = "CHOOSE";
    Button submitButton;
    EditText cityName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();
        Spinner country=view.findViewById(R.id.ChooseCountryCityUpdate);
        ArrayAdapter countryAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());
        Spinner city=view.findViewById(R.id.CityUpdateChoose);
        ArrayAdapter cityAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());
        submitButton=view.findViewById(R.id.CityUpdateButton);
        cityName=view.findViewById(R.id.CityUpdateNameTextPlain);

        cityAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        city.setAdapter(cityAdapter);


        countriesList.addAll(db.countriesDao().getCountryNames());
        countriesList.sort(String::compareToIgnoreCase);
        countriesList.add(0,defaultSelection);

        citiesList.addAll(db.citiesDao().getCityNames());
        citiesList.sort(String::compareToIgnoreCase);
        citiesList.add(0,defaultSelection);
        cityAdapter.addAll(citiesList);
        cityAdapter.notifyDataSetChanged();

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
            if (!selectedCity.equals(defaultSelection) && !TextUtils.isEmpty(cityName.getText().toString())){
                Cities city1 = new Cities();
                city1.setNameOfCity(cityName.getText().toString());
                city1.setIdOfCity(db.citiesDao().getCitiesIdByName(selectedCity));
                if(selectedCountry.equals(defaultSelection)) {
                    city1.setIdOfCountry(db.citiesDao().getCityById(db.citiesDao().getCitiesIdByName(selectedCity)).idOfCountry);
                }else city1.setIdOfCountry(db.countriesDao().getCountryIdByName(selectedCountry));
                try {
                    db.citiesDao().updateCity(city1);
                    Toast.makeText(requireActivity().getApplicationContext(),"City Updated",Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(requireActivity().getApplicationContext(),"City already exists",Toast.LENGTH_LONG).show();
                }
                cityName.setText("");
                country.setSelection(0);
                cityAdapter.clear();
                citiesList.clear();
                citiesList.addAll(db.citiesDao().getCityNames());
                citiesList.add(0, defaultSelection);
                cityAdapter.addAll(citiesList);
                city.setSelection(0);


            }else
                Toast.makeText(requireActivity().getApplicationContext(),"Required fields(*) cannot be empty",Toast.LENGTH_LONG).show();
        });
    }
}