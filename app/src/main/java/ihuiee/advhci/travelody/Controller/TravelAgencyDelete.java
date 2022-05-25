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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ihuiee.advhci.travelody.DB.AppDatabase;
import ihuiee.advhci.travelody.DB.TravelAgencies;
import ihuiee.advhci.travelody.R;

public class TravelAgencyDelete extends Fragment {

    Button travelAgencyDeleteBtn;
    String selectedTravelAgency;
    List<String> travelAgencyList = new ArrayList<>();
    String defaultSelection = "CHOOSE";
    Spinner travelAgency;

    public TravelAgencyDelete() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_travel_agency_delete, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();

        travelAgencyDeleteBtn = (Button) view.findViewById(R.id.TravelAgencyDeleteButton);
        travelAgency=view.findViewById(R.id.TravelAgencyDeleteChoose);
        ArrayAdapter travelAgencyAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());


        travelAgencyList.addAll(db.travelAgenciesDao().getTravelAgencyNames());
        travelAgencyList.sort(String::compareToIgnoreCase);
        travelAgencyList.add(0,defaultSelection);

        travelAgencyAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        travelAgency.setAdapter(travelAgencyAdapter);
        travelAgencyAdapter.addAll(travelAgencyList);
        travelAgencyAdapter.notifyDataSetChanged();
        travelAgency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTravelAgency = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        travelAgencyDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!selectedTravelAgency.equals(defaultSelection)){
                    try {
                        TravelAgencies travelAgencyObj = db.travelAgenciesDao().getTravelAgencyByName(selectedTravelAgency);
                        db.travelAgenciesDao().deleteTravelAgency(travelAgencyObj);
                        travelAgencyAdapter.clear();
                        travelAgencyList.clear();
                        travelAgencyList.addAll(db.travelAgenciesDao().getTravelAgencyNames());
                        travelAgencyList.sort(String::compareToIgnoreCase);
                        travelAgencyList.add(0,defaultSelection);
                        travelAgencyAdapter.addAll(travelAgencyList);
                        travelAgencyAdapter.notifyDataSetChanged();
                        travelAgency.setSelection(0);

                        Toast.makeText(getActivity().getApplicationContext(), "Travel Agency deleted", Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "All fields are required", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}