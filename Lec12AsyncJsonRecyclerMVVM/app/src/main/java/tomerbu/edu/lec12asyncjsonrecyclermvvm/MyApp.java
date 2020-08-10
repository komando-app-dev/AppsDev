package tomerbu.edu.lec12asyncjsonrecyclermvvm;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

/**
אובייקט לכל האפליקציה - onCreate קורה ברגע שמפעילים את האפליקציה
 את האובייקט הזה רושמים במניפסט:
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        AndroidThreeTen.init(this);
        //Analytics->
        System.out.println("App Started");
    }
}
