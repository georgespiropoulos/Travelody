package ihuiee.advhci.travelody.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface CitiesDAO {
    @Insert
    void insertCity(Cities city);

    @Delete
    void deleteCity(Cities city);

    @Update
    void updateCity(Cities city);

    @Query("SELECT * FROM cities")
    List<Cities> getCities();

    @Query("SELECT city_name FROM cities")
    List<String> getCityNames();

    @Query("SELECT city_name FROM cities WHERE country_id = :countryID")
    List<String> getCitiesOfCountry(int countryID);

    @Query("SELECT city_id FROM cities WHERE city_name = :name")
    int getCitiesIdByName(String name);

    @Query("SELECT * FROM cities WHERE city_id = :id")
    Cities getCityById(int id);

}
