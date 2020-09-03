package tomerbu.edu.lec19firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //מאזין שמופעל בכל פעם שמצב הלוגין השתנה - יופעל גם ברגע שיעשו לוג-אאוט. ויופעל גם ברגע שהאקטיביטי עולה.
        FirebaseAuth.getInstance().addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //אחרי כל התחברות או הרשמה מוצלחים פיירבייס שומרת עבורנו במכשיר את המשתמש האחרון ואת העובדה שהוא מחובר.
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

                if (currentUser == null) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    //flags for tasks: FLAG_ACTIVITY_CLEAR_the current TASK
                    //open the next activity in a new task: FLAG_ACTIVITY_NEW_TASK
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Welcome " + currentUser.getEmail(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //kotlin is new -> shorter -> lets us do MORE with less code.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //noinspection SwitchStatementWithTooFewBranches
        switch (item.getItemId()) {
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}