package ihuiee.advhci.travelody.Controller;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import ihuiee.advhci.travelody.DB.AppDatabase;
import ihuiee.advhci.travelody.R;


public class Transactions extends Fragment {

    String selectedTrip;
    List<Integer> tripIds = new ArrayList<>();
    List<String> tripsList = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> surnames = new ArrayList<>();
    ArrayList<String> paymentMethods = new ArrayList<>();
    String defaultSelection = "CHOOSE";
    Spinner trip;
    RecyclerView recyclerView;
    FirebaseFirestore fb = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transactions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();


        recyclerView = (RecyclerView) view.findViewById(R.id.transactionsRecyclerView);
        TransactionsAdapter transactionsAdapter = new TransactionsAdapter(requireContext().getApplicationContext(), names, surnames, paymentMethods);
        recyclerView.setAdapter(transactionsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        trip=view.findViewById(R.id.TransactionsChooseTrip);
        ArrayAdapter tripAdapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new ArrayList<>());

        tripAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        trip.setAdapter(tripAdapter);
        tripIds.addAll(db.tripsDao().getTripIds());
        for (int i=0; i<tripIds.size();i++){
            tripsList.add(tripIds.get(i).toString());
        }

        tripsList.sort(String::compareToIgnoreCase);
        tripsList.add(0,defaultSelection);
        tripAdapter.addAll(tripsList);
        tripAdapter.notifyDataSetChanged();
        trip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                selectedTrip = parent.getItemAtPosition(position).toString();
                fb.collection("Transactions").document("Trips").collection(selectedTrip).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            names.clear();
                            surnames.clear();
                            paymentMethods.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                names.add(document.getData().get("Όνομα").toString());
                                surnames.add(document.getData().get("Επώνυμο").toString());
                                paymentMethods.add(document.getData().get("Τρόπος Πληρωμής").toString());
                                TransactionsAdapter transactionsAdapter = new TransactionsAdapter(requireContext().getApplicationContext(), names, surnames, paymentMethods);
                                recyclerView.setAdapter(transactionsAdapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                            }
                        }else{
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}