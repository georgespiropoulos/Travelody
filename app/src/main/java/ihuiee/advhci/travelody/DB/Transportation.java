package ihuiee.advhci.travelody.DB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "transportation")
public class Transportation {
    @ColumnInfo(name = "transport_name") @NonNull @PrimaryKey
    public String nameOfTransport;
}
