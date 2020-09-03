package tomerbu.edu.lec16location;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;

import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Service is an android component: Activity - UI
 * Service - NO UI - runs in the background
 * <p>
 * <p>
 * Service may serve our activities.
 * <p>
 * Intent Service -> Background Thread -> How to communicate
 * <p>
 * Service is NOT UI Related ->
 */



public class MyGeoService extends IntentService {
    /**
     * Must have an empty Constructor:
     * the name of the thread for debugging purposes
     */
    public MyGeoService() {
        super("MyGeoService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


        //intent = תיאור של המשימה של השירות
        //המשימה - לקבל מINTENT את הקואורדינטה - ולפענח
        if (intent == null || !intent.hasExtra("loc")) return;

        LatLng latLng = intent.getParcelableExtra("loc");
        if (latLng == null )return;

        Geocoder coder = new Geocoder(this);
        //RXAndroid
        //IO in a Thread.
        try {
            List<Address> addressList = coder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addressList.size() == 0)return;
            Address address = addressList.get(0);
            //רק כתובת אחת - שמורכבת ממספר שורות - שורה ראשונה עיר, שורה שנייה רחוב


            int lineIndex = address.getMaxAddressLineIndex();

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i <= lineIndex; i++) {
                sb.append(address.getAddressLine(i)).append("\n");
            }

            //מצאנו כתובת.String

            //לדווח תוצאה למאזין

            //Using a broadcast:
            //send a broadcast:

            //action is the name of the broadcast-> it's the identifier.
            Intent result = new Intent("address");

            result.putExtra("address", sb.toString());
            sendBroadcast(result);//context.

            //RxAndroid
            //EventBus design pattern

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
