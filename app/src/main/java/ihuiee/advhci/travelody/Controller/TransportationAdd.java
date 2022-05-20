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
import ihuiee.advhci.travelody.DB.Transportation;
import ihuiee.advhci.travelody.R;

public class TransportationAdd extends Fragment {

    EditText transportationName;
    Button transportationAddBtn;

    public TransportationAdd() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transportation_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();

        transportationName = view.findViewById(R.id.TransportationAddName);
        transportationAddBtn = view.findViewById(R.id.TransportationSubmit);

        transportationAddBtn.setOnClickListener(view1 -> {
            if (!TextUtils.isEmpty(transportationName.getText().toString())) {
                try {
                    Transportation transportation = new Transportation();
                    transportation.setNameOfTransport(transportationName.getText().toString());
                    db.transportationDao().insertTransportation(transportation);
                    transportationName.setText("");
                    Toast.makeText(requireActivity().getApplicationContext(), "Transportation Added", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    transportationName.setText("");
                    Toast.makeText(requireActivity().getApplicationContext(), "Transportation Already Exist", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(requireActivity().getApplicationContext(), "Empty field", Toast.LENGTH_LONG).show();
            }

        });
    }
}