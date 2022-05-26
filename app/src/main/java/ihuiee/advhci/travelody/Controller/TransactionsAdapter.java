package ihuiee.advhci.travelody.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ihuiee.advhci.travelody.R;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.ViewHolder> {

    Context context;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> surname = new ArrayList<>();
    ArrayList<String> paymentMethod = new ArrayList<>();

    public TransactionsAdapter() {
    }

    public TransactionsAdapter(Context ct, ArrayList<String> name, ArrayList<String> surnname, ArrayList<String> paymentMethod) {
        context = ct;
        this.name = name;
        this.surname = surnname;
        this.paymentMethod = paymentMethod;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.transaction_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameText.setText(name.get(position)+" "+surname.get(position));
        holder.paymentText.setText(paymentMethod.get(position));


    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView paymentText, nameText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            paymentText = itemView.findViewById(R.id.paymentMethodtoDisplay);
            nameText = itemView.findViewById(R.id.customerName);
        }
    }
}