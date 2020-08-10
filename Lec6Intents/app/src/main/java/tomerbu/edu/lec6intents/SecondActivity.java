package tomerbu.edu.lec6intents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    //properties:
    private TextView tvMessage;
    private Button btnDone;

    //counter
    //person
    //brain

    private static final String TAG = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //System.exit(0);

        Log.d(TAG, "onCreate: ");

        tvMessage = findViewById(R.id.tvMessage);
        btnDone = findViewById(R.id.btnDone);


        //get the intent that created this activity:
        Intent mIntent = getIntent();
        //        mIntent.getStringExtra("message");
        String message = mIntent.getStringExtra(MainActivity.EXTRA_MESSAGE);//MainActivity.EXTRA_MESSAGE


        Person p = mIntent.getParcelableExtra("person");



        //message may be null:
        if (message == null ) {
            return;
        }

        //Objects.requireNonNull(null);
        //message..
        tvMessage.setText(message);

        //get the data from the intent.
        //if there is data? -> tvMessage


        btnDone.setOnClickListener(v -> {
            //finish kills the current activity.
            finish();
        });
    }





    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }


}