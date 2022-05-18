package ihuiee.advhci.travelody.DB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity (tableName = "hotels",
        foreignKeys = {
        @ForeignKey(entity = Countries.class,
                parentColumns = "country_id",
                childColumns = "hotel_country_id",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE),
        @ForeignKey(entity = Cities.class,
                parentColumns = "city_id",
                childColumns = "hotel_city_id",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE)})
public class Hotels {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "hotel_id") @NonNull
    public int idOfHotel;

    @ColumnInfo(name = "hotel_country_id") @NonNull
    public int countryIdOfHotel;

    @ColumnInfo(name = "hotel_city_id") @NonNull
    public int cityIdOfHotel;

    @ColumnInfo(name = "hotel_name") @NonNull
    public String nameOfHotel;

    @ColumnInfo(name = "hotel_address") @NonNull
    public String addressOfHotel;

    public int getIdOfHotel() {
        return idOfHotel;
    }

    public void setIdOfHotel(int idOfHotel) {
        this.idOfHotel = idOfHotel;
    }

    public int getCountryIdOfHotel() {
        return countryIdOfHotel;
    }

    public void setCountryIdOfHotel(int countryIdOfHotel) {
        this.countryIdOfHotel = countryIdOfHotel;
    }

    public int getCityIdOfHotel() {
        return cityIdOfHotel;
    }

    public void setCityIdOfHotel(int cityIdOfHotel) {
        this.cityIdOfHotel = cityIdOfHotel;
    }

    @NonNull
    public String getNameOfHotel() {
        return nameOfHotel;
    }

    public void setNameOfHotel(@NonNull String nameOfHotel) {
        this.nameOfHotel = nameOfHotel;
    }

    @NonNull
    public String getAddressOfHotel() {
        return addressOfHotel;
    }

    public void setAddressOfHotel(@NonNull String addressOfHotel) {
        this.addressOfHotel = addressOfHotel;
    }
}
