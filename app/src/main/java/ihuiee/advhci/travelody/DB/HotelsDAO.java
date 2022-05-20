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

    @Query("SELECT hotel_id FROM hotels WHERE hotel_name = :name ")
    int getHotelIdByHotelName(String name);

    @Query("SELECT * FROM hotels WHERE hotel_name = :hotelName AND hotel_city_id = :city_id AND hotel_country_id = :country_id ")
    Hotels getHotel(String hotelName, int city_id, int country_id);

    @Query("SELECT * FROM hotels WHERE hotel_id = :id ")
    Hotels getHotelById(int id);
}
