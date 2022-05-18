package ihuiee.advhci.travelody.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface CountriesDAO {
    @Insert
    void insertCountry(Countries country);

    @Update
    void updateCountry(Countries country);

    @Delete
    void deleteCountry(Countries country);

    @Query("SELECT * FROM countries")
    List<Countries> getCountries();

    @Query("SELECT country_name FROM countries")
    List<String> getCountryNames();

    @Query("SELECT country_id FROM countries WHERE country_name = :name")
    int getCountryIdByName(String name);
}
