package ihuiee.advhci.travelody.Controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import ihuiee.advhci.travelody.R;

public class CitySearch extends Fragment {

    Button nextButton;
    String selectedCountry;
    List<String> countriesList = new ArrayList<>();
    String defaultSelection = "CHOOSE";
    FragmentManager fragmentManager = getFragmentManager();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();

        fragmentManager = getParentFragmentManager();
        nextButton = (Button) view.findViewById(R.id.CitySearchButton);
        Spinner country=view.findViewById(R.id.CitySearchChooseCountry);
        ArrayAdapter countryAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());


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
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!selectedCountry.equals(defaultSelection)){
                    ArrayList<Cities> cities = (ArrayList<Cities>) db.citiesDao().getCitiesOfACountry(db.countriesDao().getCountryIdByName(selectedCountry));
                    try {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("cities", cities);
                        CityResults results = new CityResults();
                        results.setArguments(bundle);
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, results).commit();
                    }catch (Exception e){
                        country.setSelection(0);
                    }

                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "Choose a Country", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}