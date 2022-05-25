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
import android.widget.Toast;

import java.util.ArrayList;

import ihuiee.advhci.travelody.DB.AppDatabase;
import ihuiee.advhci.travelody.DB.Trips;
import ihuiee.advhci.travelody.R;

public class TripUpdate extends Fragment {

    Spinner trip;
    Button next;
    FragmentManager fragmentManager = getFragmentManager();
    String defaultSelection = "CHOOSE";
    ArrayList<String> trips = new ArrayList<>();
    ArrayList<Integer> tripIds = new ArrayList<>();
    String selectedTrip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trip_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentManager = getParentFragmentManager();
        next=view.findViewById(R.id.Next);
        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();
        trip=view.findViewById(R.id.TripID);
        ArrayAdapter tripAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());

        tripAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        trip.setAdapter(tripAdapter);
        tripIds.addAll(db.tripsDao().getTripIds());
        for (int i=0; i<tripIds.size();i++){
            trips.add(tripIds.get(i).toString());
        }

        trips.sort(String::compareToIgnoreCase);
        trips.add(0,defaultSelection);
        tripAdapter.addAll(trips);
        tripAdapter.notifyDataSetChanged();
        trip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                selectedTrip = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        next.setOnClickListener(view1 -> {
            if (!selectedTrip.equals(defaultSelection)){
                Trips trip1 = new Trips();
                trip1.setIdOfTrip(Integer.parseInt(selectedTrip));
                try {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("trip", trip1);
                    TripUpdateChoose trip2 = new TripUpdateChoose();
                    trip2.setArguments(bundle);
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, trip2).commit();
                }catch (Exception e){
                    Toast.makeText(requireActivity().getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                }
            }else Toast.makeText(requireActivity().getApplicationContext(),"Must choose a trip ID",Toast.LENGTH_LONG).show();
        });

    }
}