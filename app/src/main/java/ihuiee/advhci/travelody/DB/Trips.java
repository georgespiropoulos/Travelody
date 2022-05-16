package ihuiee.advhci.travelody.DB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (tableName = "trips",
        foreignKeys = {
        @ForeignKey(entity = TravelAgencies.class,
                parentColumns = "travel_agency_name",
                childColumns = "trip_travel_agency_name",
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Countries.class,
                parentColumns = "country_name",
                childColumns = "trip_country_name",
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Cities.class,
                parentColumns = "city_name",
                childColumns = "trip_city_name",
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Transportation.class,
                parentColumns = "transport_name",
                childColumns = "trip_transport_name",
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Hotels.class,
                parentColumns = "hotel_name",
                childColumns = "trip_hotel_name",
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE)
        })
public class Trips {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "trip_id") @NonNull
    public int idOfTrip;

    @ColumnInfo(name = "trip_travel_agency_name") @NonNull
    public String travelAgencyIdOfTrip;

    @ColumnInfo(name = "trip_country_name") @NonNull
    public String countryIdOfTrip;

    @ColumnInfo(name = "trip_city_name") @NonNull
    public String cityIdOfTrip;

    @ColumnInfo(name = "trip_transport_name") @NonNull
    public String transportIdOfTrip;

    @ColumnInfo(name = "trip_hotel_name") @NonNull
    public String hotelIdOfTrip;

    @ColumnInfo(name = "trip_departure_date") @NonNull
    public String departureDateOfTrip;

    @ColumnInfo(name = "trip_duration") @NonNull
    public String durationInDaysOfTrip;

    @ColumnInfo(name = "trip_price") @NonNull
    public String priceOfTrip;

}
