package tomerbu.edu.lec6intents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //properties:
    private Button btnSend;
    private EditText etMessage;

    //counter, person... will return to the default value() unless we save them.
    //constants:
    //Avoid magic strings, magic numbers.
    public static final String EXTRA_MESSAGE = "message";//packege.activity.key
    //public static final String EXTRA_SCORE = "score";//packege.activity.key

    private static final String TAG = MainActivity.class.getSimpleName(); //"MainActivity"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this::goToNext);
        etMessage = findViewById(R.id.etMessage);

        Log.d(TAG, "onCreate: ");
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

    private void goToNext(View v) {

        //String name = etFirstName.getText().toString()
        //String lname = etFirstName.getText().toString()
        Person p1 = new Person("moshe", "doe");

        //new Parcel
//        Parcel parcel = Parcel.obtain();
//        p1.writeToParcel(parcel, 0);
//        parcel.recycle();


        //new Person
        //get the text from the editText:
        String message = etMessage.getText().toString();
        //Intent is a Message:
        //intent has a bundle...(MAP)
        Intent goNextIntent = new Intent(this, SecondActivity.class);

        goNextIntent.putExtra(EXTRA_MESSAGE, message);
        //Person is Serializable
        goNextIntent.putExtra("person", new Person("Avi", "Doe"));
        //goNextIntent.putExtra("personJsonString", new Person("Avi", "Doe").toJson());
        //goNextIntent.putExtra("userName",  "Moshe");
        //goNextIntent.putExtra("score",  20);


        //intent uses the Builder design pattern.
        //goNextIntent.putExtra("", "").putExtra("", "" )


        startActivity(goNextIntent);
    }
}
//class Intent{
//property Bundle b;
//}


//setOnClick tv button LinearLayout
//onClick(btn)


//if (v == btnSend){}
//if (v instanceOf EditText)
//switch(v.getID()))

//Explicit intent: the class that we want!

//the context serves only for id?


//context?
//Context c = this;
//which methods does context have?
//c.deleteDatabase("aa");
//c.getResources()//get Resources -> all in R!
//c.getFilesDir();// files directory:
//get app...
//each app in android has a Linux user.
//each app can see it's own files only!

//each user has his permissions.

//Toast.makeText(c, R.string.app_name, Toast.LENGTH_SHORT).show();