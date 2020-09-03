package tomerbu.edu.lec16use_fragemnt;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //insert a fragment to our container from code.

        //FragmentManager -> אובייקט שמנהל את הפרגמנטים עבור אקטיביטי
        //FragmentManager fragmentManager = getSupportFragmentManager();

        //תפקידים עיקריים:
        //find Fragment by id (XML)
        //find Fragment by tag (Code)
        //fragment  transactions


        //add can happen twice.
        //remove if exists.
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container1, new BlankFragment())
                .commit();

    }
}