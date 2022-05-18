package ihuiee.advhci.travelody.Controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ihuiee.advhci.travelody.DB.AppDatabase;
import ihuiee.advhci.travelody.R;

public class CityAdd extends Fragment {

    Button cityAddBtn;
    EditText cityName;
    String selectedCountry;
    List<String> countriesList = new ArrayList<>();
    String defaultSelection = "CHOOSE";

    public CityAdd() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();

        cityAddBtn = (Button) view.findViewById(R.id.CityAddSubmit);
        cityName = (EditText) view.findViewById(R.id.CityAddEditText);
        Spinner country=view.findViewById(R.id.CityAddChooseCountry);
        ArrayAdapter countryAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());

        countriesList.add(0,defaultSelection);
        countriesList.addAll(db.countriesDao().getCountryNames());

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



    }
}