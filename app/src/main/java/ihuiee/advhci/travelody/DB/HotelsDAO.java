package ihuiee.advhci.travelody.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface HotelsDAO {
    @Insert
    void insertHotel(Hotels hotel);

    @Update
    void updateHotel(Hotels hotel);

    @Delete
    void deleteHotel(Hotels hotel);

    @Query("SELECT * FROM hotels")
    List<Hotels> getHotels();

    @Query("SELECT hotel_name FROM hotels")
    List<String> getHotelNames();

    @Query("SELECT hotel_name FROM hotels WHERE hotel_city_id = :cityID ")
    List<String> getHotelsOfCity(int cityID);
}
