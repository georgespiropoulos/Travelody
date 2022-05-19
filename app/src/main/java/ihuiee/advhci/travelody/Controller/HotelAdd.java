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
import ihuiee.advhci.travelody.DB.Hotels;
import ihuiee.advhci.travelody.R;

public class HotelAdd extends Fragment {

    List<String> countriesList = new ArrayList<>();
    List<String> citiesList = new ArrayList<>();
    List<String> hotelList = new ArrayList<>();
    String selectedCountry;
    String selectedCity;
    String defaultSelection = "CHOOSE";
    Button submitButton;
    EditText hotelName;
    EditText hotelAddress;

       public HotelAdd() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hotel_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();
        Spinner country=view.findViewById(R.id.HotelAddChooseCountry);
        ArrayAdapter countryAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());
        Spinner city=view.findViewById(R.id.HotelAddChooseCity);
        ArrayAdapter cityAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());
        submitButton=view.findViewById(R.id.HotelAddSubmit);
        hotelName=view.findViewById(R.id.HotelAddName);
        hotelAddress=view.findViewById(R.id.HotelAddAddress);

        cityAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        city.setAdapter(cityAdapter);

        countriesList.add(0,defaultSelection);
        citiesList.add(0, defaultSelection);

        countriesList.addAll(db.countriesDao().getCountryNames());

        countryAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        country.setAdapter(countryAdapter);
        countryAdapter.addAll(countriesList);
        countryAdapter.notifyDataSetChanged();
        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCountry = parent.getItemAtPosition(position).toString();
                ArrayList<String> tempList = new ArrayList<>();
                tempList.addAll(db.citiesDao().getCitiesOfCountry(db.countriesDao().getCountryIdByName(selectedCountry)));
                for (int i=1; i < tempList.size()+1; i++) {
                    try {
                        citiesList.set(i, tempList.get(i - 1));
                    }catch (Exception e){
                        citiesList.add(i, tempList.get(i - 1));
                    }
                }
                if (tempList.size()+1 < citiesList.size()){
                    for (int j = tempList.size()+1; j < citiesList.size(); j++){
                        citiesList.remove(j);
                    }
                }
                tempList.clear();
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
            if (!selectedCity.equals(defaultSelection) && !selectedCountry.equals(defaultSelection) && !TextUtils.isEmpty(hotelName.getText().toString()) && !TextUtils.isEmpty(hotelAddress.getText().toString())){
                Hotels hotel = new Hotels();
                hotel.setNameOfHotel(hotelName.getText().toString());
                hotel.setAddressOfHotel(hotelAddress.getText().toString());
                hotel.setCountryIdOfHotel(db.countriesDao().getCountryIdByName(selectedCountry));
                hotel.setCityIdOfHotel(db.citiesDao().getCitiesIdByName(selectedCity));
                db.hotelsDao().insertHotel(hotel);
                Toast.makeText(requireActivity().getApplicationContext(),"Hotel Added",Toast.LENGTH_LONG).show();

            }else
                Toast.makeText(requireActivity().getApplicationContext(),"All fields are Required",Toast.LENGTH_LONG).show();
        });
    }
}