package ihuiee.advhci.travelody.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface TripsDAO {
    @Insert
    void insertTrip();

    @Update
    void updateTrip();

    @Delete
    void deleteTrip();

    @Query("SELECT * FROM trips")
    List<Trips> getTrips();
}
