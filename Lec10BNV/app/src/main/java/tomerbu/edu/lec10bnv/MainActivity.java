package tomerbu.edu.lec10bnv;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Activity:
        //shows host (NavHostFragment)
        //BNV (3 buttons at the bottom)

        //Boiler Plate:
        //how does Activity finds nav Controller?
        //Navigation.findNavController(this, id of Nav Host)
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        //Top level Destinations(Fragments in the graph)
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

        //titles + destinations
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        //Nav Controller controls the toolbar (title)

        //BNV -> navController
        //on click on buttons -> trigger -> navContoller.navigate(R.id.frag1)
        NavigationUI.setupWithNavController(navView, navController);
    }

}