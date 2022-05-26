package ihuiee.advhci.travelody.Controller;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
    int rowIndex = -1;
    int idToDelete = -1;

    public TripDeleteAdapter(){}
    public TripDeleteAdapter(Context ct, ArrayList<String> id, ArrayList<String> city, ArrayList<String> country, ArrayList<String> hotel, ArrayList<String> transport,
                             ArrayList<String> travelAgency, ArrayList<String> date, ArrayList<String> duration, ArrayList<String> price){
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
        holder.idText.setText(id.get(position));
        holder.cityText.setText(city.get(position));
        holder.countryText.setText(country.get(position));
        holder.transportText.setText(transport.get(position));
        holder.dateText.setText(date.get(position));
        holder.durationText.setText(duration.get(position) + " DAYS");
        holder.hotelText.setText(hotel.get(position));
        holder.priceText.setText(price.get(position));
        holder.travelAgencyText.setText(travelAgency.get(position));
        holder.euroSymbol.setImageResource(R.drawable.baseline_euro_black_48);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rowIndex = holder.getAdapterPosition();
                idToDelete = Integer.parseInt(id.get(rowIndex));
                notifyDataSetChanged();
            }
        });
        if (rowIndex == position){
            holder.card.setCardBackgroundColor(Color.parseColor("#C6B497"));
        }else{
            holder.card.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView card;
        RecyclerView rView;
        TextView idText, cityText, countryText, transportText, dateText, durationText, hotelText, priceText, travelAgencyText;
        ImageView euroSymbol;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idText = itemView.findViewById(R.id.TripDeleteTripIDText);
            cityText = itemView.findViewById(R.id.tripDeleteCityName);
            countryText = itemView.findViewById(R.id.tripDeleteCountryName);
            transportText = itemView.findViewById(R.id.tripDeleteTransportName);
            dateText = itemView.findViewById(R.id.tripDeleteDate);
            durationText = itemView.findViewById(R.id.tripDeleteDuration);
            hotelText = itemView.findViewById(R.id.tripDeleteHotel);
            priceText = itemView.findViewById(R.id.tripDeletePrice);
            travelAgencyText = itemView.findViewById(R.id.tripDeleteTravelAgencyName);
            euroSymbol = itemView.findViewById(R.id.euroSymbol);
            card = itemView.findViewById(R.id.tripDeleteCard);
            rView = itemView.findViewById(R.id.TripDeleteRecyclerView);

        }
    }

    public int tripToDelete(){
        return idToDelete;
    }
}
