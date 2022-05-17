package ihuiee.advhci.travelody.DB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity (tableName = "transportation",
        indices = {@Index(value = {"transport_name"}, unique = true)})
public class Transportation {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transport_id") @NonNull
    public int idOfTransport;

    @ColumnInfo(name = "transport_name") @NonNull
    public String nameOfTransport;

    public int getIdOfTransport() {
        return idOfTransport;
    }

    public void setIdOfTransport(int idOfTransport) {
        this.idOfTransport = idOfTransport;
    }

    @NonNull
    public String getNameOfTransport() {
        return nameOfTransport;
    }

    public void setNameOfTransport(@NonNull String nameOfTransport) {
        this.nameOfTransport = nameOfTransport;
    }
}
