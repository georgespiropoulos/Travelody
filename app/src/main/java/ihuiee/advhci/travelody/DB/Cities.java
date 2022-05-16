package ihuiee.advhci.travelody.DB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "cities")
public class Cities {
    @ColumnInfo(name = "city_name") @NonNull
    public String nameOfCity;

    @ColumnInfo(name = "city_id") @NonNull @PrimaryKey
    public String idOfCity;

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

}
