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
    void insertHotel();

    @Update
    void updateHotel();

    @Delete
    void deleteHotel();

    @Query("SELECT * FROM hotels")
    List<Hotels> getHotels();
}
