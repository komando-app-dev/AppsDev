package tomerbu.edu.lec14persistance;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 Database
 Entities
 */

//schemas/

@Database(version = 1, entities = {Todo.class}, exportSchema = false)
public abstract class TodosDatabase  extends RoomDatabase {

    abstract TodoDao getTodosDao();
    //DAO Objects as abstract methods
}

//DROP TABLE Todos
//CREATE TABLE Todos...