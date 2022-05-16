package ihuiee.advhci.travelody.DB;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "countries")
public class Countries {
    @ColumnInfo(name = "country_name")
    public String nameOfCountry;

    @ColumnInfo(name = "country_id") @NonNull @PrimaryKey
    public String idOfCountry;

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
}
