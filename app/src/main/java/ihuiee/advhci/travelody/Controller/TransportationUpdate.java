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
import ihuiee.advhci.travelody.DB.Transportation;
import ihuiee.advhci.travelody.R;

public class TransportationUpdate extends Fragment {

    EditText transportationName;
    Button transportationUpdateBtn;
    String selectedTransportation;
    List<String> transportationList = new ArrayList<>();
    String defaultSelection = "CHOOSE";
    Spinner transportation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transportation_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();

        transportationName = view.findViewById(R.id.TransportationUpdateName);
        transportationUpdateBtn = view.findViewById(R.id.TransportationUpdateButton);
        transportation=view.findViewById(R.id.TransportationUpdateChoose);
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

        transportationUpdateBtn.setOnClickListener(view1 -> {
            if (!TextUtils.isEmpty(transportationName.getText().toString()) && !selectedTransportation.equals(defaultSelection)) {
                try {
                    Transportation transportation1 = new Transportation();
                    transportation1.setNameOfTransport(transportationName.getText().toString());
                    transportation1.setIdOfTransport(db.transportationDao().getTransportationIdByName(selectedTransportation));
                    db.transportationDao().updateTransportation(transportation1);
                    transportationName.setText("");
                    transportationAdapter.clear();
                    transportationList.clear();
                    transportationList.addAll(db.transportationDao().getTransportationNames());
                    transportationList.sort(String::compareToIgnoreCase);
                    transportationList.add(0,defaultSelection);
                    transportationAdapter.addAll(transportationList);
                    transportationAdapter.notifyDataSetChanged();
                    transportation.setSelection(0);
                    Toast.makeText(requireActivity().getApplicationContext(), "Transportation updated", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    transportationName.setText("");
                    Toast.makeText(requireActivity().getApplicationContext(), "Transportation already exists", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(requireActivity().getApplicationContext(), "Empty field", Toast.LENGTH_LONG).show();
            }

        });
    }
}