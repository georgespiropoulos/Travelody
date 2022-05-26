package ihuiee.advhci.travelody.Controller;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ihuiee.advhci.travelody.R;

public class HotelResultsAdapter extends RecyclerView.Adapter<HotelResultsAdapter.ViewHolder> {

    Context context;
    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> name = new ArrayList<>();
    int rowIndex = -1;
    int hotelToSearch = -1;

    public HotelResultsAdapter() {
    }

    public HotelResultsAdapter(Context ct, ArrayList<String> id, ArrayList<String> name) {
        context = ct;
        this.id = id;
        this.name = name;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.results_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.idText.setText(id.get(position));
        holder.nameText.setText(name.get(position));

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rowIndex = holder.getAdapterPosition();
                hotelToSearch = Integer.parseInt(id.get(rowIndex));
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView idText, nameText;
        CardView card;
        RecyclerView rView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idText = itemView.findViewById(R.id.resultID);
            nameText = itemView.findViewById(R.id.resultName);
            card = itemView.findViewById(R.id.resultsCard);
            rView = itemView.findViewById(R.id.recyclerViewHotel);
        }
    }
    public int hotelToSearch(){
        return hotelToSearch;
    }
}