package ihuiee.advhci.travelody.Controller;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

import ihuiee.advhci.travelody.DB.AppDatabase;
import ihuiee.advhci.travelody.R;

public class MainActivity extends AppCompatActivity {
    public static FragmentManager fragmentManager;
    public static AppDatabase database;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton menuBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        database = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();
        if(findViewById(R.id.activity_fragment_container)!=null){
            if(savedInstanceState!=null){
                return;
            }
            fragmentManager.beginTransaction().replace(R.id.activity_fragment_container, new MainFragment()).commit();
        }

//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        drawerLayout = findViewById(R.id.drawer_layout);
//        navigationView = findViewById(R.id.navigationView);
//
//
//        menuBtn=(ImageButton) findViewById(R.id.buttonMenu);
//        menuBtn.setOnClickListener(view -> {
//            drawerLayout.openDrawer(navigationView);
//        });
//
//        navigationView.setNavigationItemSelectedListener((menuItem) -> {
//            switch (menuItem.getItemId()) {
//                case R.id.home: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new StartingFragment()).commit();
//                    drawerLayout.closeDrawers();
//                    System.out.println("Clicked");
//                    return true;
//                }
//                case R.id.cityAdd: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new CityAdd()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.countryAdd: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new CountryAdd()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.hotelAdd: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new HotelAdd()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.transportationAdd: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new TransportationAdd()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.travelAgencyAdd: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new TravelAgencyAdd()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.tripAdd: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new TripAdd()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.citySearch: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new CitySearch()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.countrySearch: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new CountryResults()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.hotelSearch: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new HotelSearch()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.transportationSearch: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new TransportationResults()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.travelAgencySearch: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new TravelAgencyResults()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.tripSearch: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new TripSearch()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.cityUpdate: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new CityUpdate()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.countryUpdate: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new CountryUpdate()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.hotelUpdate: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new HotelUpdate()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.transportationUpdate: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new TransportationUpdate()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.travelAgencyUpdate: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new TravelAgencyUpdate()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.tripUpdate: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new TripUpdate()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.cityDelete: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new CityDelete()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.countryDelete: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new CountryDelete()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.hotelDelete: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new HotelDelete()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.transportationDelete: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new TransportationDelete()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.travelAgencyDelete: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new TravelAgencyDelete()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.tripDelete: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new TripDelete()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.seeTransactions: {
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new Transactions()).commit();
//                    drawerLayout.closeDrawers();
//                    return true;
//                }
//                case R.id.exit: {
//                    drawerLayout.closeDrawers();
//                    finish();
//                    return true;
//                }
//                default:
//                    return false;
//            }
//        });
    }
}