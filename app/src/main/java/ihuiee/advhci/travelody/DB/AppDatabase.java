package ihuiee.advhci.travelody.DB;

import androidx.room.*;

@Database(entities = {Trips.class, TravelAgencies.class, Countries.class, Transportation.class, Cities.class, Hotels.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {

    public abstract TripsDAO tripsDao();

    public abstract TravelAgenciesDAO travelAgenciesDao();

    public abstract CountriesDAO countriesDao();

    public abstract TransportationDAO transportationDao();

    public abstract CitiesDAO citiesDao();

    public abstract HotelsDAO hotelsDao();
}
