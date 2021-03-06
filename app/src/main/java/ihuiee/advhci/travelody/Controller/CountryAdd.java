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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ihuiee.advhci.travelody.DB.AppDatabase;
import ihuiee.advhci.travelody.DB.Countries;
import ihuiee.advhci.travelody.R;

public class CountryAdd extends Fragment {

    EditText countryName;
    Button countryAddBtn;

    public CountryAdd() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();

        countryName = view.findViewById(R.id.CountryAddEditText);
        countryAddBtn = view.findViewById(R.id.CountryAddSubmit);

        countryAddBtn.setOnClickListener(view1 -> {
            if (!TextUtils.isEmpty(countryName.getText().toString())) {
                try {
                    Countries country = new Countries();
                    country.setNameOfCountry(countryName.getText().toString());
                    db.countriesDao().insertCountry(country);
                countryName.setText("");
                    Toast.makeText(requireActivity().getApplicationContext(), "Country added", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                countryName.setText("");
                    Toast.makeText(requireActivity().getApplicationContext(), "Country already exists", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(requireActivity().getApplicationContext(), "Empty field", Toast.LENGTH_LONG).show();
            }

        });



    }
}