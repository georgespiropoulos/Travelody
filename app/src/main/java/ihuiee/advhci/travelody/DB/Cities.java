package ihuiee.advhci.travelody.DB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "cities",
        indices = {@Index(value = {"city_name"}, unique = true)},
        foreignKeys = {
        @ForeignKey(entity = Countries.class,
        parentColumns = "country_id",
        childColumns = "country_id",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)
        })
public class Cities {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "city_id") @NonNull
    public int idOfCity;

    @ColumnInfo(name = "country_id") @NonNull
    public int idOfCountry;

    @ColumnInfo(name = "city_name") @NonNull
    public String nameOfCity;

    public int getIdOfCity() {
        return idOfCity;
    }

    public void setIdOfCity(int idOfCity) {
        this.idOfCity = idOfCity;
    }

    public int getIdOfCountry() {
        return idOfCountry;
    }

    public void setIdOfCountry(int idOfCountry) {
        this.idOfCountry = idOfCountry;
    }

    @NonNull
    public String getNameOfCity() {
        return nameOfCity;
    }

    public void setNameOfCity(@NonNull String nameOfCity) {
        this.nameOfCity = nameOfCity;
    }
}
