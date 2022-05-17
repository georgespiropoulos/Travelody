package ihuiee.advhci.travelody.Controller;


import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        database = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "TravelodyDB").allowMainThreadQueries().build();
        if(findViewById(R.id.fragment_container)!=null){
            if(savedInstanceState!=null){
                return;
            }
            fragmentManager.beginTransaction().add(R.id.fragment_container, new StartingFragment()).commit();
        }

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        menuBtn=(ImageButton) findViewById(R.id.buttonMenu);
        menuBtn.setOnClickListener(view -> {
            drawerLayout.openDrawer(navigationView);
        });

        navigationView.setNavigationItemSelectedListener((menuItem) -> {
            switch (menuItem.getItemId()) {
                case R.id.countryAdd: {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new CountryAdd()).commit();
                    drawerLayout.closeDrawers();
                    return true;
                }
                case R.id.cityAdd: {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new CityAdd()).commit();
                    drawerLayout.closeDrawers();
                    return true;
                }
                case R.id.hotelAdd: {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new HotelAdd()).commit();
                    drawerLayout.closeDrawers();
                    return true;
                }
                case R.id.tripAdd: {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new TripAdd()).commit();
                    drawerLayout.closeDrawers();
                    return true;
                }
                case R.id.tripSearch: {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new Search()).commit();
                    drawerLayout.closeDrawers();
                    return true;
                }
                case R.id.update: {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new TripUpdate()).commit();
                    drawerLayout.closeDrawers();
                    return true;
                }
                case R.id.delete: {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new TripDelete()).commit();
                    drawerLayout.closeDrawers();
                    return true;
                }
                case R.id.exit: {
                    drawerLayout.closeDrawers();
                    finish();
                    return true;
                }
                default:
                    return false;
            }
        });
    }

}