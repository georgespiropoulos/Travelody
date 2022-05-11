package ihuiee.advhci.travelody.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity (tableName = "travelAgencies",
        primaryKeys = {"travel_agency_name", "travel_agency_id", "travel_agency_img"})
public class TravelAgencies {
    @ColumnInfo(name = "travel_agency_name")
    public String nameOfTravelAgency;

    @ColumnInfo(name = "travel_agency_id")
    public String idOfTravelAgency;

    @ColumnInfo(name = "travel_agency_img")
    public String imgOfTravelAgency;


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


    public String getImgOfTravelAgency() {
        return imgOfTravelAgency;
    }

    public void setImgOfTravelAgency( String imgOfTravelAgency) {
        this.imgOfTravelAgency = imgOfTravelAgency;
    }
}
