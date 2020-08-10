package tomerbu.edu.lec7implicitintents;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_TAKE_PHOTO = 1;
    private FloatingActionButton fabHTTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabHTTP = findViewById(R.id.fabHttp);

        fabHTTP.setOnClickListener(v -> {
            showWebsite();
        });

        findViewById(R.id.fabDial).setOnClickListener(v -> dialNumber());
        findViewById(R.id.fabMap).setOnClickListener(v -> showMap());

        findViewById(R.id.fabAlarm).setOnClickListener(v -> showAlarm());

        findViewById(R.id.fabCamera).setOnClickListener(v -> takePhoto());
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivityForResult:
        startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_TAKE_PHOTO && data != null) {
            Bitmap image = data.getParcelableExtra("data");
            //Thumbnail:
            //imageView.setImageBitmap(image);
        }
    }

    private void showAlarm() {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_HOUR, 16);
        intent.putExtra(AlarmClock.EXTRA_MINUTES, 30);
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Gym practice");

        intent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        //permission:
        startActivity(intent);
    }

    private void showWebsite() {
        String address = "https://developer.android.com/reference/android/os/Parcelable";
        //String, URL, URI

        //URL = uniform resource locator = כתובת אינטרנט
        //URI = uniform resource identifier = כתובת
        Uri uri = Uri.parse(address);
        //Uri for phone, uri for phone

        Intent openWebSiteIntent = new Intent(Intent.ACTION_VIEW, uri);

        startActivity(openWebSiteIntent);
    }//M-V-VM

    private void dialNumber() {
        String phone = "tel:0507123012";
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(phone));

        //try{}catch(ActivityNotFoundException e)

        //if the intent can be resolved:
        if (dialIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(dialIntent);
        }//else {Toast, OR Google Play}
    }

    private void showMap() {
        //Intent:
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=34.99,-106.61(בית)")); //geography
        //ACTION=ACTION_VIEW
        //Uri = geo:...
        //startActivity
        //if (intent.resolveActivity(getPackageManager()) != null)
        try {//waze
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No Such app", Toast.LENGTH_SHORT).show();
        }
    }

}