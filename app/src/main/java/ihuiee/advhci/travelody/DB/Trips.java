package ihuiee.advhci.travelody.DB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (tableName = "trips",
        foreignKeys = {
        @ForeignKey(entity = TravelAgencies.class,
                parentColumns = "travel_agency_id",
                childColumns = "trip_travel_agency_id",
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Countries.class,
                parentColumns = "country_id",
                childColumns = "trip_country_id",
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Cities.class,
                parentColumns = "city_id",
                childColumns = "trip_city_id",
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Transportation.class,
                parentColumns = "transport_id",
                childColumns = "trip_transport_id",
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Hotels.class,
                parentColumns = "hotel_id",
                childColumns = "trip_hotel_id",
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE)
        })
public class Trips {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "trip_id") @NonNull
    public int idOfTrip;

    @ColumnInfo(name = "trip_travel_agency_id") @NonNull
    public int travelAgencyIdOfTrip;

    @ColumnInfo(name = "trip_country_id") @NonNull
    public int countryIdOfTrip;

    @ColumnInfo(name = "trip_city_id") @NonNull
    public int cityIdOfTrip;

    @ColumnInfo(name = "trip_transport_id") @NonNull
    public int transportIdOfTrip;

    @ColumnInfo(name = "trip_hotel_id") @NonNull
    public int hotelIdOfTrip;

    @ColumnInfo(name = "trip_departure_date") @NonNull
    public String departureDateOfTrip;

    @ColumnInfo(name = "trip_duration") @NonNull
    public int durationInDaysOfTrip;

    @ColumnInfo(name = "trip_price") @NonNull
    public float priceOfTrip;

    public int getIdOfTrip() {
        return idOfTrip;
    }

    public void setIdOfTrip(int idOfTrip) {
        this.idOfTrip = idOfTrip;
    }

    public int getTravelAgencyIdOfTrip() {
        return travelAgencyIdOfTrip;
    }

    public void setTravelAgencyIdOfTrip(int travelAgencyIdOfTrip) {
        this.travelAgencyIdOfTrip = travelAgencyIdOfTrip;
    }

    public int getCountryIdOfTrip() {
        return countryIdOfTrip;
    }

    public void setCountryIdOfTrip(int countryIdOfTrip) {
        this.countryIdOfTrip = countryIdOfTrip;
    }

    public int getCityIdOfTrip() {
        return cityIdOfTrip;
    }

    public void setCityIdOfTrip(int cityIdOfTrip) {
        this.cityIdOfTrip = cityIdOfTrip;
    }

    public int getTransportIdOfTrip() {
        return transportIdOfTrip;
    }

    public void setTransportIdOfTrip(int transportIdOfTrip) {
        this.transportIdOfTrip = transportIdOfTrip;
    }

    public int getHotelIdOfTrip() {
        return hotelIdOfTrip;
    }

    public void setHotelIdOfTrip(int hotelIdOfTrip) {
        this.hotelIdOfTrip = hotelIdOfTrip;
    }

    @NonNull
    public String getDepartureDateOfTrip() {
        return departureDateOfTrip;
    }

    public void setDepartureDateOfTrip(@NonNull String departureDateOfTrip) {
        this.departureDateOfTrip = departureDateOfTrip;
    }

    public int getDurationInDaysOfTrip() {
        return durationInDaysOfTrip;
    }

    public void setDurationInDaysOfTrip(int durationInDaysOfTrip) {
        this.durationInDaysOfTrip = durationInDaysOfTrip;
    }

    public float getPriceOfTrip() {
        return priceOfTrip;
    }

    public void setPriceOfTrip(float priceOfTrip) {
        this.priceOfTrip = priceOfTrip;
    }
}
