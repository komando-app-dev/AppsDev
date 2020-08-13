package tomerbu.edu.lec15retrofit.movies;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tomerbuzaglo on 13/08/2020.
 * Copyright 2020 tomerbuzaglo. All Rights Reserved
 * <p>
 * Licensed under the Apache License, Version 2.0
 * you may not use this file except
 * //https://api.themoviedb.org/3/genre/movie/list?api_key=b3b1492d3e91e9f9403a2989f3031b0c
 */
public class TMDBDataSource {
    //יצירת מופע של הספרייה
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    //מופע של האינטרפייס שיצרנו בשלב הקודם:
    private MovieService service = retrofit.create(MovieService.class);


    //:סינגלטון
    private static final String apiKey = "b3b1492d3e91e9f9403a2989f3031b0c";

    //בנאי פרטי
    private TMDBDataSource() {
    }

    //מופע פרטי סטטי:
    private static TMDBDataSource sharedInstance;

    //מתודת פקטורי סטטית:
    public synchronized static TMDBDataSource getInstance() {
        if (sharedInstance == null) {
            sharedInstance = new TMDBDataSource();
        }
        return sharedInstance;
    }


    //INTERNET
    public void getMovies(/*callback*/) {


        service.getMovies(apiKey).enqueue(new Callback<TMDBResponse>() {
            @Override
            public void onResponse(Call<TMDBResponse> call, Response<TMDBResponse> response) {
                //call.request().url();
                //https:tmdb/url?api_key=aaafffddd
                TMDBResponse tmdbResponse = response.body();
                if (tmdbResponse == null) return;


                //UI Thread:
                ArrayList<Movie> movies = tmdbResponse.getMovies();
                System.out.println(movies);

            }

            @Override
            public void onFailure(Call<TMDBResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
        //async לא מחזיר ערך
        //עבודה מול האינטרנט צריכה להתבצע ברקע
        //Response on the ui thread.
    }

    //getTv()
}
