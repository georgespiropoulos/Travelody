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
    void insertTransportation(Transportation transportation);

    @Update
    void updateTransportation(Transportation transportation);

    @Delete
    void deleteTransportation(Transportation transportation);

    @Query("SELECT * FROM transportation")
    List<Transportation> getTransportations();
}
