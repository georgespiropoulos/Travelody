package ihuiee.advhci.travelody.Controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import ihuiee.advhci.travelody.DB.AppDatabase;
import ihuiee.advhci.travelody.DB.Countries;
import ihuiee.advhci.travelody.DB.TravelAgencies;
import ihuiee.advhci.travelody.R;

public class TravelAgencyResults extends Fragment {

    List<TravelAgencies> travelAgencies;
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
        travelAgencies=db.travelAgenciesDao().getTravelAgencies();

        for (int i=0; i < travelAgencies.size(); i++){
            ids.add(Integer.toString(travelAgencies.get(i).idOfTravelAgency));
            names.add(travelAgencies.get(i).nameOfTravelAgency);
        }

        ResultsAdapter resultsAdapter = new ResultsAdapter(requireContext().getApplicationContext(),ids, names);
        recyclerView.setAdapter(resultsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}