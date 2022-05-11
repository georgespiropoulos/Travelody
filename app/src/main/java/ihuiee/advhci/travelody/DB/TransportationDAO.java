package ihuiee.advhci.travelody.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface TransportationDAO {
    @Insert
    void insertTransportation();

    @Update
    void updateTransportation();

    @Delete
    void deleteTransportation();

    @Query("SELECT * FROM transportation")
    List<Transportation> getTransportations();
}
