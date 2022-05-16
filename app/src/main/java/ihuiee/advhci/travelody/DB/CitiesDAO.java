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

}
