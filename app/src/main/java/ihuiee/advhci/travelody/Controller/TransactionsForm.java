package ihuiee.advhci.travelody.Controller;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;

import ihuiee.advhci.travelody.DB.AppDatabase;
import ihuiee.advhci.travelody.R;

public class TransactionsForm extends Fragment {

    int tripId;
    EditText name;
    EditText surname;
    Button completeTransaction;
    RadioButton selectedPayment;
    RadioGroup paymentGroup;
    FirebaseFirestore fb;
    FragmentManager fragmentManager = getFragmentManager();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppDatabase db = Room.databaseBuilder(requireContext(), AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();
        fb = FirebaseFirestore.getInstance();

        try {
            tripId = getArguments().getInt("trip_id");
        }catch (Exception e){
            Toast.makeText(requireActivity().getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
        }

        String hotelName = db.hotelsDao().getHotelById(db.tripsDao().getTripById(tripId).hotelIdOfTrip).nameOfHotel.replace(" ","_");
        fragmentManager = getParentFragmentManager();
        name = view.findViewById(R.id.FormName);
        surname = view.findViewById(R.id.Surname);
        completeTransaction = view.findViewById(R.id.FormCompleteButton);
        paymentGroup = view.findViewById(R.id.PaymentMethodGroup);

        completeTransaction.setOnClickListener(view1 -> {
            selectedPayment = view.findViewById(paymentGroup.getCheckedRadioButtonId());
            if (!TextUtils.isEmpty(name.getText().toString()) && !TextUtils.isEmpty(surname.getText().toString()) && selectedPayment!=null){
                CollectionReference dbTransactions = fb.collection("Transactions/Trips/"+tripId+"_"+hotelName);

                HashMap<String, String> transaction = new HashMap<>();
                transaction.put("Όνομα", name.getText().toString());
                transaction.put("Επώνυμο", surname.getText().toString());
                transaction.put("Τρόπος Πληρωμής", selectedPayment.getText().toString());

                try {
                    dbTransactions.document(surname.getText().toString()).set(transaction).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                        }
                    });
                NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    NotificationChannel channel = new NotificationChannel("TRANSACTION_OK", "TRANSACTIONS", notificationManager.IMPORTANCE_HIGH);
                    channel.setDescription("Notifications for Transactions");
                    notificationManager.createNotificationChannel(channel);
                }
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity().getApplicationContext(), "TRANSACTION_OK")
                        .setSmallIcon(R.drawable.travelody)
                        .setContentTitle("Trip Transaction Made")
                        .setContentText(surname.getText().toString()+" "+name.getText().toString()+" BOUGHT A TRIP WITH ID: "+tripId)
                        .setAutoCancel(true);
                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this.requireContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                notificationManager.notify(0,builder.build());
                    Toast.makeText(getActivity().getApplicationContext(), "Transaction Completed", Toast.LENGTH_LONG).show();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new TripSearch()).commit();
                }catch (Exception e){
                    Toast.makeText(getActivity().getApplicationContext(), "Transaction Failed", Toast.LENGTH_LONG).show();
                }
            }else Toast.makeText(getActivity().getApplicationContext(), "All fields are required", Toast.LENGTH_LONG).show();

        });
    }

}