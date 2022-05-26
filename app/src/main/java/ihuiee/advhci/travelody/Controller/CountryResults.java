package ihuiee.advhci.travelody.Controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import ihuiee.advhci.travelody.DB.AppDatabase;
import ihuiee.advhci.travelody.DB.Countries;
import ihuiee.advhci.travelody.DB.Trips;
import ihuiee.advhci.travelody.R;

public class CountryResults extends Fragment {

    List<Countries> countries;
    ArrayList<String> ids = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    RecyclerView recyclerView;
    FragmentManager fragmentManager = getFragmentManager();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_results_universal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();

        recyclerView = (RecyclerView) view.findViewById(R.id.resultsUniversalRView);
        fragmentManager = getParentFragmentManager();
        countries=db.countriesDao().getCountries();

        for (int i=0; i < countries.size(); i++){
            ids.add(Integer.toString(countries.get(i).idOfCountry));
            names.add(countries.get(i).nameOfCountry);
        }

        ResultsAdapter resultsAdapter = new ResultsAdapter(requireContext().getApplicationContext(),ids, names);
        recyclerView.setAdapter(resultsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}