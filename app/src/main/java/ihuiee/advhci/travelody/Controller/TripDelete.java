package ihuiee.advhci.travelody.Controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Delete;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ihuiee.advhci.travelody.DB.AppDatabase;
import ihuiee.advhci.travelody.DB.Trips;
import ihuiee.advhci.travelody.R;

public class TripDelete extends Fragment {

    List<Trips> trips;
    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> city= new ArrayList<>();
    ArrayList<String> country= new ArrayList<>();
    ArrayList<String> hotel= new ArrayList<>();
    ArrayList<String> transport= new ArrayList<>();
    ArrayList<String> travelAgency= new ArrayList<>();
    ArrayList<String> date= new ArrayList<>();
    ArrayList<String> duration= new ArrayList<>();
    ArrayList<String> price= new ArrayList<>();
    RecyclerView recyclerView;
    Button deleteButton;
    FragmentManager fragmentManager = getFragmentManager();

    public TripDelete() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trip_delete, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();

        recyclerView = (RecyclerView) view.findViewById(R.id.TripDeleteRecyclerView);
        deleteButton = view.findViewById(R.id.DeleteTripButton);
        fragmentManager = getParentFragmentManager();
        trips=db.tripsDao().getTrips();

        for (int i=0; i < trips.size(); i++){
            id.add(Integer.toString(trips.get(i).idOfTrip));
            city.add(db.citiesDao().getCityById(trips.get(i).cityIdOfTrip).nameOfCity);
            country.add(db.countriesDao().getCountryById(trips.get(i).countryIdOfTrip).nameOfCountry);
            hotel.add(db.hotelsDao().getHotelById(trips.get(i).hotelIdOfTrip).nameOfHotel);
            transport.add(db.transportationDao().getTransportationNameById(trips.get(i).transportIdOfTrip));
            travelAgency.add(db.travelAgenciesDao().getTravelAgencyById(trips.get(i).travelAgencyIdOfTrip).nameOfTravelAgency);
            date.add(trips.get(i).departureDateOfTrip);
            duration.add(Integer.toString(trips.get(i).durationInDaysOfTrip));
            price.add(Float.toString(trips.get(i).priceOfTrip));
        }

        TripDeleteAdapter deleteAdapter = new TripDeleteAdapter(requireActivity().getApplicationContext(), id, city, country, hotel,
                transport, travelAgency, date, duration, price);
        recyclerView.setAdapter(deleteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = deleteAdapter.tripToDelete();
                try{
                    db.tripsDao().deleteTrip(db.tripsDao().getTripById(id));
                    Toast.makeText(getActivity().getApplicationContext(), "Trip deleted", Toast.LENGTH_LONG).show();
                }catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new TripDelete()).commit();
            }
        });
    }
}