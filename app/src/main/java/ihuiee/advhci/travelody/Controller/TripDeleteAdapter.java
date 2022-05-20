package ihuiee.advhci.travelody.Controller;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ihuiee.advhci.travelody.DB.Trips;
import ihuiee.advhci.travelody.R;

public class TripDeleteAdapter extends RecyclerView.Adapter<TripDeleteAdapter.ViewHolder> {

    Context context;
    ArrayList<String> id= new ArrayList<>();
    ArrayList<String> city= new ArrayList<>();
    ArrayList<String> country= new ArrayList<>();
    ArrayList<String> hotel= new ArrayList<>();
    ArrayList<String> travelAgency= new ArrayList<>();
    ArrayList<String> transport= new ArrayList<>();
    ArrayList<String> duration= new ArrayList<>();
    ArrayList<String> date= new ArrayList<>();
    ArrayList<String> price= new ArrayList<>();

    public TripDeleteAdapter(){}
    public TripDeleteAdapter(Context ct, ArrayList<String> city, ArrayList<String> country, ArrayList<String> hotel, ArrayList<String> travelAgency, ArrayList<String> transport,
                             ArrayList<String> duration, ArrayList<String> date, ArrayList<String> price, ArrayList<String> id){
        context = ct;
        this.id = id;
        this.city = city;
        this.country = country;
        this.hotel = hotel;
        this.travelAgency = travelAgency;
        this.transport = transport;
        this.duration = duration;
        this.date = date;
        this.price = price;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.trip_delete_recycler_view_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.id.setText(id.get(position));
        holder.city.setText(city.get(position));
        holder.country.setText(country.get(position));
//        holder.transport.setText(transport.get(position));
        holder.date.setText(date.get(position));
        holder.duration.setText(duration.get(position) + "DAYS");
        holder.hotel.setText(hotel.get(position));
        holder.price.setText(price.get(position));
        holder.travelAgency.setText(travelAgency.get(position));
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView id, city, country, transport, date, duration, hotel, price, travelAgency;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.TripDeleteTripIDText);
            city = itemView.findViewById(R.id.tripDeleteCityName);
            country = itemView.findViewById(R.id.tripDeleteCountryName);
            transport = itemView.findViewById(R.id.tripDeleteTransportName);
            date = itemView.findViewById(R.id.tripDeleteDate);
            duration = itemView.findViewById(R.id.tripDeleteDuration);
            hotel = itemView.findViewById(R.id.tripDeleteHotel);
            price = itemView.findViewById(R.id.tripDeletePrice);
            travelAgency = itemView.findViewById(R.id.tripDeleteTravelAgencyName);

        }
    }
}
