package tomerbu.edu.lec9menusandalert;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {
    Button btnAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //xml file:
        setContentView(R.layout.activity_main);

        btnAlert = findViewById(R.id.btnAlert);

        btnAlert.setOnClickListener(v -> showOptions());
        //findViewById Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        //setup menu:
        //android 4 has Action Bars
        setSupportActionBar(toolbar);
    }

    //android Navigation: Fragments, Navigation


    private void showOptions() {
        //1) Training ->complete walk through
        //2) Guide -> After Training -> More elaborate (all the options)
        //3) Reference -> מה עושה המתודה, מה עושה הפרמטר
        //4) Material -> מסמכים למאפיינים ומעצבים

        String[] colors = {"Red", "Green", "Blue"};
        //title
        new AlertDialog
                .Builder(this)
                .setTitle("בחר צבע")
                .setItems(colors, (d, i) -> {
                    //Toast.makeText(this, colors[i], Toast.LENGTH_SHORT).show();
                    ConstraintLayout mLayout = findViewById(R.id.mLayout);
                    mLayout.setBackgroundColor(Color.RED);
                })
                .show();
    }

    private void showAlert() {
        //Title
        //Message
        //Positive Button
        //Negative Button
        //Neutral Button

        //AlertDialog -> many initialization options: Builder
        //AlertDialog alertDialog =
        new AlertDialog.Builder(this)
                .setTitle("חיבור אינטרנט לא זמין")
                .setMessage("האפליקציה דורשת חיבור לאינטרנט האם אתה מעוניין לצאת?")
                .setPositiveButton("הגדרות", (dialog, i) -> {
                    Toast.makeText(this, "Stay", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("צא", (d, i) -> {
                    //System.exit(0);
                    //finish();
                }).show();

        //alertDialog.show();


    }

    //יצירת התפריט
    //בוחרת את קובץ התפריט - מאפשר שלכל אקטיביטי יהיה תפריט משלו
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Respond to item clicks:
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_show_message:
                //view -> view that is a child of a coordinator layout...
                //some view so they can find the coordinator

                //Button btn = findViewById(R.id.btnAlert);

                //some View
                Snackbar
                        .make(btnAlert, "Call mom?", Snackbar.LENGTH_INDEFINITE)
                        .setAction("OK", v -> {
                            System.out.println("Clicked");
                        })
                        .show();//Accent color
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}