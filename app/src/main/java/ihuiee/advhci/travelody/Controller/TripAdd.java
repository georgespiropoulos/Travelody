package ihuiee.advhci.travelody.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ihuiee.advhci.travelody.DB.AppDatabase;
import ihuiee.advhci.travelody.DB.Trips;
import ihuiee.advhci.travelody.R;

public class TripAdd extends Fragment {

    Button nextButton;
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
        nextButton=view.findViewById(R.id.TripAddNextButton);
        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();
        Spinner country=view.findViewById(R.id.CountryDropdown);
        ArrayAdapter countryAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());
        Spinner city=view.findViewById(R.id.CityDropdown);
        ArrayAdapter cityAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());
        Spinner hotel=view.findViewById(R.id.HotelDropDown);
        ArrayAdapter hotelAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());

        cityAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        city.setAdapter(cityAdapter);

        hotelAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        hotel.setAdapter(hotelAdapter);

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

                hotelList.clear();
                hotelList.add(0,defaultSelection);
                hotelAdapter.clear();
                hotelAdapter.addAll(hotelList);
                hotel.setSelection(0);

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


//        System.out.println(citiesList);
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = parent.getItemAtPosition(position).toString();
                hotelList.clear();
                hotelList.addAll(db.hotelsDao().getHotelsOfCity(db.citiesDao().getCitiesIdByName(selectedCity)));

                hotelList.sort(String::compareToIgnoreCase);
                hotelList.add(0,defaultSelection);
                hotelAdapter.clear();
                hotelAdapter.addAll(hotelList);
                hotelAdapter.notifyDataSetChanged();
                hotel.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        hotel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedHotel = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        nextButton.setOnClickListener(view1 -> {
            if (!selectedCity.equals(defaultSelection) && !selectedHotel.equals(defaultSelection) && !selectedCountry.equals(defaultSelection)){
                Trips trip = new Trips();
                trip.setCountryIdOfTrip(db.countriesDao().getCountryIdByName(selectedCountry));
                trip.setCityIdOfTrip(db.citiesDao().getCitiesIdByName(selectedCity));
                trip.setHotelIdOfTrip(db.hotelsDao().getHotelIdByHotelName(selectedHotel));
                try{
                   Bundle bundle = new Bundle();
                   bundle.putSerializable("trip", trip);
                   TripAddStep2 trip2 = new TripAddStep2();
                   trip2.setArguments(bundle);
                   fragmentManager.beginTransaction().replace(R.id.fragment_container, trip2).commit();
                }catch (Exception e){
                    Toast.makeText(requireActivity().getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                }
            }else
                Toast.makeText(requireActivity().getApplicationContext(),"All fields are required",Toast.LENGTH_LONG).show();
        });


    }
}