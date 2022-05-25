package ihuiee.advhci.travelody.Controller;

import android.app.DatePickerDialog;
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

import com.google.android.gms.common.internal.Objects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import ihuiee.advhci.travelody.DB.AppDatabase;
import ihuiee.advhci.travelody.DB.Trips;
import ihuiee.advhci.travelody.R;

public class TripUpdateChooseFinal extends Fragment {

    Trips trip;
    EditText price;
    EditText dateText;
    final Calendar myCalendar= Calendar.getInstance();
    Button submit;
    FragmentManager fragmentManager= getFragmentManager();
    List<String> transportationList = new ArrayList<>();
    String defaultSelection = "CHOOSE";
    String selectedTransportation;
    String selectedDuration;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trip_update_choose_final, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            trip = (Trips) getArguments().getSerializable("trip");
        }catch (Exception e){
            Toast.makeText(requireActivity().getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
        }

        price = view.findViewById(R.id.tripUpdatePrice);
        fragmentManager = getParentFragmentManager();
        submit=view.findViewById(R.id.tripUpdateButton);
        dateText = view.findViewById(R.id.tripUpdateDate);
        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();
        Spinner transportation=view.findViewById(R.id.transportUpdate);
        ArrayAdapter transportationAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());
        Spinner duration=view.findViewById(R.id.durationUpdate);

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

        duration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDuration = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submit.setOnClickListener(view1 -> {
            if (!selectedTransportation.equals(defaultSelection) && !TextUtils.isEmpty(price.getText().toString()) && !TextUtils.isEmpty(dateText.getText().toString()) && !selectedDuration.equals(defaultSelection)) {
                trip.setTransportIdOfTrip(db.transportationDao().getTransportationIdByName(selectedTransportation));
                try {
                    trip.setPriceOfTrip((float) (Math.round(Float.parseFloat(price.getText().toString()) * 100.0) / 100.0));
                } catch (Exception e) {
                    Toast.makeText(requireActivity().getApplicationContext(), "Error on field 'Price'", Toast.LENGTH_LONG).show();
                }
                trip.setDurationInDaysOfTrip(Integer.parseInt(selectedDuration));
                trip.setDepartureDateOfTrip(dateText.getText().toString());
            }else if(!selectedTransportation.equals(defaultSelection) || !TextUtils.isEmpty(price.getText().toString()) || !TextUtils.isEmpty(dateText.getText().toString()) || !selectedDuration.equals(defaultSelection)){
                if (!selectedDuration.equals(defaultSelection)) trip.setDurationInDaysOfTrip(Integer.parseInt(selectedDuration));
                if (!selectedTransportation.equals(defaultSelection)) trip.setTransportIdOfTrip(db.transportationDao().getTransportationIdByName(selectedTransportation));
                if (!TextUtils.isEmpty(price.getText().toString())) trip.setPriceOfTrip((float) (Math.round(Float.parseFloat(price.getText().toString()) * 100.0) / 100.0));
                if (!TextUtils.isEmpty(dateText.getText().toString())) trip.setDepartureDateOfTrip(dateText.getText().toString());
                if (selectedDuration.equals(defaultSelection)) trip.setDurationInDaysOfTrip(db.tripsDao().getTripById(trip.idOfTrip).durationInDaysOfTrip);
                if (selectedTransportation.equals(defaultSelection)) trip.setTransportIdOfTrip(db.tripsDao().getTripById(trip.idOfTrip).transportIdOfTrip);
                if (TextUtils.isEmpty(price.getText().toString())) trip.setPriceOfTrip(db.tripsDao().getTripById(trip.idOfTrip).priceOfTrip);
                if (TextUtils.isEmpty(dateText.getText().toString())) trip.setDepartureDateOfTrip(db.tripsDao().getTripById(trip.idOfTrip).departureDateOfTrip);
            }else{
                trip.setDurationInDaysOfTrip(db.tripsDao().getTripById(trip.idOfTrip).durationInDaysOfTrip);
                trip.setTransportIdOfTrip(db.tripsDao().getTripById(trip.idOfTrip).transportIdOfTrip);
                trip.setPriceOfTrip(db.tripsDao().getTripById(trip.idOfTrip).priceOfTrip);
                trip.setDepartureDateOfTrip(db.tripsDao().getTripById(trip.idOfTrip).departureDateOfTrip);
            }
//            System.out.println(trip.idOfTrip+", "+trip.travelAgencyIdOfTrip+", "+trip.countryIdOfTrip+", "+trip.cityIdOfTrip+", "+trip.transportIdOfTrip+", "+trip.hotelIdOfTrip+", "+trip.departureDateOfTrip+", "+trip.durationInDaysOfTrip+", "+trip.priceOfTrip);
            try {
                db.tripsDao().updateTrip(trip);
                Toast.makeText(requireActivity().getApplicationContext(), "Trip updated", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                System.out.println(e);
                Toast.makeText(requireActivity().getApplicationContext(), "Error couldn't update trip", Toast.LENGTH_LONG).show();
            }
            fragmentManager.beginTransaction().replace(R.id.fragment_container, new TripUpdate()).commit();

        });
    }

    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        dateText.setText(dateFormat.format(myCalendar.getTime()));
    }
}