package ihuiee.advhci.travelody.DB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "travelAgencies")
public class TravelAgencies {
    @ColumnInfo(name = "travel_agency_name") @NonNull @PrimaryKey
    public String nameOfTravelAgency;
}
