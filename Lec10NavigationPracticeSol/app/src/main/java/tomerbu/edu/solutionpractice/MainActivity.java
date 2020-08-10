package tomerbu.edu.solutionpractice;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.btnFirst).setOnClickListener(this);
        findViewById(R.id.btnSecond).setOnClickListener(this);
        findViewById(R.id.btnThird).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        switch (view.getId()) {
            case R.id.btnFirst:
                navController.navigate(R.id.firstFragment);
                break;
            case R.id.btnSecond:
                navController.navigate(R.id.secondFragment);
                break;
            case R.id.btnThird:
                navController.navigate(R.id.thirdFragment);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}