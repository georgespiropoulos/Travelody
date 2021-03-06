package ihuiee.advhci.travelody.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface TravelAgenciesDAO {
    @Insert
    void insertTravelAgency(TravelAgencies travelAgency);

    @Update
    void updateTravelAgency(TravelAgencies travelAgency);

    @Delete
    void deleteTravelAgency(TravelAgencies travelAgency);

    @Query("SELECT * FROM travelAgencies")
    List<TravelAgencies> getTravelAgencies();

    @Query("SELECT travel_agency_name FROM travelAgencies")
    List<String> getTravelAgencyNames();

    @Query("SELECT travel_agency_id FROM travelAgencies WHERE travel_agency_name = :name")
    int getTravelAgencyIdByName(String name);

    @Query("SELECT * FROM travelAgencies WHERE travel_agency_name = :name")
    TravelAgencies getTravelAgencyByName(String name);

    @Query("SELECT * FROM travelAgencies WHERE travel_agency_id = :id")
    TravelAgencies getTravelAgencyById(int id);
}
