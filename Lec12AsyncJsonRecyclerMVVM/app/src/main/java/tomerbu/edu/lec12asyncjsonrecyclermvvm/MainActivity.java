package tomerbu.edu.lec12asyncjsonrecyclermvvm;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Local date Back Port
       // LocalDate now = LocalDate.now();
//        //עבור מצב של Empty Activity
//        TextView tv = findViewById(R.id.);
//
//        new MovieDataSource().fetchMovies(new MovieDataSource.Callback() {
//            @Override
//            public void moviesArrived(String json, Exception e) {
//                //אסור לעדכן ממשק משתמש מThread משני
//
//                //דרך שלישית: כבר עברנו לUI Thread בצורה של Handler
//                tv.setText(json);
//                //דרך אחת לעדכן ממשק משתמש:
//                //view.post(Runnable)
//                tv.post(() -> {
//                    tv.setText(json);
//                });
//
//                //דרך נוספת:(  )activity.runOnUIThread:
//                runOnUiThread(()->tv.setText(json));
//            }
//        });

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Person
        //new MoviesFragment().getContext(); //null
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