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
import ihuiee.advhci.travelody.DB.Countries;
import ihuiee.advhci.travelody.DB.Transportation;
import ihuiee.advhci.travelody.R;

public class TransportationDelete extends Fragment {

    Button transportationDeleteBtn;
    String selectedTransportation;
    List<String> transportationList = new ArrayList<>();
    String defaultSelection = "CHOOSE";
    Spinner transportation;

    public TransportationDelete() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transportation_delete, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();

        transportationDeleteBtn = (Button) view.findViewById(R.id.TransportationDeleteButton);
        transportation=view.findViewById(R.id.TransportationDeleteChoose);
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
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        transportationDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!selectedTransportation.equals(defaultSelection)){
                    try {
                        Transportation transportationObj = db.transportationDao().getTransportationByName(selectedTransportation);
                        db.transportationDao().deleteTransportation(transportationObj);
                        transportationAdapter.clear();
                        transportationList.clear();
                        transportationList.addAll(db.transportationDao().getTransportationNames());
                        transportationList.sort(String::compareToIgnoreCase);
                        transportationList.add(0,defaultSelection);
                        transportationAdapter.addAll(transportationList);
                        transportationAdapter.notifyDataSetChanged();
                        transportation.setSelection(0);

                        Toast.makeText(getActivity().getApplicationContext(), "Transportation deleted", Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        Toast.makeText(getActivity().getApplicationContext(), "Error"+e.toString(), Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "All fields are Required", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}