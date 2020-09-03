package tomerbu.edu.lec15retrofit.movies;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * Created by tomerbuzaglo on 13/08/2020.
 * Copyright 2020 tomerbuzaglo. All Rights Reserved
 * <p>
 * Licensed under the Apache License, Version 2.0
 * you may not use this file except
 * in compliance with the License
 */
@Dao()
public interface MovieDao {
    //save all the movies
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(List<Movie> movies);

    //get all the movies

    @Query("SELECT * FROM Movie")
    List<Movie> getMovies();



}
