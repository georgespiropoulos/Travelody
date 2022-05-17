package ihuiee.advhci.travelody.DB;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity (tableName = "countries",
        indices = {@Index(value = {"country_name"}, unique = true)})
public class Countries {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "country_id")
    public int idOfCountry;

    @ColumnInfo(name = "country_name") @NonNull
    public String nameOfCountry;

    public int getIdOfCountry() {
        return idOfCountry;
    }

    public void setIdOfCountry(int idOfCountry) {
        this.idOfCountry = idOfCountry;
    }

    @NonNull
    public String getNameOfCountry() {
        return nameOfCountry;
    }

    public void setNameOfCountry(@NonNull String nameOfCountry) {
        this.nameOfCountry = nameOfCountry;
    }
}
