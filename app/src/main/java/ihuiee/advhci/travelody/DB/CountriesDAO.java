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
}
