package ihuiee.advhci.travelody.Controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ihuiee.advhci.travelody.DB.AppDatabase;
import ihuiee.advhci.travelody.DB.Countries;
import ihuiee.advhci.travelody.DB.Hotels;
import ihuiee.advhci.travelody.R;

public class HotelResults extends Fragment {

    ArrayList<Hotels> hotels;
    ArrayList<String> ids = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    RecyclerView recyclerView;
    FragmentManager fragmentManager = getFragmentManager();
    Button mapBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hotel_results, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();

        try {
            hotels = (ArrayList<Hotels>) getArguments().getSerializable("hotels");
        }catch (Exception e){
            Toast.makeText(requireActivity().getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
        }
//        System.out.println(hotels.get(0).nameOfHotel);

        mapBtn = view.findViewById(R.id.ShowOnMap);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewHotel);
        fragmentManager = getParentFragmentManager();

        for (int i=0; i < hotels.size(); i++){
            ids.add(Integer.toString(hotels.get(i).idOfHotel));
            names.add(hotels.get(i).nameOfHotel);
        }

        HotelResultsAdapter resultsAdapter = new HotelResultsAdapter(requireContext().getApplicationContext(),ids, names);
        recyclerView.setAdapter(resultsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        try {
            mapBtn.setOnClickListener(view1 -> {
                int id = resultsAdapter.hotelToSearch();
                if (id != -1) {
                    String address = db.hotelsDao().getHotelById(id).addressOfHotel;
                    String name = db.hotelsDao().getHotelById(id).nameOfHotel;
                    Geocoder geocoder = new Geocoder(getContext());
                    try {
                        ExecutorService executorService = Executors.newSingleThreadExecutor();
                        Handler handler = new Handler(Looper.getMainLooper());
                        List<Address> geoResults = geocoder.getFromLocationName(address, 1);
                        Address addr = geoResults.get(0);
                        String uri = String.format(Locale.ENGLISH, "geo:%f,%f?z=17&q=%s", addr.getLatitude(), addr.getLongitude(),Uri.encode(name), 1);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        intent.setPackage("com.google.android.apps.maps");
                        Intent chooser = Intent.createChooser(intent, "Launch Maps");

                        executorService.execute(() -> {
                            getContext().startActivity(chooser);
                            handler.post(() -> {

                            });
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else
                    Toast.makeText(requireActivity().getApplicationContext(), "Choose a hotel", Toast.LENGTH_LONG).show();
            });
        }catch (Exception e){}

    }
}