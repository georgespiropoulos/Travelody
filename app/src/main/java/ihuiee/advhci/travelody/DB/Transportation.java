package ihuiee.advhci.travelody.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity (tableName = "transportation",
        primaryKeys = {"transport_name", "transport_id"})
public class Transportation {
    @ColumnInfo(name = "transport_name")
    public String nameOfTransport;

    @ColumnInfo(name = "transport_id")
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
