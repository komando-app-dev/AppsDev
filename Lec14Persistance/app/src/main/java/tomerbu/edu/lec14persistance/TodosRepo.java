package tomerbu.edu.lec14persistance;

import android.content.Context;

import androidx.room.Room;

/**
 * Created by tomerbuzaglo on 10/08/2020.
 * Copyright 2020 tomerbuzaglo. All Rights Reserved
 * <p>
 * Licensed under the Apache License, Version 2.0
 * you may not use this file except
 * in compliance with the License
 */
public class TodosRepo {

    //properties:
    public TodosDatabase db;

    //private ctor
    private TodosRepo(Context context){
        //fileName.sqlite
        db = Room.databaseBuilder(context,
                TodosDatabase.class, "TodosDb").build();
    }


    private static TodosRepo sharedInstance;

    public synchronized static TodosRepo getSharedInstance(Context context){
        if (sharedInstance == null){
            sharedInstance = new TodosRepo(context);
        }

        return sharedInstance;
    };




    //Go json -> store the json to the database
}
