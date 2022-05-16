package ihuiee.advhci.travelody.DB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "travelAgencies")
public class TravelAgencies {
    @ColumnInfo(name = "travel_agency_name") @NonNull
    public String nameOfTravelAgency;

    @ColumnInfo(name = "travel_agency_id") @NonNull @PrimaryKey
    public String idOfTravelAgency;

    public String getNameOfTravelAgency() {
        return nameOfTravelAgency;
    }

    public void setNameOfTravelAgency( String nameOfTravelAgency) {
        this.nameOfTravelAgency = nameOfTravelAgency;
    }

    public String getIdOfTravelAgency() {
        return idOfTravelAgency;
    }

    public void setIdOfTravelAgency( String idOfTravelAgency) {
        this.idOfTravelAgency = idOfTravelAgency;
    }
}
