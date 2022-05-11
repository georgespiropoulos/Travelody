package ihuiee.advhci.travelody.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;


@Entity(tableName = "cities",
        primaryKeys = {"city_name", "city_id", "city_img"})
public class Cities {
    @ColumnInfo(name = "city_name")
    public String nameOfCity;

    @ColumnInfo(name = "city_id")
    public String idOfCity;

    @ColumnInfo(name = "city_img")
    public String imageOfCity;

    public String getNameOfCity() {
        return nameOfCity;
    }

    public void setNameOfCity(String nameOfCity) {
        this.nameOfCity = nameOfCity;
    }

    public String getIdOfCity() {
        return idOfCity;
    }

    public void setIdOfCity(String idOfCity) {
        this.idOfCity = idOfCity;
    }

    public String getImageOfCity() {
        return imageOfCity;
    }

    public void setImageOfCity(String imageOfCity) {
        this.imageOfCity = imageOfCity;
    }
}
