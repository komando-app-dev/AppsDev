package tomerbu.edu.lec12asyncjsonrecyclermvvm.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tomerbuzaglo on 06/08/2020.
 * Copyright 2020 tomerbuzaglo. All Rights Reserved
 * <p>
 * Licensed under the Apache License, Version 2.0
 * you may not use this file except
 * in compliance with the License
 */
public class Movie implements Parcelable {
    private String title;
    private String overview;
    private String backdropPath;//backdrop_path

    public Movie(String title, String overview, String backdropPath) {
        this.title = title;
        this.overview = overview;
        this.backdropPath = backdropPath;
    }

    //getters:
    public String getTitle() {
        return title;
    }
    public String getOverview() {
        return overview;
    }
    public String getBackdropPath() {
        return "https://image.tmdb.org/t/p/w500" + backdropPath;
    } //          https://image.tmdb.org/t/p/w500/avjfiojjijiasdfjf.jpg

    //toString:

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                '}';
    }


    protected Movie(Parcel in) {
        title = in.readString();
        overview = in.readString();
        backdropPath = in.readString();
    }
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(overview);
        parcel.writeString(backdropPath);
    }
}
