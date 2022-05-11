//package ihuiee.advhci.travelody;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.ArrayList;
//
//public class TrendingCardAdapter extends RecyclerView.Adapter<TrendingCardAdapter.MyViewHolder> {
//
//    private ArrayList<TrendingCard> dataSet;
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//
//        TextView Price;
//        TextView PriceLabel;
//        TextView City;
//        TextView CityLabel;
//        TextView DeptDate;
//        TextView DeptDateLabel;
//        ImageView CityPhoto;
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//            this.Price = (TextView) itemView.findViewById(R.id.textViewName);
//            this.PriceLabel = (TextView) itemView.findViewById(R.id.textViewName);
//            this.City = (TextView) itemView.findViewById(R.id.textViewName);
//            this.CityLabel = (TextView) itemView.findViewById(R.id.textViewName);
//            this.DeptDate = (TextView) itemView.findViewById(R.id.textViewName);
//            this.DeptDateLabel = (TextView) itemView.findViewById(R.id.textViewName);
//            this.CityPhoto = (ImageView) itemView.findViewById(R.id.imageView);
//        }
//    }
//
//    public TrendingCardAdapter(ArrayList<TrendingCard> data) {
//        this.dataSet = data;
//    }
//
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent,
//                                           int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.cards_layout, parent, false);
//
//        view.setOnClickListener(MainActivity.myOnClickListener);
//
//        MyViewHolder myViewHolder = new MyViewHolder(view);
//        return myViewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
//
//        TextView textViewName = holder.textViewName;
//        TextView textViewVersion = holder.textViewVersion;
//        ImageView imageView = holder.imageViewIcon;
//
//        textViewName.setText(dataSet.get(listPosition).getName());
//        textViewVersion.setText(dataSet.get(listPosition).getVersion());
//        imageView.setImageResource(dataSet.get(listPosition).getImage());
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataSet.size();
//    }
//}