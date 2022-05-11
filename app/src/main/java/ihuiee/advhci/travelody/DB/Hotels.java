package ihuiee.advhci.travelody.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity (tableName = "hotels",
        primaryKeys = {"hotel_id", "hotel_name", "hotel_address"},
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
    @ColumnInfo(name = "hotel_id")
    public String idOfHotel;

    @ColumnInfo(name = "hotel_country_id")
    public String countryIdOfHotel;

    @ColumnInfo(name = "hotel_city_id")
    public String cityIdOfHotel;

    @ColumnInfo(name = "hotel_name")
    public String nameOfHotel;

    @ColumnInfo(name = "hotel_address")
    public String addressOfHotel;

    public String getIdOfHotel() {
        return idOfHotel;
    }

    public void setIdOfHotel(String idOfHotel) {
        this.idOfHotel = idOfHotel;
    }

    public String getCountryIdOfHotel() {
        return countryIdOfHotel;
    }

    public void setCountryIdOfHotel(String countryIdOfHotel) {
        this.countryIdOfHotel = countryIdOfHotel;
    }

    public String getCityIdOfHotel() {
        return cityIdOfHotel;
    }

    public void setCityIdOfHotel(String cityIdOfHotel) {
        this.cityIdOfHotel = cityIdOfHotel;
    }

    public String getNameOfHotel() {
        return nameOfHotel;
    }

    public void setNameOfHotel(String nameOfHotel) {
        this.nameOfHotel = nameOfHotel;
    }

    public String getAddressOfHotel() {
        return addressOfHotel;
    }

    public void setAddressOfHotel(String addressOfHotel) {
        this.addressOfHotel = addressOfHotel;
    }
}
