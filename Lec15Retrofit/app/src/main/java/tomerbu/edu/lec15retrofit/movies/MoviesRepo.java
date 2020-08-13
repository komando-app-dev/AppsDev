package tomerbu.edu.lec15retrofit.movies;

import android.content.Context;

import androidx.room.Room;

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
 * אנחנו נבקש את הסרטים
 * הסרטים יגיעו או מDB או מהאינטרנט
 * הסרטים יגיעו או מDB או RottenTomatoes
 */
public class MoviesRepo {
    private MovieDatabase db;

    //constructor: init my properties
    private MoviesRepo(Context context) {
        //Room-> factory method (movies = sqlite fileName)
        db =  Room.databaseBuilder(context, MovieDatabase.class, "movies").build();
    }

    //singleton:
    //1)private משתנה סטטי
    private static MoviesRepo sharedInstance;
    //2)מתודת פקטורי
    public synchronized static MoviesRepo getInstance(Context context){
        if (sharedInstance == null){
            sharedInstance = new MoviesRepo(context);
        }
        return sharedInstance;
    }

}
