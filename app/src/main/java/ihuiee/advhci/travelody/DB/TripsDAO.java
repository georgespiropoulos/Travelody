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
    void insertTrip(Trips trip);

    @Update
    void updateTrip(Trips trip);

    @Delete
    void deleteTrip(Trips trip);

    @Query("SELECT * FROM trips")
    List<Trips> getTrips();

    @Query("SELECT * FROM trips WHERE trip_id = :id")
    Trips getTripById(int id);

    @Query("SELECT trip_id FROM trips")
    List<Integer> getTripIds();

    @Query("SELECT * FROM trips WHERE trip_country_id = :id")
    List<Trips> getTripsByCountry(int id);

    @Query("SELECT * FROM trips WHERE trip_city_id = :id")
    List<Trips> getTripsByCity(int id);

    @Query("SELECT * FROM trips WHERE trip_hotel_id = :id")
    List<Trips> getTripsByHotel(int id);



}
