package ihuiee.advhci.travelody.DB;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "countries")
public class Countries {
    @ColumnInfo(name = "country_name") @PrimaryKey @NonNull
    public String nameOfCountry;

    public String getNameOfCountry() {
        return nameOfCountry;
    }

    public void setNameOfCountry( String nameOfCountry) {
        this.nameOfCountry = nameOfCountry;
    }

}
