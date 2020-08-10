package tomerbu.edu;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//supports old versions of android.

//Screen # 1

//Activity extends Context
//Context has access to our res
public class MainActivity extends AppCompatActivity {
    //properties
    int mCount = 0;
    TextView tvCounter;

    //Button, TextView Controller

    //android calls this method: after creating the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1) choose my xml file
        setContentView(R.layout.activity_main);
        //start code...
        //set values to my properties

        //2) find the text view from the xml?
        tvCounter = findViewById(R.id.textView_count);
    }

    public void showToast(View view) {
        //show a Toast Message
        Toast message = Toast.makeText(this,
                R.string.toast_message, Toast.LENGTH_SHORT);

        //standard
        message.setGravity(Gravity.CENTER, 0, 0);

        message.show();
    }

    //counter value:
    public void countUp(View view) {
        mCount++;
        //change the value of the textView?
//        TextView tvCounter = findViewById(R.id.textView_count);
        tvCounter.setText(String.valueOf(mCount));
        //..
    }
}