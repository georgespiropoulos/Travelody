package ihuiee.advhci.travelody.Controller;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import ihuiee.advhci.travelody.DB.AppDatabase;
import ihuiee.advhci.travelody.DB.Trips;
import ihuiee.advhci.travelody.R;

public class TripAddStep2 extends Fragment {

    Trips trip;
    Button nextButton;
    String selectedTravelAgency;
    String selectedDuration;
    FragmentManager fragmentManager = getFragmentManager();
    List<String> travelAgenciesList = new ArrayList<>();
    String defaultSelection = "CHOOSE";
    EditText dateText;
    final Calendar myCalendar= Calendar.getInstance();

    public TripAddStep2() {}



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trip_add_step2, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            trip = (Trips) getArguments().getSerializable("trip");
        }catch (Exception e){
            Toast.makeText(requireActivity().getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
        }
        dateText = view.findViewById(R.id.date);

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(),date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        fragmentManager = getParentFragmentManager();
        nextButton=view.findViewById(R.id.TripAddNextButton2);
        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();
        Spinner travelAgency=view.findViewById(R.id.TravelAgencyDropDown);
        ArrayAdapter travelAgencyAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());
        Spinner duration=view.findViewById(R.id.durationdropdown);

        travelAgenciesList.addAll(db.travelAgenciesDao().getTravelAgencyNames());
        travelAgenciesList.sort(String::compareToIgnoreCase);
        travelAgenciesList.add(0,defaultSelection);

        travelAgencyAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        travelAgency.setAdapter(travelAgencyAdapter);
        travelAgencyAdapter.addAll(travelAgenciesList);
        travelAgencyAdapter.notifyDataSetChanged();
        travelAgency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTravelAgency = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        duration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedDuration = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        nextButton.setOnClickListener(view1 -> {
            if (!selectedDuration.equals(defaultSelection) && !selectedTravelAgency.equals(defaultSelection) && !TextUtils.isEmpty(dateText.getText().toString())){
                trip.setTravelAgencyIdOfTrip(db.travelAgenciesDao().getTravelAgencyIdByName(selectedTravelAgency));
                trip.setDurationInDaysOfTrip(Integer.parseInt(selectedDuration));
                trip.setDepartureDateOfTrip(dateText.getText().toString());
                try{
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("trip", trip);
                    TripAddFinal trip3 = new TripAddFinal();
                    trip3.setArguments(bundle);
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, trip3).commit();
                }catch (Exception e){
                    Toast.makeText(requireActivity().getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                }
            }else
                Toast.makeText(requireActivity().getApplicationContext(),"All fields are Required",Toast.LENGTH_LONG).show();
        });
    }
    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        dateText.setText(dateFormat.format(myCalendar.getTime()));
    }
}