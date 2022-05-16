package ihuiee.advhci.travelody.DB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "transportation")
public class Transportation {
    @ColumnInfo(name = "transport_name") @NonNull
    public String nameOfTransport;

    @ColumnInfo(name = "transport_id") @NonNull @PrimaryKey
    public String idOfTransport;

    public String getNameOfTransport() {
        return nameOfTransport;
    }

    public void setNameOfTransport(String nameOfTransport) {
        this.nameOfTransport = nameOfTransport;
    }

    public String getIdOfTransport() {
        return idOfTransport;
    }

    public void setIdOfTransport(String idOfTransport) {
        this.idOfTransport = idOfTransport;
    }
}
