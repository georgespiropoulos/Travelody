package ihuiee.advhci.travelody.Controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_results_universal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            hotels = (ArrayList<Hotels>) getArguments().getSerializable("hotels");
        }catch (Exception e){
            Toast.makeText(requireActivity().getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
        }
//        System.out.println(hotels.get(0).nameOfHotel);

        recyclerView = (RecyclerView) view.findViewById(R.id.resultsUniversalRView);
        fragmentManager = getParentFragmentManager();

        for (int i=0; i < hotels.size(); i++){
            ids.add(Integer.toString(hotels.get(i).idOfHotel));
            names.add(hotels.get(i).nameOfHotel);
        }

        ResultsAdapter resultsAdapter = new ResultsAdapter(requireContext().getApplicationContext(),ids, names);
        recyclerView.setAdapter(resultsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}