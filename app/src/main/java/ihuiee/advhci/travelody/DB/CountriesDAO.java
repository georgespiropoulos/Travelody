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
    void insertCountry();

    @Update
    void updateCountry();

    @Delete
    void deleteCountry();

    @Query("SELECT * FROM countries")
    List<Countries> getCountries();
}
