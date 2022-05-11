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
    void insertTravelAgency();

    @Update
    void updateTravelAgency();

    @Delete
    void deleteTravelAgency();

    @Query("SELECT * FROM travelAgencies")
    List<TravelAgencies> getTravelAgencies();
}
