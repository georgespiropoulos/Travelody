package ihuiee.advhci.travelody.Controller;


import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;
import android.os.Bundle;
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

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener((menuItem) -> {
            switch (menuItem.getItemId()) {
                case R.id.add: {
                    displayMessage("Add clicked");
                    drawerLayout.closeDrawers();
                    return true;
                }
                case R.id.search: {
                    displayMessage("Search clicked");
                    drawerLayout.closeDrawers();
                    return true;
                }
                case R.id.exit: {
                    displayMessage("Exit clicked");
                    drawerLayout.closeDrawers();
                    finish();
                    return true;
                }
                default:
                    return false;
            }
        });
    }

    void displayMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}