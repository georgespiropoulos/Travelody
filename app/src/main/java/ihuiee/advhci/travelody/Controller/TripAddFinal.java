package ihuiee.advhci.travelody.Controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.text.InputType;
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
import ihuiee.advhci.travelody.DB.Trips;
import ihuiee.advhci.travelody.R;


public class TripAddFinal extends Fragment {

    Trips trip;
    EditText price;
    Button submit;
    FragmentManager fragmentManager= getFragmentManager();
    List<String> transportationList = new ArrayList<>();
    String defaultSelection = "CHOOSE";
    String selectedTransportation;

    public TripAddFinal() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trip_add_final, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            trip = (Trips) getArguments().getSerializable("trip");
        }catch (Exception e){
            Toast.makeText(requireActivity().getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
        }

        price = view.findViewById(R.id.TrilAddFinalPrice);
        fragmentManager = getParentFragmentManager();
        submit=view.findViewById(R.id.TripAddFinalSubmit);
        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();
        Spinner transportation=view.findViewById(R.id.TransportationDropDown);
        ArrayAdapter transportationAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());

        transportationList.addAll(db.transportationDao().getTransportationNames());
        transportationList.sort(String::compareToIgnoreCase);
        transportationList.add(0,defaultSelection);

        transportationAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        transportation.setAdapter(transportationAdapter);
        transportationAdapter.addAll(transportationList);
        transportationAdapter.notifyDataSetChanged();
        transportation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTransportation = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submit.setOnClickListener(view1 -> {
            if (!selectedTransportation.equals(defaultSelection) && !TextUtils.isEmpty(price.getText().toString())){
                trip.setTransportIdOfTrip(db.transportationDao().getTransportationIdByName(selectedTransportation));
                try{
                    trip.setPriceOfTrip((float) (Math.round(Float.parseFloat(price.getText().toString()) * 100.0)/100.0));
                }catch (Exception e){
                    Toast.makeText(requireActivity().getApplicationContext(),"Error on field 'Price'",Toast.LENGTH_LONG).show();
                }
                try {
                    db.tripsDao().insertTrip(trip);
                }catch (Exception e){
                    Toast.makeText(requireActivity().getApplicationContext(),"Error couldn't submit form",Toast.LENGTH_LONG).show();
                }
                Toast.makeText(requireActivity().getApplicationContext(),"Trip added",Toast.LENGTH_LONG).show();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new TripAdd()).commit();

            }else
                Toast.makeText(requireActivity().getApplicationContext(),"All fields are Required",Toast.LENGTH_LONG).show();
        });
    }
}