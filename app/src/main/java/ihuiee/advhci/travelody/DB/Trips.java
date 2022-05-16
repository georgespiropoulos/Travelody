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
    public String travelAgencyIdOfTrip;

    @ColumnInfo(name = "trip_country_id") @NonNull
    public String countryIdOfTrip;

    @ColumnInfo(name = "trip_city_id") @NonNull
    public String cityIdOfTrip;

    @ColumnInfo(name = "trip_transport_id") @NonNull
    public String transportIdOfTrip;

    @ColumnInfo(name = "trip_hotel_id") @NonNull
    public String hotelIdOfTrip;

    @ColumnInfo(name = "trip_departure_date") @NonNull
    public String departureDateOfTrip;

    @ColumnInfo(name = "trip_duration") @NonNull
    public String durationInDaysOfTrip;

    @ColumnInfo(name = "trip_price") @NonNull
    public String priceOfTrip;

    public int getIdOfTrip() {
        return idOfTrip;
    }


    public String getTravelAgencyIdOfTrip() {
        return travelAgencyIdOfTrip;
    }

    public void setTravelAgencyIdOfTrip( String travelAgencyIdOfTrip) {
        this.travelAgencyIdOfTrip = travelAgencyIdOfTrip;
    }


    public String getCountryIdOfTrip() {
        return countryIdOfTrip;
    }

    public void setCountryIdOfTrip( String countryIdOfTrip) {
        this.countryIdOfTrip = countryIdOfTrip;
    }


    public String getCityIdOfTrip() {
        return cityIdOfTrip;
    }

    public void setCityIdOfTrip( String cityIdOfTrip) {
        this.cityIdOfTrip = cityIdOfTrip;
    }


    public String getTransportIdOfTrip() {
        return transportIdOfTrip;
    }

    public void setTransportIdOfTrip( String transportIdOfTrip) {
        this.transportIdOfTrip = transportIdOfTrip;
    }


    public String getHotelIdOfTrip() {
        return hotelIdOfTrip;
    }

    public void setHotelIdOfTrip( String hotelIdOfTrip) {
        this.hotelIdOfTrip = hotelIdOfTrip;
    }


    public String getDepartureDateOfTrip() {
        return departureDateOfTrip;
    }

    public void setDepartureDateOfTrip( String departureDateOfTrip) {
        this.departureDateOfTrip = departureDateOfTrip;
    }


    public String getDurationInDaysOfTrip() {
        return durationInDaysOfTrip;
    }

    public void setDurationInDaysOfTrip( String durationInDaysOfTrip) {
        this.durationInDaysOfTrip = durationInDaysOfTrip;
    }


    public String getPriceOfTrip() {
        return priceOfTrip;
    }

    public void setPriceOfTrip( String priceOfTrip) {
        this.priceOfTrip = priceOfTrip;
    }
}
