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
import ihuiee.advhci.travelody.DB.TravelAgencies;
import ihuiee.advhci.travelody.R;

public class TravelAgencyAdd extends Fragment {

    EditText travelAgencyName;
    Button travelAgencyAddBtn;

    public TravelAgencyAdd() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_travel_agency_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();

        travelAgencyName = view.findViewById(R.id.TravelAgencyName);
        travelAgencyAddBtn = view.findViewById(R.id.TravelAgencySubmit);

        travelAgencyAddBtn.setOnClickListener(view1 -> {
            if (!TextUtils.isEmpty(travelAgencyName.getText().toString())) {
                try {
                    TravelAgencies travelAgency = new TravelAgencies();
                    travelAgency.setNameOfTravelAgency(travelAgencyName.getText().toString());
                    db.travelAgenciesDao().insertTravelAgency(travelAgency);
                    travelAgencyName.setText("");
                    Toast.makeText(requireActivity().getApplicationContext(), "Travel Agency Added", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    travelAgencyName.setText("");
                    Toast.makeText(requireActivity().getApplicationContext(), "Travel Agency Already Exist", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(requireActivity().getApplicationContext(), "Empty field", Toast.LENGTH_LONG).show();
            }

        });
    }

}