package ihuiee.advhci.travelody.Controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ihuiee.advhci.travelody.DB.AppDatabase;
import ihuiee.advhci.travelody.DB.Countries;
import ihuiee.advhci.travelody.DB.CountriesDAO;
import ihuiee.advhci.travelody.R;

public class TripAdd extends Fragment {

    Button button;
    String selectedCountry;
    String selectedCity;
    String selectedHotel;
    FragmentManager fragmentManager = getFragmentManager();
    List<String> countriesList = new ArrayList<>();
    List<String> citiesList = new ArrayList<>();
    List<String> hotelList = new ArrayList<>();
    String defaultSelection = "CHOOSE";


    public TripAdd() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trip_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentManager = getParentFragmentManager();
        button=view.findViewById(R.id.TripAddNextButton);
        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();
        Spinner country=view.findViewById(R.id.CountryDropdown);
        ArrayAdapter countryAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());
        Spinner city=view.findViewById(R.id.CityDropdown);
        ArrayAdapter cityAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());
        Spinner hotel=view.findViewById(R.id.HotelDropDown);
        ArrayAdapter hotelAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());

        countriesList.add(0,defaultSelection);
        citiesList.add(0, defaultSelection);
        hotelList.add(0,defaultSelection);

        countriesList.addAll(db.countriesDao().getCountryNames());

        countryAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        country.setAdapter(countryAdapter);
        countryAdapter.addAll(countriesList);
        countryAdapter.notifyDataSetChanged();
        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCountry = parent.getItemAtPosition(position).toString();
                citiesList.addAll(db.citiesDao().getCitiesOfCountry(db.countriesDao().getCountryIdByName(selectedCountry)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cityAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        city.setAdapter(cityAdapter);
        cityAdapter.addAll(citiesList);
        cityAdapter.notifyDataSetChanged();
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = parent.getItemAtPosition(position).toString();
                hotelList.addAll(db.hotelsDao().getHotelsOfCity(db.citiesDao().getCitiesIdByName(selectedCity)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        hotelAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        hotel.setAdapter(hotelAdapter);
        hotelAdapter.addAll(hotelList);
        hotelAdapter.notifyDataSetChanged();
        hotel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedHotel = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button.setOnClickListener(view1 -> {
            if (!selectedCity.equals(defaultSelection) || !selectedHotel.equals(defaultSelection) || !selectedCountry.equals(defaultSelection)){
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new TripAddStep2()).commit();
            }else
                Toast.makeText(requireActivity().getApplicationContext(),"All fields are Required",Toast.LENGTH_LONG).show();
        });


    }
}