package ihuiee.advhci.travelody.Controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ihuiee.advhci.travelody.DB.Trips;
import ihuiee.advhci.travelody.R;

public class TripAddStep2 extends Fragment {

    Trips trip;

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


    }
}