package tomerbu.edu.lec16location;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class FirstFragment extends Fragment {
    private static final int RC_LOCATION = 0;
    //properties:
    private GoogleMap map;

    //fused = cellular + GPS
    //דורש context אי אפשר לאתחל עכשיו - צריך לחכות שיהיה קונטסט
    private FusedLocationProviderClient mApiClient;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        SupportMapFragment mapFragment = new SupportMapFragment();
        mapFragment.getMapAsync(this::onMapReady);//method ref:
        //כשאנחנו בתוך פרגמנט - נשתמש בפרגמנט מנג'ר של הפרגמנט.
        getChildFragmentManager().
                beginTransaction().
                replace(R.id.frame, mapFragment, "mapFrag").
                commit();

        if (getContext() != null)
            mApiClient = new FusedLocationProviderClient(getContext());
    }

    public void onMapReady(GoogleMap map) {
        this.map = map; //save the map for later.

        //הוספנו סיכה על המפה:

        LatLng latLng = new LatLng(35.2566, -120.2223);
        map.addMarker(new MarkerOptions().position(latLng));

        getUserLocation();
    }


    //טכנית אפשר לבקש מספר אישורים בו זמנית - מערך של permission
    private final static String permission = Manifest.permission.ACCESS_FINE_LOCATION;
    //private final static String permission2 = Manifest.permission.CAMERA;
    //private final static String permission3 = Manifest.permission.READ_CONTACTS;

    //פעולה שדורשת רשות:
    private void getUserLocation() {
        if (getContext() == null) return;

        //בדיקה האם יש רשות:
        int result = ActivityCompat.checkSelfPermission(getContext(), permission);

        //אם אין עדיין רשות - נבקש ונצא החוצה:
        if (result != PackageManager.PERMISSION_GRANTED) {
            //request permission:
            requestPermissions(new String[]{permission}, RC_LOCATION);
            return;
        }
        //אם יש רשות: ניתן לגשת למיקום:

        //אם הגענו עד לשורה 107 - יש רשות
        Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
        //getLastKnownLocation();


        //TODO: Discuss LocationRequest
        LocationRequest request = new LocationRequest();
        //GPS / Cellular / without no energy
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); //Use case related = Battery

        //עדכוני מיקום שוטפים - מרגע שהפעלנו עד שנפסיק.
        //request.setNumUpdates(2)]

        //כל כמה זמן המערכת תדגום את הצ'יפ - אקטיבי
        request.setInterval(10_000);

        //waze -> interval(0)
        //נניח שאפליקציה אחרת מקבלת את המיקום כל 100 מילי
        request.setFastestInterval(1000);

        //request.setSmallestDisplacement(1);//

        //Looper.Main -> results will be posted on the ui thread.
        mApiClient.requestLocationUpdates(request, callback, Looper.getMainLooper());

    }

    //נגדיר callback ברמת המחלקה כדי שנוכל להפסיק להאזין בהמשך

    //לכאן תגיע התשובה:
    LocationCallback callback = new LocationCallback() {
        //ctrl+o

        @Override
        public void onLocationResult(LocationResult locationResult) {
            //locationResult.getLocations()
            Location lastLocation = locationResult.getLastLocation();
            System.out.println(lastLocation);
            LatLng latLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());


            //todo: cache bitmap (put this var as a property)
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_marker);

            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title("Your Location")
                    .snippet("Snippet")
                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap));


            map.addMarker(options);

            //animate/move the camera to the marker location:
             map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));

            //changes apply by reference:
            UiSettings settings = map.getUiSettings();
            settings.setMapToolbarEnabled(true);
            settings.setMyLocationButtonEnabled(true);

            //LatLng -> to address (reverse geocoding)
            //address -> LatLng
            System.out.println(lastLocation);


            //explicit intent:
            Intent geoIntent = new Intent(getContext(), MyGeoService.class);
            geoIntent.putExtra("loc", latLng);

            //startService:
            getContext().startService(geoIntent);
        }
    };
//    LocationCallback callback = new LocationCallback() {
//        @Override
//        public void onLocationResult(LocationResult locationResult) {
//            Location location = locationResult.getLastLocation();
//
//            //TODO: Marker
//            System.out.println(location);
//        }
//    };


    private void getLastKnownLocation() {
        //

        //מיקום אחרון: אם אפליקציות אחרות כבר השתמשו "היום" בAPI תהיה תשובה.
        //באמולטור תמיד יהיה null אלא אם כן נפתח אפליקציה אחרת.
        @SuppressLint("MissingPermission") Task<Location> lastLocation = mApiClient.getLastLocation();

        lastLocation.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //יעילות: הכל עובר גוגל - אין צורך בבקשה חדשה - אין צריכת סוללה.
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                System.out.println("Last known location: " + latitude + ",  " + longitude);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }

    //Callback (Activity or fragment)
    // קיבלנו תשובה לגבי בקשה עם requestCode
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //ירושה - תמיד נקרא ל-super
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == RC_LOCATION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getUserLocation();
        } else {
            //alert dialog -> exit or settings?
            Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
            //Intent to settings / dialog
        }
    }





    //listen
    @Override
    public void onResume() {
        super.onResume();
        //start listening:
        getContext().registerReceiver(mReceiver, new IntentFilter("address"));
    }

    //stop listening
    @Override
    public void onPause() {
        super.onPause();
        getContext().unregisterReceiver(mReceiver);
    }


    //general callback:
    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("address");
            System.out.println(result);
        }
    };
}


//dynamically add your fragment:
//

//SupportMapFragment mapFragment = new SupportMapFragment();

//SecondFragment f2 = new SecondFragment();
//f2.setArguments();

//        Bundle b = new Bundle();
//        b.putString("carType", "ferrari");
//        mapFragment.setArguments(b);
//
//        mapFragment.getMapAsync(...);


//אחרי שכבר יש פרגמנט - אפשר למצוא אותו לפי תגית:
// SupportMapFragment mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentByTag("mapFrag");

// NavHost: getActivity().getSupportFragmentManager()