package ihuiee.advhci.travelody.DB;


import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity (tableName = "countries",
        primaryKeys = {"country_name", "country_id", "country_img"})
public class Countries {
    @ColumnInfo(name = "country_name")
    public String nameOfCountry;

    @ColumnInfo(name = "country_id")
    public String idOfCountry;

    @ColumnInfo(name = "country_img")
    public String imageOfCountry;


    public String getNameOfCountry() {
        return nameOfCountry;
    }

    public void setNameOfCountry( String nameOfCountry) {
        this.nameOfCountry = nameOfCountry;
    }


    public String getIdOfCountry() {
        return idOfCountry;
    }

    public void setIdOfCountry( String idOfCountry) {
        this.idOfCountry = idOfCountry;
    }


    public String getImageOfCountry() {
        return imageOfCountry;
    }

    public void setImageOfCountry( String imageOfCountry) {
        this.imageOfCountry = imageOfCountry;
    }
}
