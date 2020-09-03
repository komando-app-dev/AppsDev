package tomerbu.edu.lec15retrofit.movies;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tomerbuzaglo on 13/08/2020.
 * Copyright 2020 tomerbuzaglo. All Rights Reserved
 * <p>
 * Licensed under the Apache License, Version 2.0
 * you may not use this file except
 * in compliance with the
 * <p>
 * <p>
 * Repository: להחזיק מידע מכמה מקורות
 * UI יבקש את הסרטים
 * הסרטים יגיעו או מDB או מהאינטרנט
 */
public class MoviesRepo {//MovieRepo.class
    private MovieDatabase db;

    //used for DB Access:
    private Executor executor = Executors.newSingleThreadExecutor();
    private Handler uiThread = new Handler(Looper.getMainLooper());

    //constructor: init my properties
    private MoviesRepo(Context context) {
        //Room-> factory method (movies = sqlite fileName)
        db = Room.databaseBuilder(context, MovieDatabase.class, "movies").build();
    }

    //singleton:
    //1)private משתנה סטטי
    private static MoviesRepo sharedInstance;

    //2)מתודת פקטורי
    public synchronized static MoviesRepo getInstance(Context context) {
        if (sharedInstance == null) {
            sharedInstance = new MoviesRepo(context);
        }
        return sharedInstance;
    }

    //Room:
    //Background Thread.
    private void save(List<Movie> movies) {//Database on Main Thread
        db.getMovieDao().save(movies);
    }

    private List<Movie> fetchFromDatabase() {
        return db.getMovieDao().getMovies();
    }

    //נגדיר מאזין עבור הUI
    public interface MovieCallback {
        void onResponse(List<Movie> movies, Exception exc);
    }

    //הUI יבקש את הסרטים - ויעביר למבדה
    public void getMovies(MovieCallback callback) {
        //policy: להביא את הסרטים מהאינטרנט כל עוד יש חיבור
        TMDBDataSource.getInstance().getMovies(new Callback<TMDBResponse>() {
            @Override
            public void onResponse(Call<TMDBResponse> call, Response<TMDBResponse> response) {
                //if body not null

                TMDBResponse res = response.body();

                uiThread.post(() -> {
                    if (res == null) {
                        callback.onResponse(null, null);
                        return;
                    }
                    //יש אינטרנט - הגיע מידע חדש.
                    callback.onResponse(res.getMovies(), null);

                });


                //Still goto db on the Executor:
                //save movies to db //Background.

                //insert movies that already exist. Primary key.
                executor.execute(() -> save(res.getMovies()));
            }

            @Override //Throwable is the base class for Exceptions
            public void onFailure(Call<TMDBResponse> call, Throwable t) {
                //אם אין סרטים - ננסה מהדטה בייס
                executor.execute(() -> {
                    List<Movie> movies = fetchFromDatabase();

                    //callback on the UI Thread:
                    uiThread.post(() -> {
                        if (movies.size() == 0) {
                            callback.onResponse(null, new Exception(t));
                        } else {
                            callback.onResponse(movies, null);
                        }
                    });
                });

                //אם בכל זאת אין סרטים גם בDB ->
                //callback שלילי
            }
        });
    }

    //Room + retrofit
    //public void getMovies(callback)


    //policy: getSharedPrefs -> תאריך עדכון - אם לא עבר שבוע - נביא מדטה-בייס
}
