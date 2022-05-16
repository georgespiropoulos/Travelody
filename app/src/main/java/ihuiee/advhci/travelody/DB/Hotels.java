package ihuiee.advhci.travelody.DB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (tableName = "hotels",
        foreignKeys = {
        @ForeignKey(entity = Countries.class,
                parentColumns = "country_name",
                childColumns = "hotel_country_name",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE),
        @ForeignKey(entity = Cities.class,
                parentColumns = "city_name",
                childColumns = "hotel_city_name",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE)})
public class Hotels {
    @ColumnInfo(name = "hotel_id") @NonNull @PrimaryKey
    public String idOfHotel;

    @ColumnInfo(name = "hotel_country_name") @NonNull
    public String countryNameOfHotel;

    @ColumnInfo(name = "hotel_city_name") @NonNull
    public String cityNameOfHotel;

    @ColumnInfo(name = "hotel_name") @NonNull
    public String nameOfHotel;

    @ColumnInfo(name = "hotel_address") @NonNull
    public String addressOfHotel;


}
