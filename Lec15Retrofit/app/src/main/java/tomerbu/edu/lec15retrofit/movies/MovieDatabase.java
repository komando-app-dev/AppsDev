package tomerbu.edu.lec15retrofit.movies;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Created by tomerbuzaglo on 13/08/2020.
 * Copyright 2020 tomerbuzaglo. All Rights Reserved
 * <p>
 * Licensed under the Apache License, Version 2.0
 * you may not use this file except
 * in compliance with the License
 */

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao getMovieDao();
    //...other daos ass well.
}
