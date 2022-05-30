
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

public class HotelUpdate extends Fragment {

    List<String> hotelsList = new ArrayList<>();
    List<String> countriesList = new ArrayList<>();
    List<String> citiesList = new ArrayList<>();
    String selectedHotel;
    String selectedCountry;
    String selectedCity;
    String defaultSelection = "CHOOSE";
    Button submitButton;
    EditText hotelName;
    EditText hotelAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hotel_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();
        Spinner country=view.findViewById(R.id.ChooseCountryHotelUpdate);
        ArrayAdapter countryAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());
        Spinner city=view.findViewById(R.id.ChooseCityHotelUpdate);
        ArrayAdapter cityAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());
        Spinner hotel=view.findViewById(R.id.HotelUpdateChoose);
        ArrayAdapter hotelAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());
        submitButton=view.findViewById(R.id.HotelUpdateButton);
        hotelName=view.findViewById(R.id.HotelUpdateName);
        hotelAddress=view.findViewById(R.id.HotelUpdateAddressText);

        cityAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        city.setAdapter(cityAdapter);

        hotelAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        hotel.setAdapter(hotelAdapter);

        hotelsList.addAll(db.hotelsDao().getHotelNames());
        hotelsList.sort(String::compareToIgnoreCase);
        hotelsList.add(0,defaultSelection);
        hotelAdapter.addAll(hotelsList);
        hotelAdapter.notifyDataSetChanged();
        hotel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                selectedHotel = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


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
            if (!selectedHotel.equals(defaultSelection) && !TextUtils.isEmpty(hotelName.getText().toString()) && !TextUtils.isEmpty(hotelAddress.getText().toString())){
                Hotels hotel1 = new Hotels();
                hotel1.setIdOfHotel(db.hotelsDao().getHotelIdByHotelName(selectedHotel));
                hotel1.setNameOfHotel(hotelName.getText().toString());
                hotel1.setAddressOfHotel(hotelAddress.getText().toString());
                if(!selectedCountry.equals(defaultSelection) && !selectedCity.equals(defaultSelection)) {
                    hotel1.setCountryIdOfHotel(db.countriesDao().getCountryIdByName(selectedCountry));
                    hotel1.setCityIdOfHotel(db.citiesDao().getCitiesIdByName(selectedCity));
                }else if(!selectedCountry.equals(defaultSelection) && selectedCity.equals(defaultSelection)) {
                    Toast.makeText(requireActivity().getApplicationContext(),"Must choose a city",Toast.LENGTH_LONG).show();
                }else{
                    hotel1.setCountryIdOfHotel(db.hotelsDao().getHotelById(db.hotelsDao().getHotelIdByHotelName(selectedHotel)).countryIdOfHotel);
                    hotel1.setCityIdOfHotel(db.hotelsDao().getHotelById(db.hotelsDao().getHotelIdByHotelName(selectedHotel)).cityIdOfHotel);
                }
                try {
                    db.hotelsDao().updateHotel(hotel1);
                }catch (Exception e){
                    Toast.makeText(requireActivity().getApplicationContext(),"Hotel already exists",Toast.LENGTH_LONG).show();
                }
                hotelName.setText("");
                hotelAddress.setText("");
                country.setSelection(0);
                city.setSelection(0);
                Toast.makeText(requireActivity().getApplicationContext(),"Hotel updated",Toast.LENGTH_LONG).show();

            }else
                Toast.makeText(requireActivity().getApplicationContext(),"Required fields(*) cannot be empty",Toast.LENGTH_LONG).show();
        });
    }
}