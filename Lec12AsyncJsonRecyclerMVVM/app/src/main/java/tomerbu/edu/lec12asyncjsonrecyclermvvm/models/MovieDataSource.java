package tomerbu.edu.lec12asyncjsonrecyclermvvm.models;


import android.os.Handler;
import android.os.Looper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * Created by tomerbuzaglo on 03/08/2020.
 * Copyright 2020 tomerbuzaglo. All Rights Reserved
 * <p>
 * Licensed under the Apache License, Version 2.0
 * you may not use this file except
 * in compliance with the License
 */
public class MovieDataSource {

    public interface Callback {
        void moviesArrived(List<Movie> movies, Exception e);
    }
    //Thread / Executors / 3rd party (OKHTTP, RETROFIT)
    public void fetchMovies(Callback listener) {
        Handler main = new Handler(Looper.getMainLooper());
        //init an empty list to hold the parsed movies:
        ArrayList<Movie> movies = new ArrayList<>();

        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                String address = "https://api.themoviedb.org/3/discover/movie?api_key=b3b1492d3e91e9f9403a2989f3031b0c";
                String json = IO.getHttps(address);

                //{} root element is a JsonObject:
                JSONObject obj = new JSONObject(json);

                //get the results array: {results: [{...}]}
                JSONArray arr = obj.getJSONArray("results"); //not iterable...

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject movieObject = arr.getJSONObject(i);

                    String title =  movieObject.getString("title");
                    String backdropPath = movieObject.getString("backdrop_path");
                    String overview = movieObject.getString("overview");

                    //Double num = ((Number) movieObject.get("vote_average")).doubleValue();
                    Movie m = new Movie(title, overview, backdropPath);
                    movies.add(m);
                    //retrofit...
                }
                //report back to the caller (Viewmodel)
                main.post(() -> {
                    //report back on the ui thread
                    listener.moviesArrived(movies, null);
                });
            } catch (Exception e) {
                main.post(() -> {
                    listener.moviesArrived(null, e);
                });
            }
        });
        //return void
    }
}
//urlConnection.getResponseCode()
//HttpsUrlConnection:HttpUrlConnection:UrlConnection
//
//