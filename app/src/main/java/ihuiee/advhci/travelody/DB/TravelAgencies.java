package ihuiee.advhci.travelody.DB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity (tableName = "travelAgencies",
        indices = {@Index(value = {"travel_agency_name"}, unique = true)})
public class TravelAgencies {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "travel_agency_id") @NonNull
    public int idOfTravelAgency;

    @ColumnInfo(name = "travel_agency_name") @NonNull
    public String nameOfTravelAgency;

    public int getIdOfTravelAgency() {
        return idOfTravelAgency;
    }

    public void setIdOfTravelAgency(int idOfTravelAgency) {
        this.idOfTravelAgency = idOfTravelAgency;
    }

    @NonNull
    public String getNameOfTravelAgency() {
        return nameOfTravelAgency;
    }

    public void setNameOfTravelAgency(@NonNull String nameOfTravelAgency) {
        this.nameOfTravelAgency = nameOfTravelAgency;
    }
}
